package rawson.prueba.interfaces.adapters.rest.controller;

import rawson.prueba.application.UsuarioApplicationService;
import rawson.prueba.application.dto.UsuarioRequestDTO;
import rawson.prueba.application.dto.UsuarioResponseDTO;
import rawson.prueba.application.dto.ErrorResponseDTO;
import rawson.prueba.domain.exception.UsuarioNoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ADAPTADOR DE ENTRADA (Input Adapter)
 * 
 * Arquitectura Hexagonal - Interfaces Layer:
 * El controlador REST traduce HTTP a llamadas de aplicación.
 * NO contiene lógica de negocio; solo orquesta y maneja excepciones.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioApplicationService applicationService;

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos del usuario",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRequestDTO dto) {
        try {
            logger.info("📝 POST /api/usuarios - Iniciando creación de usuario");
            logger.debug("   Correo: {}", dto.getCorreo());
            UsuarioResponseDTO usuario = applicationService.crearUsuario(dto);
            logger.info("✅ Usuario creado exitosamente. ID: {}", usuario.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (IllegalArgumentException e) {
            logger.warn("⚠️  Validación fallida: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO(e.getMessage()));
        } catch (Exception e) {
            logger.error("❌ Error al crear usuario: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO("Error interno del servidor: " + e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class)))
    })
    public ResponseEntity<?> obtenerTodosUsuarios() {
        try {
            logger.info("📋 GET /api/usuarios - Obteniendo todos los usuarios");
            var usuarios = applicationService.obtenerTodos();
            logger.info("✅ Se encontraron {} usuarios", usuarios.size());
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            logger.error("❌ Error al obtener usuarios: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO("Error interno del servidor: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por ID", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    public ResponseEntity<?> obtenerUsuario(@PathVariable String id) {
        try {
            logger.info("🔍 GET /api/usuarios/{} - Buscando usuario", id);
            UsuarioResponseDTO usuario = applicationService.obtenerPorId(id);
            logger.info("✅ Usuario encontrado: {}", usuario.getNombre());
            return ResponseEntity.ok(usuario);
        } catch (UsuarioNoEncontradoException e) {
            logger.warn("⚠️  Usuario no encontrado: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDTO(e.getMessage()));
        } catch (Exception e) {
            logger.error("❌ Error al obtener usuario: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO("Error interno del servidor: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario completamente", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos del usuario",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    public ResponseEntity<?> actualizarUsuario(@PathVariable String id, @RequestBody UsuarioRequestDTO dto) {
        try {
            logger.info("✏️  PUT /api/usuarios/{} - Actualizando usuario completamente", id);
            logger.debug("   Nuevos datos - Correo: {}, Nombre: {}", dto.getCorreo(), dto.getNombre());
            UsuarioResponseDTO usuario = applicationService.actualizarCompletamente(id, dto);
            logger.info("✅ Usuario actualizado exitosamente: {}", id);
            return ResponseEntity.ok(usuario);
        } catch (UsuarioNoEncontradoException e) {
            logger.warn("⚠️  Usuario no encontrado: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDTO(e.getMessage()));
        } catch (IllegalArgumentException e) {
            logger.warn("⚠️  Error en actualización: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO(e.getMessage()));
        } catch (Exception e) {
            logger.error("❌ Error al actualizar usuario: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO("Error interno del servidor: " + e.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar parcialmente un usuario", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado parcialmente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos del usuario",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    public ResponseEntity<?> actualizarParcialUsuario(@PathVariable String id, @RequestBody UsuarioRequestDTO dto) {
        try {
            logger.info("🔧 PATCH /api/usuarios/{} - Actualización parcial", id);
            logger.debug("   Campos a actualizar - Nombre: {}, Correo: {}, Teléfonos: {}", 
                    dto.getNombre() != null ? dto.getNombre() : "sin cambios",
                    dto.getCorreo() != null ? dto.getCorreo() : "sin cambios",
                    dto.getTelefonos() != null ? dto.getTelefonos().size() : 0);
            UsuarioResponseDTO usuario = applicationService.actualizarParcialmente(id, dto);
            logger.info("✅ Usuario parcialmente actualizado: {}", id);
            return ResponseEntity.ok(usuario);
        } catch (UsuarioNoEncontradoException e) {
            logger.warn("⚠️  Usuario no encontrado: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDTO(e.getMessage()));
        } catch (IllegalArgumentException e) {
            logger.warn("⚠️  Error en actualización parcial: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO(e.getMessage()));
        } catch (Exception e) {
            logger.error("❌ Error al actualizar parcialmente: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO("Error interno del servidor: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    public ResponseEntity<?> eliminarUsuario(@PathVariable String id) {
        try {
            logger.info("🗑️  DELETE /api/usuarios/{} - Eliminando usuario", id);
            applicationService.eliminarUsuario(id);
            logger.info("✅ Usuario eliminado exitosamente: {}", id);
            return ResponseEntity.ok(new ErrorResponseDTO("Usuario con ID " + id + " ha sido eliminado exitosamente"));
        } catch (UsuarioNoEncontradoException e) {
            logger.warn("⚠️  Usuario no encontrado para eliminar: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDTO(e.getMessage()));
        } catch (Exception e) {
            logger.error("❌ Error al eliminar usuario: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO("Error interno del servidor: " + e.getMessage()));
        }
    }
}
