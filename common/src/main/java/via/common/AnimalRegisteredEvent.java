package via.common;

import java.io.Serializable;
import java.time.LocalDate;

public class AnimalRegisteredEvent implements Serializable {
    private String registrationNumber;
    private LocalDate arrivalDate;
    private String originFarm;
    private String type;
    private Double weight;

    public AnimalRegisteredEvent() {}

    public AnimalRegisteredEvent(String registrationNumber, LocalDate arrivalDate, String originFarm, String type, Double weight) {
        this.registrationNumber = registrationNumber;
        this.arrivalDate = arrivalDate;
        this.originFarm = originFarm;
        this.type = type;
        this.weight = weight;
    }


    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public LocalDate getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(LocalDate arrivalDate) { this.arrivalDate = arrivalDate; }
    public String getOriginFarm() { return originFarm; }
    public void setOriginFarm(String originFarm) { this.originFarm = originFarm; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
}
