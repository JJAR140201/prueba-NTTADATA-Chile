package rawson.prueba.application.usecase;

import rawson.prueba.application.dto.UsuarioRequestDTO;
import rawson.prueba.application.dto.UsuarioResponseDTO;
import rawson.prueba.application.dto.TelefonoDTO;
import rawson.prueba.domain.entity.Usuario;
import rawson.prueba.domain.entity.Telefono;
import rawson.prueba.domain.port.UsuarioRepositoryPort;
import rawson.prueba.domain.port.ValidacionPort;
import rawson.prueba.domain.exception.UsuarioNoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CASO DE USO: Actualizar Usuario
 */
public class ActualizarUsuarioUseCase {
    
    private static final Logger logger = LoggerFactory.getLogger(ActualizarUsuarioUseCase.class);
    private final UsuarioRepositoryPort usuarioRepository;
    private final ValidacionPort validacionPort;
    
    public ActualizarUsuarioUseCase(
            UsuarioRepositoryPort usuarioRepository,
            ValidacionPort validacionPort) {
        this.usuarioRepository = usuarioRepository;
        this.validacionPort = validacionPort;
    }
    
    public UsuarioResponseDTO actualizarCompletamente(String id, UsuarioRequestDTO dto) {
        logger.info("▶️  Caso de uso: Actualizar Usuario Completamente - {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
        
        actualizarCampos(usuario, dto);
        Usuario actualizado = usuarioRepository.save(usuario);
        logger.info("✅ Usuario actualizado");
        return convertirAResponseDTO(actualizado);
    }
    
    public UsuarioResponseDTO actualizarParcialmente(String id, UsuarioRequestDTO dto) {
        logger.info("▶️  Caso de uso: Actualizar Usuario Parcialmente - {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
        
        if (dto.getNombre() != null && !dto.getNombre().isEmpty()) {
            usuario.setNombre(dto.getNombre());
        }
        if (dto.getCorreo() != null && !dto.getCorreo().isEmpty()) {
            validarCambioCorreo(usuario, dto.getCorreo());
            usuario.setCorreo(dto.getCorreo());
        }
        if (dto.getContrasena() != null && !dto.getContrasena().isEmpty()) {
            if (!validacionPort.validarContrasena(dto.getContrasena())) {
                throw new IllegalArgumentException("Contraseña inválida");
            }
            usuario.setContrasena(validacionPort.encodePassword(dto.getContrasena()));
        }
        if (dto.getTelefonos() != null && !dto.getTelefonos().isEmpty()) {
            actualizarTelefonos(usuario, dto);
        }
        
        Usuario actualizado = usuarioRepository.save(usuario);
        logger.info("✅ Usuario actualizado parcialmente");
        return convertirAResponseDTO(actualizado);
    }
    
    private void actualizarCampos(Usuario usuario, UsuarioRequestDTO dto) {
        usuario.setNombre(dto.getNombre());
        validarCambioCorreo(usuario, dto.getCorreo());
        usuario.setCorreo(dto.getCorreo());
        
        if (!validacionPort.validarContrasena(dto.getContrasena())) {
            throw new IllegalArgumentException("Contraseña inválida");
        }
        usuario.setContrasena(validacionPort.encodePassword(dto.getContrasena()));
        actualizarTelefonos(usuario, dto);
    }
    
    private void validarCambioCorreo(Usuario usuario, String nuevoCorreo) {
        if (!usuario.getCorreo().equals(nuevoCorreo)) {
            if (!validacionPort.validarCorreo(nuevoCorreo)) {
                throw new IllegalArgumentException("Correo inválido");
            }
            if (usuarioRepository.findByCorreo(nuevoCorreo).isPresent()) {
                throw new IllegalArgumentException("Correo ya registrado");
            }
        }
    }
    
    private void actualizarTelefonos(Usuario usuario, UsuarioRequestDTO dto) {
        List<Telefono> telefonos = dto.getTelefonos().stream().map(t -> {
            Telefono tel = new Telefono();
            tel.setNumero(t.getNumero());
            tel.setCodigoCiudad(t.getCodigoCiudad());
            tel.setCodigoPais(t.getCodigoPais());
            return tel;
        }).collect(Collectors.toList());
        usuario.setTelefonos(telefonos);
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
