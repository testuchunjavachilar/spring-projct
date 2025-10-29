package uz.salikhdev.springprojct.config.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import uz.salikhdev.springprojct.entity.User;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class TokenAuthentication implements Authentication {

    private final String token;
    private boolean authenticated;
    private User user;

    public TokenAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        if (user != null) {
            return user.getEmail();
        } else {
            return null;
        }
    }
}

