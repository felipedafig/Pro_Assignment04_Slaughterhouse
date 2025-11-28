package via.pro_assignment4.traceability;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import via.common.AnimalRegisteredEvent;
import via.common.PartAddedToTrayEvent;
import via.common.ProductCreatedEvent;
import java.time.LocalDateTime;

@Component
public class TraceabilityListener {

    private final TraceRecordRepository repository;

    public TraceabilityListener(TraceRecordRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "trace-animal-queue")
    public void handleAnimalRegistered(AnimalRegisteredEvent event) {
        TraceRecord record = new TraceRecord(
            "ANIMAL_REGISTERED",
            "Animal " + event.getRegistrationNumber() + " registered with weight " + event.getWeight() + " at " + event.getArrivalDate(),
            LocalDateTime.now()
        );
        repository.save(record);
    }

    @RabbitListener(queues = "trace-product-queue")
    public void handleProductCreated(ProductCreatedEvent event) {
        TraceRecord record = new TraceRecord(
            "PRODUCT_CREATED",
            "Product " + event.getProductId() + " created from trays " + event.getTrayIds(),
            LocalDateTime.now()
        );
        repository.save(record);
    }

    @RabbitListener(queues = "trace-part-queue")
    public void handlePartAdded(PartAddedToTrayEvent event) {
        TraceRecord record = new TraceRecord(
            "PART_ADDED",
            "Part from animal " + event.getAnimalRegistrationNumber() + " added to tray " + event.getTrayId(),
            LocalDateTime.now()
        );
        repository.save(record);
    }
}
