package via.station3.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(TrayAnimalLookup.class)
public class TrayAnimalLookup implements Serializable {
    @Id
    private Long trayId;
    @Id
    private String animalRegistrationNumber;

    public TrayAnimalLookup() {}

    public TrayAnimalLookup(Long trayId, String animalRegistrationNumber) {
        this.trayId = trayId;
        this.animalRegistrationNumber = animalRegistrationNumber;
    }

    public Long getTrayId() { return trayId; }
    public void setTrayId(Long trayId) { this.trayId = trayId; }
    public String getAnimalRegistrationNumber() { return animalRegistrationNumber; }
    public void setAnimalRegistrationNumber(String animalRegistrationNumber) { this.animalRegistrationNumber = animalRegistrationNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrayAnimalLookup that = (TrayAnimalLookup) o;
        return Objects.equals(trayId, that.trayId) && Objects.equals(animalRegistrationNumber, that.animalRegistrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trayId, animalRegistrationNumber);
    }
}
