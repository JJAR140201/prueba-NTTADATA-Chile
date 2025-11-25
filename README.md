# API REST de Gestión de Usuarios

## Descripción
Aplicación REST desarrollada en Spring Boot que proporciona un API para la gestión de usuarios con autenticación mediante JWT. La aplicación implementa operaciones CRUD (GET, POST, PUT, PATCH, DELETE) con validaciones de correo electrónico y contraseña.

## Requisitos Previos
- Java 17+
- Maven 3.6+
- Git

## Tecnologías Utilizadas
- **Framework**: Spring Boot 3.1.5
- **Base de Datos**: H2 (en memoria)
- **ORM**: Hibernate/JPA
- **Autenticación**: JWT (JSON Web Tokens)
- **API Documentation**: Swagger/OpenAPI 3.0
- **Testing**: JUnit 5 y Mockito
- **Construcción**: Maven

## Instalación

### 1. Clonar el Repositorio
```bash
git clone https://github.com/JJAR140201/prueba-NTTADATA-Chile
cd prueba-NTTADATA-Chile
```

### 2. Compilar el Proyecto
```bash
mvn clean install
```

### 3. Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8081`

(Puerto configurado en `application.properties`)

## Estructura de la Aplicación

### Arquitectura Hexagonal (Ports & Adapters)

La aplicación está estructurada en 4 capas independientes:

```
src/main/java/rawson/prueba/
│
├── domain/                              ← 🎯 DOMINIO (Núcleo independiente)
│   ├── entity/                          # Entidades: Usuario, Telefono
│   ├── port/                            # Puertos (Interfaces): UsuarioRepositoryPort, SecurityPort, ValidacionPort
│   └── exception/                       # Excepciones: UsuarioNoEncontradoException
│
├── application/                         ← 📱 APLICACIÓN (Casos de uso)
│   ├── usecase/                         # 4 casos de uso independientes
│   │   ├── CrearUsuarioUseCase
│   │   ├── ObtenerUsuariosUseCase
│   │   ├── ActualizarUsuarioUseCase
│   │   └── EliminarUsuarioUseCase
│   ├── dto/                             # Data Transfer Objects (DTOs)
│   └── UsuarioApplicationService.java   # Orquestador de casos de uso
│
├── infrastructure/                      ← 🔧 INFRAESTRUCTURA (Implementaciones técnicas)
│   ├── persistence/                     # Adaptadores de persistencia (JPA, Spring Data)
│   ├── security/                        # Adaptador de seguridad (JWT, BCrypt)
│   └── util/                            # Adaptadores de validación (Regex)
│
└── interfaces/                          ← 🌐 INTERFACES (Adaptadores de entrada/salida)
    ├── adapters/rest/controller/        # Controladores REST (@RestController)
    └── config/                          # Configuraciones Spring (JWT, Security, Swagger)
```

### Ventajas de la Arquitectura Hexagonal

✅ **Testabilidad**: Los casos de uso son POJOs sin dependencias de Spring  
✅ **Flexibilidad**: Cambiar tecnología sin tocar la lógica de negocio  
✅ **Claridad**: Separación clara de responsabilidades  
✅ **Escalabilidad**: Fácil agregar nuevas features y adaptadores  
✅ **Independencia**: El dominio no conoce ninguna tecnología external

## Endpoints de la API

### 1. Crear Usuario (POST)
**URL**: `/api/usuarios`
**Método**: POST
**Autenticación**: No requiere
**Content-Type**: application/json

**Solicitud:**
```json
{
  "nombre": "Juan Rodriguez",
  "correo": "juan@rodriguez.org",
  "contraseña": "Password123!",
  "telefonos": [
    {
      "numero": "1234567",
      "codigoCiudad": "1",
      "codigoPais": "57"
    }
  ]
}
```

**Respuesta Exitosa (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nombre": "Juan Rodriguez",
  "correo": "juan@rodriguez.org",
  "creado": "2024-01-15T10:30:00",
  "modificado": "2024-01-15T10:30:00",
  "ultimoLogin": "2024-01-15T10:30:00",
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "activo": true,
  "telefonos": [
    {
      "numero": "1234567",
      "codigoCiudad": "1",
      "codigoPais": "57"
    }
  ]
}
```

**Respuesta de Error (400 Bad Request):**
```json
{
  "mensaje": "El correo ya está registrado"
}
```

### 2. Obtener Usuario (GET)
**URL**: `/api/usuarios/{id}`
**Método**: GET
**Autenticación**: Requiere JWT (Bearer Token)

**Respuesta Exitosa (200 OK):** Retorna el usuario completo (ver ejemplo anterior)

**Respuesta de Error (404 Not Found):**
```json
{
  "mensaje": "Usuario no encontrado"
}
```

### 3. Actualizar Usuario Completamente (PUT)
**URL**: `/api/usuarios/{id}`
**Método**: PUT
**Autenticación**: Requiere JWT (Bearer Token)
**Content-Type**: application/json

**Solicitud:** (mismo formato que POST)

**Respuesta Exitosa (200 OK):** Retorna el usuario actualizado

### 4. Actualizar Usuario Parcialmente (PATCH)
**URL**: `/api/usuarios/{id}`
**Método**: PATCH
**Autenticación**: Requiere JWT (Bearer Token)
**Content-Type**: application/json

**Solicitud:** (solo los campos a actualizar)
```json
{
  "nombre": "Juan Rodriguez Updated"
}
```

**Respuesta Exitosa (200 OK):** Retorna el usuario actualizado

### 5. Eliminar Usuario (DELETE)
**URL**: `/api/usuarios/{id}`
**Método**: DELETE
**Autenticación**: Requiere JWT (Bearer Token)

**Respuesta Exitosa (204 No Content)**

**Respuesta de Error (404 Not Found):**
```json
{
  "mensaje": "Usuario no encontrado"
}
```

## Validaciones

### Formato del Correo
El correo debe cumplir con la expresión regular: `^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]{2}$`

Ejemplos válidos:
- juan@rodriguez.org
- user123@domain.com

### Formato de la Contraseña
Por defecto, la contraseña debe:
- Contener al menos 8 caracteres
- Incluir al menos una letra minúscula
- Incluir al menos una letra mayúscula
- Incluir al menos un número
- Incluir al menos un carácter especial (@$!%*?&)

Expresión regular: `^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$`

Ejemplo válido: `Password123!`

## Autenticación JWT

### Obtener Token
Al crear un usuario, la respuesta incluye un token JWT que puede usarse para acceder a los otros endpoints.

### Usar el Token
En los headers de las solicitudes autenticadas, incluir:
```
Authorization: Bearer <tu_token_jwt>
```

### Expiración del Token
Por defecto, los tokens expiran después de 24 horas (86400000 ms).

## Documentación Swagger

Una vez la aplicación esté en ejecución, puede acceder a la documentación interactiva de Swagger en:

```
http://localhost:8081/swagger-ui.html
```

O acceder a los JSON de la especificación OpenAPI en:

```
http://localhost:8081/v3/api-docs
```

## Consola H2

Para ver la base de datos en memoria durante el desarrollo:

```
http://localhost:8081/h2-console
```

**Configuración:**
- URL de JDBC: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: (dejar en blanco)

## Configuración

### Archivo application.properties

Ubicado en `src/main/resources/application.properties`

```properties
# Base de Datos H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop

# JWT
jwt.secret=MySecretKeyForJWTTokenGenerationAndValidation12345
jwt.expiration=86400000

# Validaciones
validacion.correo.regex=^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2}$
validacion.contrasena.regex=^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$
```

Puede personalizar estas propiedades según sus necesidades.

## Ejecución de Pruebas

### Ejecutar Todas las Pruebas
```bash
mvn test
```

### Ejecutar Pruebas de un Módulo Específico
```bash
mvn test -Dtest=UsuarioServiceTest
```

## Construcción para Producción

### Generar Jar Ejecutable
```bash
mvn clean package
```

El JAR estará disponible en `target/prueba-0.0.1-SNAPSHOT.jar`

### Ejecutar el JAR
```bash
java -jar target/prueba-0.0.1-SNAPSHOT.jar
```

## Base de Datos

### Script de Creación
Las tablas se crean automáticamente mediante Hibernate usando la configuración:
```properties
spring.jpa.hibernate.ddl-auto=create-drop
```

**Tablas generadas:**
- `usuarios`: Contiene la información de los usuarios
- `telefonos`: Contiene los números de teléfono asociados a cada usuario

### Diagrama ER (Entity-Relationship)

```
USUARIOS
├── id (PK, UUID)
├── nombre
├── correo (UNIQUE)
├── contrasena
├── token
├── creado
├── modificado
├── ultimoLogin
└── activo

TELEFONOS
├── id (PK, Auto-increment)
├── numero
├── codigoCiudad
├── codigoPais
└── usuario_id (FK → USUARIOS.id)
```

## Ejemplo de Uso Completo

### 1. Crear un usuario
```bash
curl -X POST http://localhost:8081/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Rodriguez",
    "correo": "juan@rodriguez.org",
    "contraseña": "Password123!",
    "telefonos": [
      {
        "numero": "1234567",
        "codigoCiudad": "1",
        "codigoPais": "57"
      }
    ]
  }'
```

Respuesta:
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

### 2. Usar el token para obtener el usuario
```bash
curl -X GET http://localhost:8081/api/usuarios/550e8400-e29b-41d4-a716-446655440000 \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..."
```

### 3. Actualizar el usuario
```bash
curl -X PUT http://localhost:8081/api/usuarios/550e8400-e29b-41d4-a716-446655440000 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..." \
  -d '{
    "nombre": "Juan Rodriguez Updated",
    "correo": "juan@rodriguez.org",
    "contraseña": "NewPassword123!",
    "telefonos": []
  }'
```

### 4. Eliminar el usuario
```bash
curl -X DELETE http://localhost:8081/api/usuarios/550e8400-e29b-41d4-a716-446655440000 \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..."
```

## Manejo de Errores

### Posibles Códigos de Error

| Código | Descripción |
|--------|-------------|
| 201 | Creado exitosamente |
| 200 | OK |
| 204 | No Content (Eliminado) |
| 400 | Bad Request (Datos inválidos) |
| 401 | Unauthorized (No autenticado) |
| 404 | Not Found (Recurso no encontrado) |
| 500 | Internal Server Error |

## Diagrama de Solución

### Diagrama de Arquitectura - Componentes y Capas
![Arquitectura Componentes y Capas](images/DIAGRAMA%20DE%20ARQUITECTURA%20Componentes-Capas.png)

### Diagrama de Clases UML Completo
![Diagrama de Clases UML](images/DIAGRAMA%20DE%20CLASES%20UML%20COMPLETO.png)

### Diagrama de Secuencia - POST
![Diagrama de Secuencia POST](images/DIAGRAMA%20DE%20SECUENCIA–POST.png)

## Contribuciones

Para contribuir al proyecto:
1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT.

## Contacto

**NTTA Data Chile**
- Website: https://co.nttdata.com/
- Email: contacto@nttadata.com

## Soporte

Para reportar problemas o sugerencias, por favor abre un issue en el repositorio.

---

**Última actualización**: Noviembre 2025
**Versión**: 1.0.0
