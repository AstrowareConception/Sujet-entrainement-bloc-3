package fr.logitemp.api;
import fr.logitemp.api.Contracts.LoginRequest;
import fr.logitemp.api.Contracts.LoginResponse;
import fr.logitemp.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AuthenticationManager authenticationManager;
 private final JwtService jwt;
 public AuthController(AuthenticationManager manager,JwtService jwt){this.authenticationManager=manager;this.jwt=jwt;}
 @PostMapping("/login") public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
  var auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(),request.password()));
  String role=auth.getAuthorities().iterator().next().getAuthority();
  return ResponseEntity.ok(new LoginResponse(jwt.issue(request.email(),role),request.email(),role));
 }
}
