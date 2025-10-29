package uz.salikhdev.springprojct.dto;

import lombok.Builder;

@Builder
public record TokenResponse(
        String token,
        String role
) {
}
