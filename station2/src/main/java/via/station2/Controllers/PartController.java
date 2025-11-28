package via.station2.Controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import via.common.PartAddedToTrayEvent;
import via.station2.Model.Part;
import via.station2.Model.Tray;
import via.station2.Repositories.PartRepository;
import via.station2.Repositories.TrayRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    @Autowired
    private PartRepository partRepository;
    @Autowired
    private TrayRepository trayRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/create")
    public Part createPart(@RequestBody Part part) {
        return partRepository.save(part);
    }

    @PostMapping("/add-to-tray/{trayId}")
    public Tray addPartToTray(@PathVariable Long trayId, @RequestBody Part part) {

        part = partRepository.save(part);

        Optional<Tray> trayOpt = trayRepository.findById(trayId);
        if (trayOpt.isPresent()) {
            Tray tray = trayOpt.get();

            tray.getParts().add(part);
            tray.setCurrentWeight(tray.getCurrentWeight() + part.getWeight());
            Tray savedTray = trayRepository.save(tray);


            PartAddedToTrayEvent event = new PartAddedToTrayEvent(savedTray.getId(), part.getAnimalRegistrationNumber());
            rabbitTemplate.convertAndSend("part.added.to.tray", event);

            return savedTray;
        }
        return null;
    }

    @PostMapping("/create-tray")
    public Tray createTray(@RequestBody Tray tray) {
        tray.setCurrentWeight(0.0);
        return trayRepository.save(tray);
    }
}
