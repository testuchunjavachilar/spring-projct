package uz.salikhdev.springprojct.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Builder
@AllArgsConstructor
public class Student {
    private long id;
    private String name;
    private int age;
    private String email;
}
