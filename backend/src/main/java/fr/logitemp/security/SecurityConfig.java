package fr.logitemp.security;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration @EnableMethodSecurity
public class SecurityConfig {
 @Bean UserDetailsService users() {
  // Comptes de démonstration uniquement : ne pas réutiliser en production.
  return new InMemoryUserDetailsManager(
    User.withUsername("operateur@logitemp.test").password("{noop}Demo1234!").roles("OPERATOR").build(),
    User.withUsername("qualite@logitemp.test").password("{noop}Demo1234!").roles("QUALITY").build()
  );
 }
 @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {return config.getAuthenticationManager();}
 @Bean CorsConfigurationSource corsConfigurationSource() {
  var config=new CorsConfiguration();
  // ANOMALIE VOLONTAIRE LT-02 : les véritables origines de l'UI ne figurent pas ici.
  config.setAllowedOrigins(List.of("http://localhost:4301"));
  config.setAllowedMethods(List.of("GET","POST","PATCH","OPTIONS"));
  config.setAllowedHeaders(List.of("Authorization","Content-Type"));
  var source=new UrlBasedCorsConfigurationSource();
  source.registerCorsConfiguration("/api/**",config);
  return source;
 }
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http,JwtFilter jwtFilter,CorsConfigurationSource corsConfigurationSource) throws Exception {
  return http.csrf(csrf->csrf.disable()).cors(cors->cors.configurationSource(corsConfigurationSource))
    .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(a->a.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
     .requestMatchers("/api/auth/login","/actuator/health").permitAll().anyRequest().authenticated())
    .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class).build();
 }
}
