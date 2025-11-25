package rawson.prueba.domain.port;

/**
 * PUERTO DE SALIDA (Output Port)
 * 
 * Arquitectura Hexagonal: Define el contrato para validaciones de datos.
 * La lógica de negocio NO conoce cómo se validan; solo que debe hacerse.
 */
public interface ValidacionPort {
    
    /**
     * Valida el formato de un correo electrónico.
     * @param correo el correo a validar
     * @return true si es válido, false en caso contrario
     */
    boolean validarCorreo(String correo);
    
    /**
     * Valida el formato de una contraseña.
     * @param contrasena la contraseña a validar
     * @return true si cumple requisitos, false en caso contrario
     */
    boolean validarContrasena(String contrasena);
    
    /**
     * Codifica una contraseña de forma segura.
     * @param contrasena la contraseña a codificar
     * @return la contraseña codificada
     */
    String encodePassword(String contrasena);
    
    /**
     * Valida que una contraseña coincida con su versión codificada.
     * @param rawPassword la contraseña en texto plano
     * @param encodedPassword la contraseña codificada
     * @return true si coinciden, false en caso contrario
     */
    boolean matchPassword(String rawPassword, String encodedPassword);
}
