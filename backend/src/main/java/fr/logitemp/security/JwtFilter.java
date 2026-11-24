package fr.logitemp.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {
 private final JwtService jwtService;
 public JwtFilter(JwtService jwtService){this.jwtService=jwtService;}
 @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)
   throws IOException,ServletException {
  String authorization=request.getHeader("Authorization");
  if(authorization!=null && authorization.startsWith("Bearer ")) {
   try {
    var claims=jwtService.verify(authorization.substring(7));
    String role=claims.get("role",String.class);
    var auth=new UsernamePasswordAuthenticationToken(claims.getSubject(),null,List.of(new SimpleGrantedAuthority(role)));
    SecurityContextHolder.getContext().setAuthentication(auth);
   } catch(Exception ignored) { SecurityContextHolder.clearContext(); }
  }
  chain.doFilter(request,response);
 }
}
