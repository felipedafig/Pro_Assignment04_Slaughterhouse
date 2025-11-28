package via.pro3.pro_assignment4.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "animal")
public class Animal {
    // Default constructor required by JPA
    public Animal() {}
    
    @Id
    @Column(name = "registration_number")
    private String animalId;

    @Column(name = "type")
    private String type;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "origin_farm")
    private String originFarm;


    public String getId() {
        return animalId;
    }

    public void setId(String registrationNumber) {
        this.animalId = registrationNumber;
    }


    public String getRegistrationNumber() {
        return animalId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return originFarm;
    }

    public void setOrigin(String originFarm) {
        this.originFarm = originFarm;
    }
}