package via.station3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Recall Functionality
    @GetMapping("/recall/animal/{animalRegNumber}")
    public List<Product> getProductsByAnimal(@PathVariable String animalRegNumber) {
        // 1. Find all trays this animal is in
        List<TrayAnimalLookup> trayLookups = trayAnimalLookupRepository.findByAnimalRegistrationNumber(animalRegNumber);

        // 2. Find all products that contain these trays
        Set<Product> products = new HashSet<>();
        for (TrayAnimalLookup lookup : trayLookups) {
            List<Product> productsWithTray = productRepository.findByTrayIds(lookup.getTrayId());
            products.addAll(productsWithTray);
        }
        return new ArrayList<>(products);
    }
}
