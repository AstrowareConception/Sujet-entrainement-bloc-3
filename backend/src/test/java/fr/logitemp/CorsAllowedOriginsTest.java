package fr.logitemp;
import fr.logitemp.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.junit.jupiter.api.Assertions.*;

class CorsAllowedOriginsTest {
 @Test void studentAngularOriginMustBeAllowed() {
  var request=new MockHttpServletRequest("OPTIONS","/api/shipments");
  var config=new SecurityConfig().corsConfigurationSource().getCorsConfiguration(request);
  assertNotNull(config);
  assertTrue(config.getAllowedOrigins().contains("http://localhost:4200"));
  assertTrue(config.getAllowedOrigins().contains("http://localhost:8081"));
 }
}
