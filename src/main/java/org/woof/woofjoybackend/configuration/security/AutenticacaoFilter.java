package org.woof.woofjoybackend.configuration.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.woof.woofjoybackend.configuration.security.jwt.GerenciadorTokenJwt;
import org.woof.woofjoybackend.service.AutenticacaoService;

import java.io.IOException;
import java.util.Objects;

public class AutenticacaoFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutenticacaoFilter.class);
    private final AutenticacaoService autenticacaoService;
    private final GerenciadorTokenJwt jwtTokenManager;

    public AutenticacaoFilter(AutenticacaoService autenticacaoService, GerenciadorTokenJwt jwtTokenManager) {
        this.autenticacaoService = autenticacaoService;
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = null;
        String jwtToken = null;

        String requestTokenHeader = request.getHeader("Authorization");

        if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
                username = jwtTokenManager.getUsernameFromToken(jwtToken);
            } catch (ExpiredJwtException exception) {
                LOGGER.info("[FALHA AUTENTICACAO] - Token expirado, usuario: {} - {}",
                        exception.getClaims().getSubject(), exception.getMessage());

                LOGGER.trace("[FALHA AUTENTICACAO] - stack trace: %s", exception);

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (Exception erro) {
                LOGGER.info("[FALHA AUTENTICACAO] - Token não autorizado");

                LOGGER.trace("[FALHA AUTENTICACAO] - stack trace: %s", erro);

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try {
                addUsernameInContext(request, username, jwtToken);
            } catch (UsernameNotFoundException usernameNotFoundException) {
                LOGGER.info("[FALHA AUTENTICACAO] - Usuário não encontrado");

                LOGGER.trace("[FALHA AUTENTICACAO] - stack trace: %s", usernameNotFoundException);

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        filterChain.doFilter(request, response);
    }
    //	MUDO AQUI
    private void addUsernameInContext(HttpServletRequest request, String username, String jwtToken) throws UsernameNotFoundException {
        UserDetails userDetails = autenticacaoService.loadUserByUsername(username);

        if (jwtTokenManager.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
                    (
                            userDetails, null, userDetails.getAuthorities()
                    );

            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
