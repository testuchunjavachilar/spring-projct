package uz.salikhdev.springprojct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springprojct.entity.post.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByDeleteAtIsNullOrderByCreatedAtDesc();

    Optional<Post> findByIdAndDeleteAtIsNull(Long id);

    List<Post> findAllByUser_IdAndDeleteAtIsNullOrderByCreatedAtDesc(Long userId);

}
