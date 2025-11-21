package uz.salikhdev.springprojct.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.springprojct.dto.request.PostCreateDto;
import uz.salikhdev.springprojct.dto.response.MessageResponse;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.service.PostService;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "Post", description = "APIs for managing posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody @Valid PostCreateDto dto,
            @AuthenticationPrincipal User user) {
        postService.create(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageResponse.success("Post created successfully"));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getById(id));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserPost(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getUserPosts(userId));
    }


}
