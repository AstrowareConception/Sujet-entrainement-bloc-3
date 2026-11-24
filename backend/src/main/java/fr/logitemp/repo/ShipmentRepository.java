package fr.logitemp.repo;
import fr.logitemp.domain.Shipment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
 boolean existsByTrackingCode(String trackingCode);
 Optional<Shipment> findByTrackingCode(String trackingCode);
}
