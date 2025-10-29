package uz.salikhdev.springprojct.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String token;

    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;

}
