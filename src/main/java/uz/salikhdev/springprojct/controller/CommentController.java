package uz.salikhdev.springprojct.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.springprojct.dto.request.CommentRequest;
import uz.salikhdev.springprojct.dto.response.MessageResponse;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Tag(name = "Comment", description = "APIs for managing comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findByPostId(postId));
    }

    @PostMapping
    public ResponseEntity<?> addComment(
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal User user) {
        commentService.createComment(request, user);
        return ResponseEntity.ok(MessageResponse.success("Comment added successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, @AuthenticationPrincipal User user) {
        commentService.deleteComment(id, user);
        return ResponseEntity.ok(MessageResponse.success("Comment deleted successfully"));
    }

}
