package fr.logitemp;

import fr.logitemp.domain.Shipment;
import fr.logitemp.repo.ShipmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LogiTempApplication {
  public static void main(String[] args) { SpringApplication.run(LogiTempApplication.class, args); }
  @Bean CommandLineRunner sampleShipments(ShipmentRepository repository) {
    return args -> {
      if (repository.count() == 0) {
        repository.save(new Shipment("LT-VAL-0182", "Produits laitiers", 0.0, 4.0));
        repository.save(new Shipment("LT-LYO-0227", "Produits frais", 2.0, 8.0));
        repository.save(new Shipment("LT-MRS-1041", "Poisson frais", 0.0, 2.0));
      }
    };
  }
}
