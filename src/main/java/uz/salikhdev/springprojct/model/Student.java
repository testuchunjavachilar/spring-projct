package uz.salikhdev.springprojct.model;

import lombok.Data;

@Data
public class Student {
    private final long id;
    private final String name;
    private final int age;
    private final String email;
}
