package rawson.prueba.application.usecase;

import rawson.prueba.domain.entity.Usuario;
import rawson.prueba.domain.port.UsuarioRepositoryPort;
import rawson.prueba.domain.exception.UsuarioNoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CASO DE USO: Eliminar Usuario
 */
public class EliminarUsuarioUseCase {
    
    private static final Logger logger = LoggerFactory.getLogger(EliminarUsuarioUseCase.class);
    private final UsuarioRepositoryPort usuarioRepository;
    
    public EliminarUsuarioUseCase(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public void ejecutar(String id) {
        logger.info("▶️  Caso de uso: Eliminar Usuario - {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
        usuarioRepository.delete(usuario);
        logger.info("✅ Usuario eliminado");
    }
}
