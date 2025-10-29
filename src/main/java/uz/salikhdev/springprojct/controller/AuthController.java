package uz.salikhdev.springprojct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.springprojct.dto.MessageResponse;
import uz.salikhdev.springprojct.dto.TokenResponse;
import uz.salikhdev.springprojct.dto.UserLoginDto;
import uz.salikhdev.springprojct.dto.UserRegisterDto;
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
        String token = authService.login(dto);
        return ResponseEntity.status(200).body(
                TokenResponse.builder()
                        .token(token)
                        .role(null)
                        .build()
        );
    }


}
