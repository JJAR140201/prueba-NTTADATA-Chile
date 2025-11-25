package rawson.prueba.infrastructure.util;

import rawson.prueba.domain.port.ValidacionPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * ADAPTADOR DE SALIDA (Output Adapter)
 * 
 * Implementa el puerto ValidacionPort.
 * Proporciona validaciones de correo, contraseña y encoding BCrypt.
 */
@Component
public class ValidacionAdapter implements ValidacionPort {
    
    @Value("${validacion.correo.regex:^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2}$}")
    private String correoRegex;
    
    @Value("${validacion.contrasena.regex:^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$}")
    private String contrasenaRegex;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public boolean validarCorreo(String correo) {
        if (correo == null || correo.isEmpty()) {
            return false;
        }
        return Pattern.matches(correoRegex, correo);
    }
    
    @Override
    public boolean validarContrasena(String contrasena) {
        if (contrasena == null || contrasena.isEmpty()) {
            return false;
        }
        return Pattern.matches(contrasenaRegex, contrasena);
    }
    
    @Override
    public String encodePassword(String contrasena) {
        return passwordEncoder.encode(contrasena);
    }
    
    @Override
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
