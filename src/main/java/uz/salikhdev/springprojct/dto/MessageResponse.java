package uz.salikhdev.springprojct.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MessageResponse(
        String message,
        Boolean status,
        LocalDateTime timestamp
) {

    public static MessageResponse success(String message) {
        return MessageResponse.builder()
                .message(message)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static MessageResponse error(String message) {
        return MessageResponse.builder()
                .message("Xatolik: " +message)
                .status(false)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
