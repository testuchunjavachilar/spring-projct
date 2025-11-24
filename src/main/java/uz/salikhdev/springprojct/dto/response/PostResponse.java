package uz.salikhdev.springprojct.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostResponse(
        Long id,
        String title,
        String description,
        String resourceUrl,
        Long likes,
        String author,
        LocalDateTime createdAt
) {
}
