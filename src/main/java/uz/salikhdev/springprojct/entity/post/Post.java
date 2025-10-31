package uz.salikhdev.springprojct.entity.post;

import jakarta.persistence.*;
import lombok.*;
import uz.salikhdev.springprojct.entity.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Column(name = "resource_url")
    private String resourceUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "delete_at")
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;
}
