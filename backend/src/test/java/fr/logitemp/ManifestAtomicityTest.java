package fr.logitemp;
import fr.logitemp.api.Contracts.ImportManifest;
import fr.logitemp.service.ShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

class ManifestAtomicityTest {
 @Test void batchImportMustBeTransactional() throws Exception {
  var method=ShipmentService.class.getMethod("importAll",ImportManifest.class);
  assertNotNull(method.getAnnotation(Transactional.class),"L'import doit être atomique");
 }
}
