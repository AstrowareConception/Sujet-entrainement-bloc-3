package fr.logitemp.api;
import fr.logitemp.api.Contracts.ReadingInput;
import fr.logitemp.domain.TelemetryReading;
import fr.logitemp.service.TelemetryService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/telemetry")
public class TelemetryController {
 private final TelemetryService service;
 public TelemetryController(TelemetryService service){this.service=service;}
 @GetMapping("/{shipmentId}") public List<TelemetryReading> list(@PathVariable Long shipmentId){return service.list(shipmentId);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public TelemetryReading add(@RequestBody ReadingInput input){return service.save(input);}
}
