package fr.logitemp.repo;
import fr.logitemp.domain.TelemetryReading;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TelemetryRepository extends JpaRepository<TelemetryReading,Long> {
 List<TelemetryReading> findByShipmentIdOrderByMeasuredAtDesc(Long shipmentId);
}
