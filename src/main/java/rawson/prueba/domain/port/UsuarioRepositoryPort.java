package rawson.prueba.domain.port;

import rawson.prueba.domain.entity.Usuario;
import java.util.List;
import java.util.Optional;

/**
 * PUERTO DE SALIDA (Output Port)
 * 
 * Arquitectura Hexagonal: Define el contrato que cualquier adaptador de persistencia debe cumplir.
 * Esta interfaz desacopla la lógica de negocio de la implementación específica de la BD.
 * 
 * ¿Por qué es un PUERTO?
 * - Es una interfaz que define el contrato entre dominios
 * - La aplicación depende de esta abstracción, no de la implementación concreta
 * - Permite cambiar la BD sin modificar la lógica de negocio
 * 
 * ¿Por qué es de SALIDA?
 * - La aplicación llama a este puerto (sale del dominio)
 * - El adaptador implementa este puerto (entra en infraestructura)
 * - Permite inyección de dependencias y testing sin BD real
 */
public interface UsuarioRepositoryPort {
    
    /**
     * Guarda un nuevo usuario o actualiza uno existente.
     * @param usuario el usuario a guardar
     * @return el usuario guardado con su ID generado
     */
    Usuario save(Usuario usuario);
    
    /**
     * Busca un usuario por su ID.
     * @param id el ID del usuario
     * @return Optional con el usuario si existe
     */
    Optional<Usuario> findById(String id);
    
    /**
     * Obtiene todos los usuarios.
     * @return lista de todos los usuarios
     */
    List<Usuario> findAll();
    
    /**
     * Busca un usuario por su correo electrónico.
     * @param correo el correo del usuario
     * @return Optional con el usuario si existe
     */
    Optional<Usuario> findByCorreo(String correo);
    
    /**
     * Elimina un usuario.
     * @param usuario el usuario a eliminar
     */
    void delete(Usuario usuario);
    
    /**
     * Verifica si existe un usuario con el ID especificado.
     * @param id el ID del usuario
     * @return true si existe, false en caso contrario
     */
    boolean existsById(String id);
}
