package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.entity.like.Like;
import uz.salikhdev.springprojct.entity.post.Post;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.excetion.BadRequestException;
import uz.salikhdev.springprojct.excetion.NotFoundException;
import uz.salikhdev.springprojct.repository.LikeRepository;
import uz.salikhdev.springprojct.repository.PostRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricsService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public void likePost(Long postId, User user) {
        Post post = postRepository.findByIdAndDeleteAtIsNull(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        post.getLikes().stream()
                .filter(like -> like.getUser().getId().equals(user.getId()))
                .findFirst()
                .ifPresent(like -> {
                    throw new BadRequestException("You have already liked this post");
                });

        Like like = Like.builder()
                .post(post)
                .user(user)
                .build();

        likeRepository.save(like);

    }

    public void unlikePost(Long postId, User user) {
        Post post = postRepository.findByIdAndDeleteAtIsNull(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        Long likeId = post.getLikes().stream()
                .filter(like -> like.getUser().getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("You have not liked this post"))
                .getId();


        likeRepository.deleteByLikeId(likeId);
    }
}
