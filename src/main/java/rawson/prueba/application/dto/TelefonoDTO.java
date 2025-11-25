package rawson.prueba.application.dto;

public class TelefonoDTO {
    private String numero;
    private String codigoCiudad;
    private String codigoPais;

    public TelefonoDTO() {}

    public TelefonoDTO(String numero, String codigoCiudad, String codigoPais) {
        this.numero = numero;
        this.codigoCiudad = codigoCiudad;
        this.codigoPais = codigoPais;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getCodigoCiudad() { return codigoCiudad; }
    public void setCodigoCiudad(String codigoCiudad) { this.codigoCiudad = codigoCiudad; }
    public String getCodigoPais() { return codigoPais; }
    public void setCodigoPais(String codigoPais) { this.codigoPais = codigoPais; }
}
