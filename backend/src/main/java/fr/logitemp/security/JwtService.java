package fr.logitemp.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
 private final SecretKey key;
 public JwtService(@Value("${app.jwt.secret}") String secret){key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));}
 public String issue(String email,String role){
  return Jwts.builder().subject(email).claim("role",role).issuedAt(new Date())
   .expiration(Date.from(Instant.now().plusSeconds(3600))).signWith(key).compact();
 }
 public Claims verify(String jwt){return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();}
}
