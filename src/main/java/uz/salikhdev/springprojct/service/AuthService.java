package uz.salikhdev.springprojct.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.dto.response.TokenResponse;
import uz.salikhdev.springprojct.dto.request.UserLoginDto;
import uz.salikhdev.springprojct.dto.request.UserRegisterDto;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.entity.user.UserRole;
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

        if (userRepository.existsByEmail(dto.username())) {
            throw new AlreadyExistException("User with username " + dto.username() + " already exist");
        }

        User user = User.builder()
                .username(dto.username())
                .password(encoder.encode(dto.password()))
                .firstname(dto.firstname())
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
    }

    public TokenResponse login(UserLoginDto dto) {

        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new NotFoundException("User not with email: " + dto.username()));

        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password or email");
        }

        String token = UUID.randomUUID().toString();

        user.setToken(token);
        userRepository.save(user);

        return TokenResponse.builder().token(token).role(user.getRole().name()).build();
    }

    public void logOut(User user) {
        user.setToken(null);
        userRepository.save(user);
    }
}
