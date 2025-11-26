package via.pro3.pro_assignment3.Repositories;

import org.springframework.data.repository.CrudRepository;
import via.pro3.pro_assignment3.Model.Animal;

import java.time.LocalDate;
import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, String>  {

    List<Animal> findByArrivalDate(LocalDate arrivalDate);
    
    List<Animal> findByOriginFarm(String originFarm);
}
