package fr.logitemp.service;
import fr.logitemp.api.Contracts.ImportManifest;
import fr.logitemp.api.Contracts.ShipmentDraft;
import fr.logitemp.domain.Shipment;
import fr.logitemp.repo.ShipmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {
 private final ShipmentRepository repository;
 public ShipmentService(ShipmentRepository repository){this.repository=repository;}
 public List<Shipment> list(){return repository.findAll();}
 public Shipment release(Long id) {
  // ANOMALIE VOLONTAIRE LT-04 : aucune vérification métier du rôle QUALITÉ.
  var shipment=repository.findById(id).orElseThrow(()->new IllegalArgumentException("Expédition introuvable"));
  shipment.release();
  return repository.save(shipment);
 }
 public List<Shipment> importAll(ImportManifest manifest) {
  // FONCTIONNALITÉ MANQUANTE LT-03 : import atomique JSON à implémenter.
  throw new UnsupportedOperationException("Import de lots indisponible");
 }
}
