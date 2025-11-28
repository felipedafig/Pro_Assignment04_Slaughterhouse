package via.pro_assignment4.Controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import via.pro_assignment4.Model.Animal;
import via.pro_assignment4.Repositories.AnimalRepository;
import via.common.AnimalRegisteredEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public Iterable<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable String id) {
        Optional<Animal> animal = animalRepository.findById(id);
        return animal.orElse(null);
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        animal.setArrivalDate(LocalDate.now());
        Animal savedAnimal = animalRepository.save(animal);


        AnimalRegisteredEvent event = new AnimalRegisteredEvent(
            savedAnimal.getRegistrationNumber(),
            savedAnimal.getArrivalDate(),
            savedAnimal.getOrigin(),
            savedAnimal.getType(),
            savedAnimal.getWeight()
        );
        rabbitTemplate.convertAndSend("animal.registered", event);

        return savedAnimal;
    }

    @GetMapping("/by-date/{date}")
    public List<Animal> getAllAnimalsParticularArrivedDate(@PathVariable LocalDate date) {
        return animalRepository.findByArrivalDate(date);
    }

    @GetMapping("/by-origin/{origin}")
    public List<Animal> getAllAnimalsParticularOrigin(@PathVariable String origin) {
        return animalRepository.findByOriginFarm(origin);
    }
}