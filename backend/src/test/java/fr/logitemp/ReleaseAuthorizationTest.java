package fr.logitemp;
import fr.logitemp.service.ShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.prepost.PreAuthorize;
import static org.junit.jupiter.api.Assertions.*;

class ReleaseAuthorizationTest {
 @Test void releaseRequiresQualityRole() throws Exception {
  var method=ShipmentService.class.getMethod("release",Long.class);
  var annotation=method.getAnnotation(PreAuthorize.class);
  assertNotNull(annotation,"La transition RELEASED doit être protégée au niveau service");
  assertTrue(annotation.value().contains("QUALITY"));
 }
}
