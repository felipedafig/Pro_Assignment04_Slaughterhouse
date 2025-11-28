package via.pro3.pro_assignment4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "server.ssl.key-store=",
    "server.ssl.key-store-password=",
    "server.ssl.key-store-type=",
    "server.ssl.key-alias="
})
class ProAssignment4ApplicationTests {

    @Test
    void contextLoads() {
    }

}

