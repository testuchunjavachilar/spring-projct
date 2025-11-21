package uz.salikhdev.springprojct.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentResponse(
        Long id,
        String content,
        String authorUsername,
        LocalDateTime createdAt
) { }
