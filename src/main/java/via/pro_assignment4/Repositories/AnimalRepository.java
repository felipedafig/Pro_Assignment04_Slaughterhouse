package via.pro_assignment4.Repositories;

import org.springframework.data.repository.CrudRepository;
import via.pro_assignment4.Model.Animal;

import java.time.LocalDate;
import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, String>  {

    List<Animal> findByArrivalDate(LocalDate arrivalDate);
    
    List<Animal> findByOriginFarm(String originFarm);
}
