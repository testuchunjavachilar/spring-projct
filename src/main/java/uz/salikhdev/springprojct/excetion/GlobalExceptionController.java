package uz.salikhdev.springprojct.excetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.salikhdev.springprojct.dto.response.MessageResponse;

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

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<?> badRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                MessageResponse.error(e.getMessage())
        );
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<?> notFoud(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                MessageResponse.error(e.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                MessageResponse.error(errorMessage)
        );
    }

}
