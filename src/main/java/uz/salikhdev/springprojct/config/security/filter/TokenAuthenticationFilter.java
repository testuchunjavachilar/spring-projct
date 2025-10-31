package uz.salikhdev.springprojct.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.salikhdev.springprojct.dto.response.MessageResponse;
import uz.salikhdev.springprojct.excetion.InvalidTokenException;

import java.io.IOException;
import java.time.LocalDateTime;


@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws IOException, ServletException {

        final String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            try {
                String token = header.substring(7);

                Authentication authRequest = new TokenAuthentication(token);
                Authentication authResult = authenticationManager.authenticate(authRequest);

                if (authResult.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authResult);
                }
            } catch (InvalidTokenException e) {
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                MessageResponse body = MessageResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(false)
                        .message("Unauthorized: " + e.getMessage())
                        .build();
                response.getWriter().write(objectMapper.writeValueAsString(body));
                return;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(request, response);
    }
}
