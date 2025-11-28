package via.station3.Repositories;

import org.springframework.data.repository.CrudRepository;
import via.station3.Model.Product;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByTrayIds(Long trayId);
}
