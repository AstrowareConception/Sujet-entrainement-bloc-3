package fr.logitemp;
import fr.logitemp.domain.Shipment;
import fr.logitemp.domain.ShipmentStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DomainBaselineTest {
 @Test void aNewShipmentStartsAsCreated(){assertEquals(ShipmentStatus.CREATED,new Shipment("LT-TEST","Lait",0.0,4.0).getStatus());}
}
