package fr.logitemp.api;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiErrorHandler {
 @ExceptionHandler(IllegalArgumentException.class)
 public ResponseEntity<Map<String,String>> badRequest(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(UnsupportedOperationException.class)
 public ResponseEntity<Map<String,String>> incomplete(UnsupportedOperationException e){return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(Map.of("error",e.getMessage()));}
}
