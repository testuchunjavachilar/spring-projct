package uz.salikhdev.springprojct.dto;

import lombok.Builder;

@Builder
public record UserRegisterDto(
        String email,
        String password,
        String firstname,
        String lastname
) {
}
