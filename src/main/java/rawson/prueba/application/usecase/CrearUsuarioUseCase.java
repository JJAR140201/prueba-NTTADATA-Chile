package rawson.prueba.application.usecase;

import rawson.prueba.application.dto.UsuarioRequestDTO;
import rawson.prueba.application.dto.UsuarioResponseDTO;
import rawson.prueba.domain.entity.Usuario;
import rawson.prueba.domain.entity.Telefono;
import rawson.prueba.domain.port.UsuarioRepositoryPort;
import rawson.prueba.domain.port.SecurityPort;
import rawson.prueba.domain.port.ValidacionPort;
import rawson.prueba.domain.exception.UsuarioNoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CASO DE USO: Crear Usuario
 * 
 * Arquitectura Hexagonal - Application Layer:
 * Los casos de uso coordinan la lógica de negocio usando los puertos del dominio.
 * Son agnósticos de la tecnología (no conocen Spring, BD, HTTP, etc.).
 */
public class CrearUsuarioUseCase {
    
    private static final Logger logger = LoggerFactory.getLogger(CrearUsuarioUseCase.class);
    
    private final UsuarioRepositoryPort usuarioRepository;
    private final SecurityPort securityPort;
    private final ValidacionPort validacionPort;
    
    public CrearUsuarioUseCase(
            UsuarioRepositoryPort usuarioRepository,
            SecurityPort securityPort,
            ValidacionPort validacionPort) {
        this.usuarioRepository = usuarioRepository;
        this.securityPort = securityPort;
        this.validacionPort = validacionPort;
    }
    
    /**
     * Ejecuta el caso de uso de crear usuario.
     */
    public UsuarioResponseDTO ejecutar(UsuarioRequestDTO dto) {
        logger.info("▶️  Caso de uso: Crear Usuario - {}", dto.getCorreo());
        
        // Validar que los datos requeridos no sean nulos
        if (dto.getCorreo() == null || dto.getCorreo().isEmpty()) {
            logger.warn("❌ Correo requerido");
            throw new IllegalArgumentException("El correo es requerido");
        }
        
        if (dto.getContrasena() == null || dto.getContrasena().isEmpty()) {
            logger.warn("❌ Contraseña requerida");
            throw new IllegalArgumentException("La contraseña es requerida");
        }
        
        if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
            logger.warn("❌ Nombre requerido");
            throw new IllegalArgumentException("El nombre es requerido");
        }
        
        // Validar correo
        logger.debug("   Validando correo...");
        if (!validacionPort.validarCorreo(dto.getCorreo())) {
            logger.warn("❌ Correo inválido: {}", dto.getCorreo());
            throw new IllegalArgumentException("El formato del correo no es válido");
        }
        
        // Validar contraseña
        logger.debug("   Validando contraseña...");
        if (!validacionPort.validarContrasena(dto.getContrasena())) {
            logger.warn("❌ Contraseña inválida");
            throw new IllegalArgumentException("El formato de la contraseña no es válido");
        }
        
        // Verificar si el correo ya existe
        logger.debug("   Verificando unicidad de correo...");
        if (usuarioRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            logger.warn("❌ El correo ya está registrado: {}", dto.getCorreo());
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        
        // Crear nuevo usuario
        logger.debug("   Creando entidad Usuario...");
        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.getCorreo());
        usuario.setNombre(dto.getNombre());
        usuario.setContrasena(validacionPort.encodePassword(dto.getContrasena()));
        usuario.setActivo(true);
        usuario.setCreado(LocalDateTime.now());
        usuario.setModificado(LocalDateTime.now());
        usuario.setUltimoLogin(LocalDateTime.now());
        
        // Procesar teléfonos
        if (dto.getTelefonos() != null && !dto.getTelefonos().isEmpty()) {
            logger.debug("   Procesando {} teléfono(s)...", dto.getTelefonos().size());
            List<Telefono> telefonos = dto.getTelefonos().stream().map(t -> {
                Telefono tel = new Telefono();
                tel.setNumero(t.getNumero());
                tel.setCodigoCiudad(t.getCodigoCiudad());
                tel.setCodigoPais(t.getCodigoPais());
                return tel;
            }).collect(Collectors.toList());
            usuario.setTelefonos(telefonos);
        }
        
        // Guardar usuario (se genera ID en @PrePersist)
        logger.info("   💾 Guardando usuario en BD...");
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        logger.info("   ✅ Usuario guardado. ID: {}", usuarioGuardado.getId());
        
        // Generar token JWT
        logger.info("   🔑 Generando token JWT...");
        String token = securityPort.generateToken(usuarioGuardado.getId());
        usuarioGuardado.setToken(token);
        
        // Actualizar usuario con token
        logger.info("   💾 Actualizando usuario con token...");
        usuarioGuardado = usuarioRepository.save(usuarioGuardado);
        logger.info("✅ Usuario creado exitosamente");
        
        return convertirAResponseDTO(usuarioGuardado);
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
                var tel = new rawson.prueba.application.dto.TelefonoDTO();
                tel.setNumero(t.getNumero());
                tel.setCodigoCiudad(t.getCodigoCiudad());
                tel.setCodigoPais(t.getCodigoPais());
                return tel;
            }).collect(Collectors.toList()));
        }
        
        return dto;
    }
}
