package uz.salikhdev.springprojct.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record RestartPasswordDto(
        @Email(message = "Invalid email format")
        String email,
        String username
) { }
