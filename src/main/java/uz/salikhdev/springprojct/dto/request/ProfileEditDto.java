package uz.salikhdev.springprojct.dto.request;

import lombok.Builder;

@Builder
public record ProfileEditDto(
        String firstName,
        String lastName,
        String bio
) {
}
