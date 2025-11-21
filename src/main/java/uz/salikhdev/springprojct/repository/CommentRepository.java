package uz.salikhdev.springprojct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springprojct.entity.comment.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost_IdAndIsActiveTrueAndDeletedAtIsNullOrderByCreatedAtDesc(Long postId);

    Optional<Comment> findByIdAndDeletedAtIsNull(Long id);

    @Modifying
    @Query("UPDATE comment c SET c.deletedAt = CURRENT_TIMESTAMP WHERE c.id = :id")
    void deleteById(Long id);

}
