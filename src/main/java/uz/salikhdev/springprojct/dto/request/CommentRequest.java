package uz.salikhdev.springprojct.dto.request;

import lombok.Builder;

@Builder
public record CommentRequest(
        Long postId,
        String content
) {
}
