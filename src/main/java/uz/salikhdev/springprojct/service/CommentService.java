package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.dto.request.CommentRequest;
import uz.salikhdev.springprojct.dto.response.CommentResponse;
import uz.salikhdev.springprojct.entity.comment.Comment;
import uz.salikhdev.springprojct.entity.post.Post;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.excetion.BadRequestException;
import uz.salikhdev.springprojct.excetion.NotFoundException;
import uz.salikhdev.springprojct.mapper.CommentMapper;
import uz.salikhdev.springprojct.repository.CommentRepository;
import uz.salikhdev.springprojct.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;

    public List<CommentResponse> findByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));
        List<Comment> all = commentRepository.findAllByPost_IdAndIsActiveTrueAndDeletedAtIsNullOrderByCreatedAtDesc(post.getId());
        return commentMapper.toResponse(all);
    }

    public void createComment(CommentRequest request, User user) {
        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new NotFoundException("Post not found"));

        Comment comment = Comment.builder()
                .post(post)
                .author(user)
                .content(request.content())
                .isActive(true)
                .build();

        commentRepository.save(comment);
    }

    public void deleteComment(Long id, User user) {
        Comment comment = commentRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        // TODO CHECK DELETE

        if (!comment.getAuthor().getId().equals(user.getId())) {
            throw new BadRequestException("You are not authorized to delete this comment");
        }

        commentRepository.deleteById(comment.getId());
    }
}
