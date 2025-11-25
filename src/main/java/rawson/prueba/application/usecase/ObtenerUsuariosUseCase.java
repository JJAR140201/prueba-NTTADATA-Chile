package rawson.prueba.application.usecase;

import rawson.prueba.application.dto.UsuarioResponseDTO;
import rawson.prueba.application.dto.TelefonoDTO;
import rawson.prueba.domain.entity.Usuario;
import rawson.prueba.domain.port.UsuarioRepositoryPort;
import rawson.prueba.domain.exception.UsuarioNoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CASO DE USO: Obtener Usuarios
 */
public class ObtenerUsuariosUseCase {
    
    private static final Logger logger = LoggerFactory.getLogger(ObtenerUsuariosUseCase.class);
    private final UsuarioRepositoryPort usuarioRepository;
    
    public ObtenerUsuariosUseCase(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public List<UsuarioResponseDTO> obtenerTodos() {
        logger.info("▶️  Caso de uso: Obtener Todos los Usuarios");
        List<Usuario> usuarios = usuarioRepository.findAll();
        logger.info("✅ Total de usuarios encontrados: {}", usuarios.size());
        return usuarios.stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }
    
    public UsuarioResponseDTO obtenerPorId(String id) {
        logger.info("▶️  Caso de uso: Obtener Usuario por ID - {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
        logger.info("✅ Usuario encontrado: {}", usuario.getNombre());
        return convertirAResponseDTO(usuario);
    }
    
    private UsuarioResponseDTO convertirAResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setCreado(usuario.getCreado());
        dto.setModificado(usuario.getModificado());
        dto.setUltimoLogin(usuario.getUltimoLogin());
        dto.setToken(usuario.getToken());
        dto.setActivo(usuario.getActivo());
        
        if (usuario.getTelefonos() != null) {
            dto.setTelefonos(usuario.getTelefonos().stream().map(t -> {
                TelefonoDTO tel = new TelefonoDTO();
                tel.setNumero(t.getNumero());
                tel.setCodigoCiudad(t.getCodigoCiudad());
                tel.setCodigoPais(t.getCodigoPais());
                return tel;
            }).collect(Collectors.toList()));
        }
        
        return dto;
    }
}
