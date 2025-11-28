package via.station3.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_tray", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tray_id")
    private Set<Long> trayIds = new HashSet<>();

    public Product() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<Long> getTrayIds() { return trayIds; }
    public void setTrayIds(Set<Long> trayIds) { this.trayIds = trayIds; }
}
