package rawson.prueba.infrastructure.persistence.adapter;

import rawson.prueba.domain.entity.Usuario;
import rawson.prueba.domain.port.UsuarioRepositoryPort;
import rawson.prueba.infrastructure.persistence.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * ADAPTADOR DE SALIDA (Output Adapter)
 * 
 * Arquitectura Hexagonal - Infrastructure Layer:
 * Implementa el puerto UsuarioRepositoryPort usando Spring Data JPA.
 * Convierte la llamada de dominio a operaciones de BD.
 * 
 * Desacoplamiento: El dominio NO sabe que usa JPA; solo sabe que implementa el puerto.
 */
@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {
    
    @Autowired
    private UsuarioJpaRepository jpaRepository;
    
    @Override
    public Usuario save(Usuario usuario) {
        return jpaRepository.save(usuario);
    }
    
    @Override
    public Optional<Usuario> findById(String id) {
        return jpaRepository.findById(id);
    }
    
    @Override
    public List<Usuario> findAll() {
        return jpaRepository.findAll();
    }
    
    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return jpaRepository.findByCorreo(correo);
    }
    
    @Override
    public void delete(Usuario usuario) {
        jpaRepository.delete(usuario);
    }
    
    @Override
    public boolean existsById(String id) {
        return jpaRepository.existsById(id);
    }
}
