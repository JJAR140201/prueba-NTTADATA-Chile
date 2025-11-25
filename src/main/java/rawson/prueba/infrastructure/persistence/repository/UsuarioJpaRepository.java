package rawson.prueba.infrastructure.persistence.repository;

import rawson.prueba.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * SPRING DATA JPA REPOSITORY
 * 
 * No es un puerto; es la implementación técnica.
 * El UsuarioRepositoryAdapter lo usa para implementar el puerto UsuarioRepositoryPort.
 * 
 * Ciclo: Dominio → Puerto → Adaptador → JpaRepository → BD
 */
@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String correo);
}
