package uz.salikhdev.springprojct.dto.request;

import lombok.Builder;

@Builder
public record UserRegisterDto(
        String username,
        String password,
        String firstname
) {
}
