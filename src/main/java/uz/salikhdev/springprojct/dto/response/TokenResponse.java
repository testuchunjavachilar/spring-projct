package uz.salikhdev.springprojct.dto.response;

import lombok.Builder;

@Builder
public record TokenResponse(
        String token,
        String role
) {
}
