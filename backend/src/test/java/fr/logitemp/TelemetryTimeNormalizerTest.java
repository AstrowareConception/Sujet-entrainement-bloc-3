package fr.logitemp;
import fr.logitemp.service.TelemetryTimeNormalizer;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TelemetryTimeNormalizerTest {
 @Test void timezoneOffsetMustBePreservedAsUtcInstant() {
  assertEquals(Instant.parse("2026-07-14T09:30:00Z"),TelemetryTimeNormalizer.normalize("2026-07-14T11:30:00+02:00"));
 }
}
