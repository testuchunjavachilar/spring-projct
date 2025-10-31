package uz.salikhdev.springprojct.dto.request;

import lombok.Builder;

@Builder
public record UserLoginDto(
        String username,
        String password
) {
}
