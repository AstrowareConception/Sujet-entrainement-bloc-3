package fr.logitemp.api;
import java.util.List;

public final class Contracts {
 private Contracts() {}
 public record LoginRequest(String email,String password) {}
 public record LoginResponse(String token,String email,String role) {}
 public record ReadingInput(Long shipmentId,Double temperatureC,Double humidityPct,String measuredAt) {}
 public record ShipmentDraft(String trackingCode,String productName,Double minTempC,Double maxTempC) {}
 public record ImportManifest(List<ShipmentDraft> shipments) {}
}
