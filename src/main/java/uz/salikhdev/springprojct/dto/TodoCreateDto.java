package uz.salikhdev.springprojct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TodoCreateDto {
    private String title;
    private String description;
}
