package uz.salikhdev.springprojct.dto.response;

import lombok.Builder;

@Builder
public record TokenResponse(
        Long id,
        String token,
        String role
) {
}
