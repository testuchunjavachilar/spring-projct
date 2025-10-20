package uz.salikhdev.springprojct.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.salikhdev.springprojct.dto.MessageDto;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, RuntimeException.class})
    public ResponseEntity<MessageDto> handleException(Exception e) {
        return ResponseEntity.badRequest()
                .body(
                        MessageDto.builder()
                                .message(e.getMessage())
                                .timestamp(LocalDateTime.now().toString())
                                .status(false)
                                .build()
                );
    }


}
