package uz.salikhdev.springprojct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springprojct.entity.post.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByDeleteAtIsNullOrderByCreatedAtDesc();

    Post findByIdAndDeleteAtIsNull(Long id);

}
