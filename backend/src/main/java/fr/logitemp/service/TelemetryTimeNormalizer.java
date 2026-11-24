package fr.logitemp.service;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public final class TelemetryTimeNormalizer {
 private TelemetryTimeNormalizer() {}
 public static Instant normalize(String sourceTimestamp) {
  // ANOMALIE VOLONTAIRE LT-01 : l'offset local est perdu pendant la conversion.
  return OffsetDateTime.parse(sourceTimestamp).toLocalDateTime().toInstant(ZoneOffset.UTC);
 }
}
