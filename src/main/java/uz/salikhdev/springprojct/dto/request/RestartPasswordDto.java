package uz.salikhdev.springprojct.dto.request;

import lombok.Builder;

@Builder
public record RestartPasswordDto(
        String email,
        String username
) { }
