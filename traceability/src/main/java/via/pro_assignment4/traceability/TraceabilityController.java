package via.pro_assignment4.traceability;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/trace")
public class TraceabilityController {

    private final TraceRecordRepository repository;

    public TraceabilityController(TraceRecordRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TraceRecord> getAllRecords() {
        return repository.findAll();
    }
}
