package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.dto.UserLoginDto;
import uz.salikhdev.springprojct.dto.UserRegisterDto;
import uz.salikhdev.springprojct.entity.User;
import uz.salikhdev.springprojct.excetion.AlreadyExistException;
import uz.salikhdev.springprojct.excetion.BadCredentialsException;
import uz.salikhdev.springprojct.excetion.NotFoundException;
import uz.salikhdev.springprojct.repository.UserRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void register(UserRegisterDto dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new AlreadyExistException("User with email " + dto.email() + " already exist");
        }

        User user = User.builder()
                .email(dto.email())
                .password(encoder.encode(dto.password()))
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .build();

        userRepository.save(user);
    }

    public String login(UserLoginDto dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new NotFoundException("User not with email: " + dto.email()));


        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password or email");
        }

        String token = UUID.randomUUID().toString();

        user.setToken(token);
        userRepository.save(user);

        return token;
    }
}
