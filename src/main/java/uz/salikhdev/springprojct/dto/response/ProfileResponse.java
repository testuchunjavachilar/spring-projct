package uz.salikhdev.springprojct.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProfileResponse(
        Long id,
        @JsonProperty(value = "first_name")
        String firstName,
        @JsonProperty(value = "last_name")
        String lastName,
        String bio,
        String email,
        @JsonProperty(value = "created_at")
        LocalDateTime createdAt
) {
}
