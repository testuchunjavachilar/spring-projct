package uz.salikhdev.springprojct.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.salikhdev.springprojct.dto.request.ProfileEditDto;
import uz.salikhdev.springprojct.dto.response.MessageResponse;
import uz.salikhdev.springprojct.dto.response.ProfileResponse;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.service.ResourceService;
import uz.salikhdev.springprojct.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UserService userService;
    private final ResourceService resourceService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long id) {
        ProfileResponse profile = userService.getProfile(id);
        return ResponseEntity.ok(profile);
    }

    @PatchMapping("/profile/{id}")
    public ResponseEntity<?> updateUserProfile(
            @PathVariable Long id,
            @RequestBody ProfileEditDto dto,
            @AuthenticationPrincipal User user) {
        userService.updateProfile(id, dto, user);
        return ResponseEntity.ok(MessageResponse.success("Profile updated successfully"));
    }

    @PostMapping("/active/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> blockUser(
            @PathVariable Long id, @RequestParam Boolean active) {
        userService.statusUpdate(id, active);
        return ResponseEntity.ok(MessageResponse.success("User active is update successfully"));
    }

    @PostMapping(value = "/{id}/avatar-upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadAvatar(
            @PathVariable Long id,
            @RequestPart MultipartFile file) {
        resourceService.uploadUserAvatar(id, file);
        return ResponseEntity.ok(MessageResponse.success("Avatar upload successfully"));
    }


}
