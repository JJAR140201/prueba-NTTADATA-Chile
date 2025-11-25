package rawson.prueba.domain.port;

/**
 * PUERTO DE SALIDA (Output Port)
 * 
 * Arquitectura Hexagonal: Define el contrato para operaciones de JWT y seguridad.
 * Desacopla la lógica de negocio de la implementación específica de tokens.
 * 
 * ¿Por qué?
 * - La aplicación usa esta interfaz para generar tokens
 * - El adaptador de infraestructura implementa la lógica real de JWT
 * - Permite cambiar la estrategia de seguridad sin afectar el dominio
 */
public interface SecurityPort {
    
    /**
     * Genera un token JWT para un usuario.
     * @param userId el ID del usuario
     * @return el token JWT generado
     */
    String generateToken(String userId);
    
    /**
     * Valida un token JWT.
     * @param token el token a validar
     * @return true si es válido, false en caso contrario
     */
    boolean validateToken(String token);
    
    /**
     * Extrae el ID del usuario desde el token.
     * @param token el token JWT
     * @return el ID del usuario
     */
    String getUserIdFromToken(String token);
}
