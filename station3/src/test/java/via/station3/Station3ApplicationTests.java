package via.station3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import via.station3.Repositories.ProductRepository;
import via.station3.Repositories.TrayAnimalLookupRepository;

@SpringBootTest
class Station3ApplicationTests {

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private TrayAnimalLookupRepository trayAnimalLookupRepository;

    @Test
    void contextLoads() {
    }
}
