package uz.salikhdev.springprojct.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private String description;
    private LocalDateTime createAt;
}
