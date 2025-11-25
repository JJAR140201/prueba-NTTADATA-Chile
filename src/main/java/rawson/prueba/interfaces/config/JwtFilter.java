package rawson.prueba.interfaces.config;

import rawson.prueba.domain.port.SecurityPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * FILTRO JWT
 * 
 * Interceptor que valida tokens JWT en cada solicitud.
 * Si el token es válido, establece la autenticación en el contexto de Spring Security.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final SecurityPort securityPort;
    
    public JwtFilter(SecurityPort securityPort) {
        this.securityPort = securityPort;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getTokenFromRequest(request);
            
            if (token != null && securityPort.validateToken(token)) {
                String userId = securityPort.getUserIdFromToken(token);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                Authentication auth = new UsernamePasswordAuthenticationToken(userId, null, authorities);
                org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(auth);
                logger.debug("✅ Token válido para usuario: {}", userId);
            }
        } catch (Exception e) {
            logger.debug("❌ Error validando token: {}", e.getMessage());
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String getTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
