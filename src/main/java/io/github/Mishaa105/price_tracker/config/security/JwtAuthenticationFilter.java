package io.github.Mishaa105.price_tracker.config.security;

import io.github.Mishaa105.price_tracker.enums.auth.Role;
import io.github.Mishaa105.price_tracker.service.auth.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.List;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException
    {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        UUID userId;
        Role userRole;

        try
        {
            Claims claims = jwtService.validateToken(token);

            userId = UUID.fromString(claims.getSubject());
            userRole = Role.valueOf(claims.get("role", String.class));
        }
        catch (JwtException | IllegalArgumentException e)
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken
                (userId, null, List.of(new SimpleGrantedAuthority("ROLE_" + userRole.name())));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }
}
