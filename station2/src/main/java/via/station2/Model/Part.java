package via.station2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double weight;
    private String type;
    private String animalRegistrationNumber;

    public Part() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getAnimalRegistrationNumber() { return animalRegistrationNumber; }
    public void setAnimalRegistrationNumber(String animalRegistrationNumber) { this.animalRegistrationNumber = animalRegistrationNumber; }
}
