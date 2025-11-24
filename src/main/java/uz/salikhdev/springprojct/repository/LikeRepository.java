package uz.salikhdev.springprojct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springprojct.entity.like.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Modifying
    @Query("DELETE FROM _like l WHERE l.id = ?1")
    void deleteByLikeId(Long postId);

}
