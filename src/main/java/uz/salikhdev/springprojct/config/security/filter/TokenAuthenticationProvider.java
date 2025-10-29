package uz.salikhdev.springprojct.config.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import uz.salikhdev.springprojct.entity.User;
import uz.salikhdev.springprojct.excetion.InvalidTokenException;
import uz.salikhdev.springprojct.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid Token"));

        TokenAuthentication auth = new TokenAuthentication(token);
        auth.setUser(user);
        auth.setAuthenticated(true);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.isAssignableFrom(authentication);
    }
}
