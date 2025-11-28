package via.station2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import via.station2.Repositories.PartRepository;
import via.station2.Repositories.TrayRepository;

@SpringBootTest
class Station2ApplicationTests {

    @MockBean
    private PartRepository partRepository;
    @MockBean
    private TrayRepository trayRepository;

    @Test
    void contextLoads() {
    }
}
