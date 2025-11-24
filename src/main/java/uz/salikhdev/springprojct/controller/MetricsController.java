package uz.salikhdev.springprojct.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.service.MetricsService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Mentrics", description = "Endpoints for managing likes")
public class MetricsController {

    private final MetricsService likeService;

    @PostMapping("/likes/post/{postId}")
    public ResponseEntity<?> likePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal User user
    ) {
        likeService.likePost(postId, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unlikes/post/{postId}")
    public ResponseEntity<?> dislikePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal User user
    ) {
        likeService.unlikePost(postId, user);
        return ResponseEntity.ok().build();
    }

}
