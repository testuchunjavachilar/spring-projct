package uz.salikhdev.springprojct.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserRegisterDto(
        @NotNull(message = "Username cannot be null")
        @NotBlank(message = "Username cannot be blank")
        @Min(value = 3, message = "Username must be at least 3 characters long")
        String username,
        @NotNull(message = "Password cannot be null")
        @NotBlank(message = "Password cannot be blank")
        @Min(value = 5, message = "Password must be at least 5 characters long")
        String password,
        @NotNull(message = "FirstName cannot be null")
        @NotBlank(message = "FirstName cannot be blank")
        String firstname,
        @NotNull(message = "Email cannot be null")
        @NotBlank(message = "Email cannot be blank")
        String email
) {
}
