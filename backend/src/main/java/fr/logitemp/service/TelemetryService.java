package fr.logitemp.service;
import fr.logitemp.api.Contracts.ReadingInput;
import fr.logitemp.domain.TelemetryReading;
import fr.logitemp.repo.ShipmentRepository;
import fr.logitemp.repo.TelemetryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TelemetryService {
 private final ShipmentRepository shipments;
 private final TelemetryRepository readings;
 public TelemetryService(ShipmentRepository shipments,TelemetryRepository readings) {
  this.shipments=shipments;this.readings=readings;
 }
 public TelemetryReading save(ReadingInput input) {
  if(input.temperatureC()==null || input.humidityPct()==null || input.measuredAt()==null) throw new IllegalArgumentException("Relevé incomplet");
  if(input.humidityPct()<0 || input.humidityPct()>100) throw new IllegalArgumentException("Humidité invalide");
  var shipment=shipments.findById(input.shipmentId()).orElseThrow(()->new IllegalArgumentException("Expédition inexistante"));
  return readings.save(new TelemetryReading(shipment,input.temperatureC(),input.humidityPct(),TelemetryTimeNormalizer.normalize(input.measuredAt())));
 }
 public List<TelemetryReading> list(Long shipmentId) {
  return readings.findByShipmentIdOrderByMeasuredAtDesc(shipmentId);
 }
}
