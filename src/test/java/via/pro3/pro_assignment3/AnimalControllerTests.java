package via.pro3.pro_assignment3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import via.pro3.pro_assignment3.Model.Animal;
import via.pro3.pro_assignment3.Repositories.AnimalRepository;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AnimalRepository animalRepository;

    private static final String API_PATH = "/api/animals";
    private static final String TEST_REG_NUMBER = "T001-ISOLATED";
    private static final String TEST_ORIGIN_FARM = "IsolatedFarm";
    private static final LocalDate TEST_ARRIVAL_DATE = LocalDate.of(2025, 1, 1);

    private Animal testAnimal;

    @BeforeEach
    void setup() {
        testAnimal = new Animal();
        testAnimal.setId(TEST_REG_NUMBER);
        testAnimal.setType("SetupType");
        testAnimal.setWeight(500.0);
        testAnimal.setOrigin(TEST_ORIGIN_FARM);
        testAnimal.setArrivalDate(TEST_ARRIVAL_DATE);

        animalRepository.deleteAll();
        animalRepository.save(testAnimal);
    }

    // --- 1. Test POST: createAnimal() ---
    @Test
    void shouldCreateNewAnimal() throws Exception {
        String newRegNum = "T002-NEW";
        Animal newAnimal = new Animal();
        newAnimal.setId(newRegNum);
        newAnimal.setType("NewType");
        newAnimal.setWeight(300.0);
        newAnimal.setOrigin("NewFarm");

        mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAnimal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registrationNumber").value(newRegNum));

        assertTrue(animalRepository.findById(newRegNum).isPresent());
    }

    // --- 2. Test GET All: getAllAnimals() ---
    @Test
    void shouldGetAllAnimals() throws Exception {
        mockMvc.perform(get(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].registrationNumber").value(TEST_REG_NUMBER));
    }

    // --- 3. Test GET by ID: getAnimalById() ---
    @Test
    void shouldGetAnimalById() throws Exception {
        mockMvc.perform(get(API_PATH + "/{id}", TEST_REG_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registrationNumber").value(TEST_REG_NUMBER))
                .andExpect(jsonPath("$.type").value("SetupType"));
    }

    // --- 4. Test GET by Date: getAllAnimalsParticularArrivedDate() ---
    @Test
    void shouldGetAnimalsByArrivalDate() throws Exception {
        String dateString = TEST_ARRIVAL_DATE.toString();

        mockMvc.perform(get(API_PATH + "/by-date/{date}", dateString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].registrationNumber").value(TEST_REG_NUMBER));
    }

    // --- 5. Test GET by Origin: getAllAnimalsParticularOrigin() ---
    @Test
    void shouldGetAnimalsByOrigin() throws Exception {
        mockMvc.perform(get(API_PATH + "/by-origin/{origin}", TEST_ORIGIN_FARM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].registrationNumber").value(TEST_REG_NUMBER));
    }
}