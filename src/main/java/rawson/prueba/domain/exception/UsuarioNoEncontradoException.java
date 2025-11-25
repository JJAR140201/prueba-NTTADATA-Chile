package rawson.prueba.domain.exception;

/**
 * Excepción del dominio para usuario no encontrado.
 * 
 * Arquitectura Hexagonal: Las excepciones del dominio comunican errores de negocio,
 * no errores técnicos. Esto permite que la lógica de negocio sea independiente
 * del framework utilizado.
 */
public class UsuarioNoEncontradoException extends RuntimeException {
    
    public UsuarioNoEncontradoException(String id) {
        super("Usuario no encontrado con ID: " + id);
    }
    
    public UsuarioNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
