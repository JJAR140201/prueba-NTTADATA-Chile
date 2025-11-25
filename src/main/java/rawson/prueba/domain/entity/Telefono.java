package rawson.prueba.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "telefonos")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String codigoCiudad;

    @Column(nullable = false)
    private String codigoPais;

    @Column(name = "usuario_id")
    private String usuarioId;

    public Telefono() {}

    public Telefono(Long id, String numero, String codigoCiudad, String codigoPais, String usuarioId) {
        this.id = id;
        this.numero = numero;
        this.codigoCiudad = codigoCiudad;
        this.codigoPais = codigoPais;
        this.usuarioId = usuarioId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getCodigoCiudad() { return codigoCiudad; }
    public void setCodigoCiudad(String codigoCiudad) { this.codigoCiudad = codigoCiudad; }
    public String getCodigoPais() { return codigoPais; }
    public void setCodigoPais(String codigoPais) { this.codigoPais = codigoPais; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}
