package uz.salikhdev.springprojct.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.salikhdev.springprojct.dto.request.RestartPasswordDto;
import uz.salikhdev.springprojct.dto.request.UserLoginDto;
import uz.salikhdev.springprojct.dto.request.UserRegisterDto;
import uz.salikhdev.springprojct.dto.request.VerifyCondeDto;
import uz.salikhdev.springprojct.dto.response.TokenResponse;
import uz.salikhdev.springprojct.entity.user.User;
import uz.salikhdev.springprojct.entity.user.UserRole;
import uz.salikhdev.springprojct.excetion.AlreadyExistException;
import uz.salikhdev.springprojct.excetion.BadCredentialsException;
import uz.salikhdev.springprojct.excetion.NotFoundException;
import uz.salikhdev.springprojct.repository.UserRepository;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final Random random;
    private final StringRedisTemplate redis;

    public void register(UserRegisterDto dto) {

        if (userRepository.existsByEmail(dto.username())) {
            throw new AlreadyExistException("User with username " + dto.username() + " already exist");
        }

        User user = User.builder()
                .username(dto.username())
                .password(encoder.encode(dto.password()))
                .firstname(dto.firstname())
                .isActive(true)
                .email(dto.email())
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
    }

    public TokenResponse login(UserLoginDto dto) {

        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new NotFoundException("User not with email: " + dto.username()));

        if (user.getIsActive() == false) {
            throw new BadCredentialsException("User is not active");
        }

        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password or email");
        }

        String token = UUID.randomUUID().toString();

        user.setToken(token);
        User saveUser = userRepository.save(user);

        return TokenResponse.builder().token(token).id(saveUser.getId()).role(user.getRole().name()).build();
    }

    public void logOut(User user) {
        user.setToken(null);
        userRepository.save(user);
    }

    public String restartPassword(RestartPasswordDto dto) {

        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new NotFoundException("User not found with username: " + dto.username()));

        if (!user.getEmail().equals(dto.email())) {
            throw new BadCredentialsException("Email does not match");
        }

        String code = String.valueOf(random.nextInt(1000, 9999));
        // TODO : SEND code TO EMAIL
        redis.opsForValue().set(dto.email(), code, 2, TimeUnit.MINUTES);
        return code;
    }

    public void verifyCode(@Valid VerifyCondeDto dto) {
        String ramCode = redis.opsForValue().get(dto.email());

        if (ramCode == null || !ramCode.equals(dto.code())) {
            throw new BadCredentialsException("Invalid code");
        }
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + dto.email()));
        user.setPassword(encoder.encode(dto.newPassword()));
        userRepository.save(user);
        redis.delete(dto.email());
    }
}
