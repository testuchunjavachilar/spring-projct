package uz.salikhdev.springprojct.excetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.salikhdev.springprojct.dto.MessageResponse;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<?> error(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                MessageResponse.error(e.getMessage())
        );
    }

    @ExceptionHandler(AlreadyExistException.class)
    private ResponseEntity<?> already(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                MessageResponse.error(e.getMessage())
        );
    }
}
