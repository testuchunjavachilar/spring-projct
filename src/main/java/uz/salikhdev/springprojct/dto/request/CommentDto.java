package uz.salikhdev.springprojct.dto.request;

import lombok.Builder;

@Builder
public record CommentDto(
        Long postId,
        String content
) {
}
