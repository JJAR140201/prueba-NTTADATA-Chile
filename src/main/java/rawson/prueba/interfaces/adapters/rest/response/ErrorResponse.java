package rawson.prueba.interfaces.adapters.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RESPUESTA ERROR REST
 * Adaptador de salida para errores HTTP
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String mensaje;
}
