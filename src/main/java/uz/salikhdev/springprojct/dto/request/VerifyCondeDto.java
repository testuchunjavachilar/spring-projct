package uz.salikhdev.springprojct.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record VerifyCondeDto(
        @Email(message = "Invalid email format")
        String email,
        //@Min(value = 4, message = "Code must be at least 4 characters long")
        String code,
        //@Min(value = 5, message = "Password must be at least 5 characters long")
        String newPassword
) {
}