package uz.salikhdev.springprojct.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Todo {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
}
