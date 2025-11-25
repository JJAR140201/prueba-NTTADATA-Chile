package rawson.prueba.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UsuarioResponseDTO {
    
    private String id;
    private String nombre;
    private String correo;
    private LocalDateTime creado;
    private LocalDateTime modificado;
    private LocalDateTime ultimoLogin;
    private String token;
    private Boolean activo;
    private List<TelefonoDTO> telefonos;

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(String id, String nombre, String correo, LocalDateTime creado, 
            LocalDateTime modificado, LocalDateTime ultimoLogin, String token, Boolean activo, List<TelefonoDTO> telefonos) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.creado = creado;
        this.modificado = modificado;
        this.ultimoLogin = ultimoLogin;
        this.token = token;
        this.activo = activo;
        this.telefonos = telefonos;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
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
    public List<TelefonoDTO> getTelefonos() { return telefonos; }
    public void setTelefonos(List<TelefonoDTO> telefonos) { this.telefonos = telefonos; }
}
