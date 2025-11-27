package via.station3.Repositories;

import org.springframework.data.repository.CrudRepository;
import via.station3.Model.TrayAnimalLookup;
import java.util.List;

public interface TrayAnimalLookupRepository extends CrudRepository<TrayAnimalLookup, Long> {
    List<TrayAnimalLookup> findByAnimalRegistrationNumber(String animalRegistrationNumber);
    List<TrayAnimalLookup> findByTrayId(Long trayId);
}
