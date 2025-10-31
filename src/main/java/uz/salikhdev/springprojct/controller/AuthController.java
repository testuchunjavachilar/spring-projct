package uz.salikhdev.springprojct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.springprojct.dto.response.MessageResponse;
import uz.salikhdev.springprojct.dto.response.TokenResponse;
import uz.salikhdev.springprojct.dto.request.UserLoginDto;
import uz.salikhdev.springprojct.dto.request.UserRegisterDto;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {
        authService.register(dto);
        return ResponseEntity.status(201).body(MessageResponse.success("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto dto) {
        TokenResponse response = authService.login(dto);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal User user) {
        authService.logOut(user);
        return ResponseEntity.status(200).body(MessageResponse.success("Logout successfully"));
    }

}
