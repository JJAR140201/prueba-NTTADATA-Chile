package rawson.prueba.infrastructure.security;

import rawson.prueba.domain.port.SecurityPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * ADAPTADOR DE SALIDA (Output Adapter)
 * 
 * Implementa el puerto SecurityPort usando JJWT.
 * Desacopla la lógica de tokens JWT de la implementación específica.
 */
@Component
public class SecurityAdapter implements SecurityPort {
    
    private String secret;
    
    @Value("${jwt.expiration:86400000}")
    private Long expiration;
    
    public SecurityAdapter(@Value("${jwt.secret:}") String configuredSecret) throws Exception {
        if (configuredSecret == null || configuredSecret.trim().isEmpty()) {
            this.secret = generateSecretKey();
        } else {
            this.secret = configuredSecret;
        }
    }
    
    private String generateSecretKey() throws Exception {
        // Generar clave de 512 bits para HS512
        javax.crypto.KeyGenerator keyGen = javax.crypto.KeyGenerator.getInstance("HmacSHA512");
        keyGen.init(512);
        return java.util.Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());
    }
    
    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    @Override
    public String generateToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    
    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public String getUserIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
