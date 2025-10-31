package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.dto.request.PostCreateDto;
import uz.salikhdev.springprojct.entity.post.Post;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void create(PostCreateDto dto, User user) {
        Post post = Post.builder()
                .title(dto.title())
                .description(dto.description())
                .resourceUrl(dto.resourceUrl())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        postRepository.save(post);
    }

    public List<Post> getAll() {
        return postRepository.findAllByDeleteAtIsNullOrderByCreatedAtDesc();
    }


    public Post getById(Long id) {
        return postRepository.findByIdAndDeleteAtIsNull(id);
    }
}
