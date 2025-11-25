package rawson.prueba.application;

import rawson.prueba.application.dto.UsuarioRequestDTO;
import rawson.prueba.application.dto.UsuarioResponseDTO;
import rawson.prueba.application.usecase.*;
import rawson.prueba.domain.port.UsuarioRepositoryPort;
import rawson.prueba.domain.port.SecurityPort;
import rawson.prueba.domain.port.ValidacionPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SERVICIO DE APLICACIÓN (Application Service)
 * 
 * Arquitectura Hexagonal - Application Layer:
 * Orquesta los casos de uso e inyecta las dependencias de puertos.
 * 
 * Responsabilidades:
 * - Instanciar los casos de uso con sus dependencias
 * - Coordinar múltiples casos de uso si es necesario
 * - Traducir excepciones de dominio a excepciones de aplicación
 */
@Service
public class UsuarioApplicationService {
    
    private final UsuarioRepositoryPort usuarioRepository;
    private final SecurityPort securityPort;
    private final ValidacionPort validacionPort;
    
    private CrearUsuarioUseCase crearUsuarioUseCase;
    private ObtenerUsuariosUseCase obtenerUsuariosUseCase;
    private ActualizarUsuarioUseCase actualizarUsuarioUseCase;
    private EliminarUsuarioUseCase eliminarUsuarioUseCase;
    
    @Autowired
    public UsuarioApplicationService(
            UsuarioRepositoryPort usuarioRepository,
            SecurityPort securityPort,
            ValidacionPort validacionPort) {
        this.usuarioRepository = usuarioRepository;
        this.securityPort = securityPort;
        this.validacionPort = validacionPort;
        
        // Inicializar casos de uso
        this.crearUsuarioUseCase = new CrearUsuarioUseCase(usuarioRepository, securityPort, validacionPort);
        this.obtenerUsuariosUseCase = new ObtenerUsuariosUseCase(usuarioRepository);
        this.actualizarUsuarioUseCase = new ActualizarUsuarioUseCase(usuarioRepository, validacionPort);
        this.eliminarUsuarioUseCase = new EliminarUsuarioUseCase(usuarioRepository);
    }
    
    // Métodos públicos que delegan a casos de uso
    
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        return crearUsuarioUseCase.ejecutar(dto);
    }
    
    public List<UsuarioResponseDTO> obtenerTodos() {
        return obtenerUsuariosUseCase.obtenerTodos();
    }
    
    public UsuarioResponseDTO obtenerPorId(String id) {
        return obtenerUsuariosUseCase.obtenerPorId(id);
    }
    
    public UsuarioResponseDTO actualizarCompletamente(String id, UsuarioRequestDTO dto) {
        return actualizarUsuarioUseCase.actualizarCompletamente(id, dto);
    }
    
    public UsuarioResponseDTO actualizarParcialmente(String id, UsuarioRequestDTO dto) {
        return actualizarUsuarioUseCase.actualizarParcialmente(id, dto);
    }
    
    public void eliminarUsuario(String id) {
        eliminarUsuarioUseCase.ejecutar(id);
    }
}
