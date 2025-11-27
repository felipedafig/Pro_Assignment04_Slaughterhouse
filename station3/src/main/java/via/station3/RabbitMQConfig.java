package via.station3;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import via.common.PartAddedToTrayEvent;
import via.station3.Model.TrayAnimalLookup;
import via.station3.Repositories.TrayAnimalLookupRepository;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private TrayAnimalLookupRepository trayAnimalLookupRepository;

    @RabbitListener(queues = "part.added.to.tray")
    public void receivePartAddedToTray(PartAddedToTrayEvent event) {
        System.out.println("Station 3 received Part Added to Tray: " + event.getTrayId() + ", Animal: " + event.getAnimalRegistrationNumber());

        // Store mapping. Note: PK is (trayId, animalReg). Idempotent insert.
        TrayAnimalLookup lookup = new TrayAnimalLookup(event.getTrayId(), event.getAnimalRegistrationNumber());
        trayAnimalLookupRepository.save(lookup);
    }
}
