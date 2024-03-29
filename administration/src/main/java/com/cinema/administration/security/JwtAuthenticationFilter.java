package com.cinema.administration.security;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nullable;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter implements GrpcAuthenticationReader {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            if (!jwtToken.isBlank()) {
                try {
                    String cinemaHallEmailAddress = jwtUtil.getJwtTokenSubject(jwtToken);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(cinemaHallEmailAddress);

                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } catch (JwtException jwtException) {
                    log.error("Cannot authenticate user: {}", jwtException.getMessage());
                    throw new InsufficientAuthenticationException("JWT Authentication Failed", jwtException);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    @Nullable
    @Override
    public Authentication readAuthentication(ServerCall<?, ?> call, Metadata headers) throws AuthenticationException {
        return null;
    }

}
