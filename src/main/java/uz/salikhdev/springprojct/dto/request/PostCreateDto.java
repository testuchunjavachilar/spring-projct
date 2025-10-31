package uz.salikhdev.springprojct.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PostCreateDto(
        @NotNull(message = "Title bo'sh bo'lmasligi kerak")
        @NotBlank(message = "Title bo'sh bo'lmasligi kerak")
        String title,
        String description,
        @JsonProperty("resource_url")
        @NotNull(message = "resource url bo'sh bo'lmasligi kerak")
        String resourceUrl
) {
}
