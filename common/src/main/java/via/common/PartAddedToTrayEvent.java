package via.common;

import java.io.Serializable;
import java.util.List;

public class PartAddedToTrayEvent implements Serializable {
    private Long trayId;
    private String animalRegistrationNumber;

    public PartAddedToTrayEvent() {}

    public PartAddedToTrayEvent(Long trayId, String animalRegistrationNumber) {
        this.trayId = trayId;
        this.animalRegistrationNumber = animalRegistrationNumber;
    }

    public Long getTrayId() { return trayId; }
    public void setTrayId(Long trayId) { this.trayId = trayId; }
    public String getAnimalRegistrationNumber() { return animalRegistrationNumber; }
    public void setAnimalRegistrationNumber(String animalRegistrationNumber) { this.animalRegistrationNumber = animalRegistrationNumber; }
}
