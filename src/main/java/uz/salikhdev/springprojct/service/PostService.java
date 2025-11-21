package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.dto.request.PostCreateDto;
import uz.salikhdev.springprojct.dto.response.PostResponse;
import uz.salikhdev.springprojct.entity.post.Post;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.excetion.NotFoundException;
import uz.salikhdev.springprojct.mapper.PostMapper;
import uz.salikhdev.springprojct.repository.PostRepository;
import uz.salikhdev.springprojct.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

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

    public List<PostResponse> getAll() {
        List<Post> all = postRepository.findAllByDeleteAtIsNullOrderByCreatedAtDesc();
        return postMapper.toResponse(all);
    }

    public Post getById(Long id) {
        return postRepository.findByIdAndDeleteAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }

    public List<PostResponse> getUserPosts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<Post> posts = postRepository.findAllByUser_IdAndDeleteAtIsNullOrderByCreatedAtDesc(user.getId());

        return postMapper.toResponse(posts);
    }
}
