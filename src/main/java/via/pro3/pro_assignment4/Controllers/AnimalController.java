package via.pro3.pro_assignment4.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import via.pro3.pro_assignment4.Model.Animal;
import via.pro3.pro_assignment4.Repositories.AnimalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

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
        return animalRepository.save(animal);
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