package rawson.prueba.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO de Solicitud para Usuario
 * 
 * Arquitectura Hexagonal - Capa de Aplicación:
 * Los DTOs transforman datos externos en objetos del dominio.
 * Actúan como barrera entre la interfaz y la lógica de negocio.
 */
public class UsuarioRequestDTO {
    
    private String nombre;
    private String correo;
    @JsonProperty("contraseña")
    private String contrasena;
    private List<TelefonoDTO> telefonos;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(String nombre, String correo, String contrasena, List<TelefonoDTO> telefonos) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefonos = telefonos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<TelefonoDTO> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoDTO> telefonos) {
        this.telefonos = telefonos;
    }
}
