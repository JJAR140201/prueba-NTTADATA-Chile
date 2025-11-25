package rawson.prueba.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private LocalDateTime creado;

    @Column(nullable = false)
    private LocalDateTime modificado;

    @Column(nullable = false)
    private LocalDateTime ultimoLogin;

    @Column(nullable = true)
    private String token;

    @Column(nullable = false)
    private Boolean activo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private List<Telefono> telefonos;

    public Usuario() {}

    public Usuario(String id, String correo, String nombre, String contrasena, LocalDateTime creado,
                   LocalDateTime modificado, LocalDateTime ultimoLogin, String token, Boolean activo, List<Telefono> telefonos) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.creado = creado;
        this.modificado = modificado;
        this.ultimoLogin = ultimoLogin;
        this.token = token;
        this.activo = activo;
        this.telefonos = telefonos;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public LocalDateTime getCreado() { return creado; }
    public void setCreado(LocalDateTime creado) { this.creado = creado; }
    public LocalDateTime getModificado() { return modificado; }
    public void setModificado(LocalDateTime modificado) { this.modificado = modificado; }
    public LocalDateTime getUltimoLogin() { return ultimoLogin; }
    public void setUltimoLogin(LocalDateTime ultimoLogin) { this.ultimoLogin = ultimoLogin; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public List<Telefono> getTelefonos() { return telefonos; }
    public void setTelefonos(List<Telefono> telefonos) { this.telefonos = telefonos; }

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        if (this.creado == null) {
            this.creado = LocalDateTime.now();
        }
        if (this.modificado == null) {
            this.modificado = LocalDateTime.now();
        }
        if (this.ultimoLogin == null) {
            this.ultimoLogin = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.modificado = LocalDateTime.now();
    }
}
