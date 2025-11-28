package via.station3.Controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import via.common.ProductCreatedEvent;
import via.station3.Model.Product;
import via.station3.Model.TrayAnimalLookup;
import via.station3.Repositories.ProductRepository;
import via.station3.Repositories.TrayAnimalLookupRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TrayAnimalLookupRepository trayAnimalLookupRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        ProductCreatedEvent event = new ProductCreatedEvent(savedProduct.getId(), savedProduct.getTrayIds());
        rabbitTemplate.convertAndSend("product-events", "", event);
        return savedProduct;
    }


    @GetMapping("/recall/animal/{animalRegNumber}")
    public List<Product> getProductsByAnimal(@PathVariable String animalRegNumber) {

        List<TrayAnimalLookup> trayLookups = trayAnimalLookupRepository.findByAnimalRegistrationNumber(animalRegNumber);


        Set<Product> products = new HashSet<>();
        for (TrayAnimalLookup lookup : trayLookups) {
            List<Product> productsWithTray = productRepository.findByTrayIds(lookup.getTrayId());
            products.addAll(productsWithTray);
        }
        return new ArrayList<>(products);
    }
}
