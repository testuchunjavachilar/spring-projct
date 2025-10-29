package uz.salikhdev.springprojct.dto;

import lombok.Builder;

@Builder
public record UserLoginDto(
        String email,
        String password
) {
}
