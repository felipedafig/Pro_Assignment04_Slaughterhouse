package via.common;

import java.io.Serializable;
import java.util.Set;

public class ProductCreatedEvent implements Serializable {
    private Long productId;
    private Set<Long> trayIds;

    public ProductCreatedEvent() {}

    public ProductCreatedEvent(Long productId, Set<Long> trayIds) {
        this.productId = productId;
        this.trayIds = trayIds;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Set<Long> getTrayIds() { return trayIds; }
    public void setTrayIds(Set<Long> trayIds) { this.trayIds = trayIds; }
}
