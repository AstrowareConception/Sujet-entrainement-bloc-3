package fr.logitemp.api;
import fr.logitemp.api.Contracts.ImportManifest;
import fr.logitemp.domain.Shipment;
import fr.logitemp.service.ShipmentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/shipments")
public class ShipmentController {
 private final ShipmentService service;
 public ShipmentController(ShipmentService service){this.service=service;}
 @GetMapping public List<Shipment> list(){return service.list();}
 @PostMapping("/import") @ResponseStatus(HttpStatus.CREATED)
 public List<Shipment> importManifest(@RequestBody ImportManifest manifest){return service.importAll(manifest);}
 @PatchMapping("/{id}/release") public Shipment release(@PathVariable Long id){return service.release(id);}
}
