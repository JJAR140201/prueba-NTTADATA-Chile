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

## Ventajas de Tecnologías Utilizadas y Razones de Uso

### 🚀 Spring Boot 3.1.5

**Ventajas:**
- ✅ **Configuración Automática**: Proporciona auto-configuración inteligente que reduce la boilerplate code
- ✅ **Inicio Rápido**: Permite crear aplicaciones REST en minutos sin configuración compleja
- ✅ **Ecosistema Robusto**: Integración seamless con Spring Data, Spring Security, Spring Cloud
- ✅ **Producción Ready**: Incluye actuators, health checks, métricas para monitoreo
- ✅ **Java Moderno**: Soporta Java 17 con todas las características modernas del lenguaje
- ✅ **Embedded Server**: Tomcat embebido, no requiere servidor externo

**Razones de Uso:**
- Es el estándar de facto en la industria para aplicaciones Java/REST
- Permite desarrollo rápido manteniendo calidad enterprise
- Excelente soporte y comunidad activa
- Compatible con arquitectura hexagonal

---

### 💾 H2 Database (En Memoria)

**Ventajas:**
- ✅ **Sin Instalación**: No requiere servidor externo, se ejecuta en memoria
- ✅ **Ideal para Desarrollo**: Perfecto para testing y desarrollo local
- ✅ **Consola Web Integrada**: Interfaz visual para inspeccionar datos en tiempo real
- ✅ **Velocidad**: Extremadamente rápida al estar en RAM
- ✅ **SQL Estándar**: Compatible con SQL estándar, fácil migración a BD de producción
- ✅ **Reseteo Automático**: Se limpia automáticamente al reiniciar la aplicación

**Razones de Uso:**
- Perfecto para fase de desarrollo sin infraestructura compleja
- Eliminates frición en ambiente local
- Facilita CI/CD sin dependencias externas
- Permite testing determinista sin efectos secundarios

---

### 🗄️ Hibernate/JPA (Object-Relational Mapping)

**Ventajas:**
- ✅ **Abstracción de Base de Datos**: Código independiente del motor SQL específico
- ✅ **Mapeo Automático**: Convierte automáticamente objetos Java en registros SQL
- ✅ **Queries Type-Safe**: Criteria API y QueryDSL para queries sin SQL raw strings
- ✅ **Lazy Loading**: Carga datos bajo demanda optimizando memoria
- ✅ **Transacciones Automáticas**: Gestión automática de transacciones ACID
- ✅ **Cascading**: Gestión automática de relaciones parent-child
- ✅ **Change Tracking**: Detecta cambios automáticamente para updates

**Razones de Uso:**
- Elimina tediosa tarea de mapear SQL a objetos Java
- Permite cambiar BD sin cambiar código de aplicación
- Previene SQL Injection mediante parametrización automática
- Mejor mantenibilidad y menos errores manuales

---

### 🔐 JWT (JSON Web Tokens)

**Ventajas:**
- ✅ **Stateless Authentication**: No requiere sesiones en el servidor
- ✅ **Escalabilidad**: Perfecto para microservicios y load balancing
- ✅ **Información Encapsulada**: El token contiene claims/información del usuario
- ✅ **Seguridad Criptográfica**: Tokens firmados digitalmente, imposibles de falsificar
- ✅ **Cross-Domain/CORS**: Funciona perfectamente con CORS y múltiples dominios
- ✅ **Mobile-Friendly**: Ideal para aplicaciones móviles que no soportan cookies
- ✅ **Autorización Granular**: Permite codificar permisos directamente en el token

**Razones de Uso:**
- Estándar moderno para autenticación en APIs REST
- Escalable horizontalmente sin estado en servidor
- Elimina vulnerabilidades de session hijacking
- Mejor experiencia en aplicaciones distribuidas

---

### 📚 Swagger/OpenAPI 3.0

**Ventajas:**
- ✅ **Documentación Automática**: Genera documentación del API directamente del código
- ✅ **Interfaz Interactiva**: Consola web para probar endpoints sin Postman
- ✅ **Client Generators**: Genera clientes para diferentes lenguajes automáticamente
- ✅ **Especificación Estándar**: OpenAPI 3.0 es estándar de la industria
- ✅ **Sincronización Automática**: La documentación siempre está actualizada con el código
- ✅ **Validación Automática**: Valida las respuestas contra el esquema definido
- ✅ **Facilita Integración**: Terceros pueden consumir la API sabiendo exactamente qué esperar

**Razones de Uso:**
- Elimina documentación manual que se desactualiza
- Reduce tiempo de onboarding para nuevos developers
- Herramienta crítica para comunicación entre equipos frontend/backend
- Esencial para APIs públicas

---

### 🧪 JUnit 5 y Mockito

**Ventajas:**

**JUnit 5:**
- ✅ **Arquitectura Modular**: Separa testing framework de execution engine
- ✅ **Anotaciones Mejoradas**: @Test, @BeforeEach, @ParameterizedTest más poderosas
- ✅ **Display Names Personalizados**: `@DisplayName("descripción legible")` para reportes claros
- ✅ **Extensibilidad**: Sistema de extensiones para custom behavior
- ✅ **Soporte Paralelización**: Ejecuta tests en paralelo para feedback más rápido

**Mockito:**
- ✅ **Mocking Simplificado**: API clara y legible para crear mocks
- ✅ **Stubbing Flexible**: Define comportamiento de dependencias fácilmente
- ✅ **Verificación de Interacciones**: Valida que métodos fueron llamados correctamente
- ✅ **Inyección de Mocks**: `@Mock` y `@InjectMocks` para tests más limpios
- ✅ **Spy Objects**: Combina mocks y objetos reales para scenarios complejos

**Razones de Uso:**
- JUnit 5 es la versión moderna recomendada por la comunidad Java
- Testing permite validar lógica de negocio sin dependencias externas
- Mockito elimina la necesidad de crear stubs manuales
- Tests automáticos capturan regresiones tempranamente

---

### 🔨 Maven 3.11.0

**Ventajas:**
- ✅ **Gestión de Dependencias Centralizada**: POM.xml define todas las librerías
- ✅ **Build Reproducible**: Mismo pom.xml en cualquier máquina genera idéntico build
- ✅ **Ciclo de Vida Estándar**: Fases predefinidas (compile, test, package, deploy)
- ✅ **Plugin Ecosystem**: Plugins para cualquier tarea imaginable
- ✅ **Convention Over Configuration**: Estructura de directorios predefinida reduce configuración
- ✅ **Manejo de Versiones**: Transitive dependency resolution, evita conflicts
- ✅ **CI/CD Integración**: Perfecto para pipelines de automatización

**Razones de Uso:**
- Maven es estándar en la industria Java enterprise
- Gestión de dependencias automática y segura
- Ciclo de vida bien definido
- Excelente para proyectos grandes y complejos

---

### 🏗️ Arquitectura Hexagonal (Ports & Adapters)

**Ventajas:**
- ✅ **Testabilidad Total**: Lógica de negocio sin dependencias externas
- ✅ **Independencia Tecnológica**: Cambiar BD, framework sin afectar dominio
- ✅ **Separación de Responsabilidades**: Cada capa tiene una función clara
- ✅ **Mantenibilidad**: Código organizado y fácil de entender
- ✅ **Escalabilidad**: Agregar features sin afectar capas existentes
- ✅ **Flexibilidad**: Múltiples adaptadores de entrada/salida
- ✅ **Domain-Driven Design**: Enfoque en la lógica de negocio

**Razones de Uso:**
- Aplicaciones producen en 5+ años, arquitectura permite evolucionar
- Desacopla lógica de negocio de detalles técnicos
- Facilita testing sin mock de la aplicación completa
- Preparado para cambios de tecnología sin reescribir negocio

---

### 🔒 Spring Security + BCrypt

**Ventajas:**

**Spring Security:**
- ✅ **Autenticación Centralizada**: Un lugar para gestionar quién eres
- ✅ **Autorización Granular**: Control de acceso por endpoint/método
- ✅ **CSRF Protection**: Protección contra ataques CSRF automática
- ✅ **Integración JWT**: Soporte nativo para tokens JWT
- ✅ **Password Encoding**: Gestión centralizada de encoding

**BCrypt:**
- ✅ **Hashing Adaptativo**: Se vuelve más lento con el tiempo contra ataques
- ✅ **Salt Automático**: Previene rainbow table attacks
- ✅ **Industry Standard**: Usado por gobiernos y grandes corporaciones
- ✅ **Resistant**: Ningún algoritmo de cracking efectivo conocido

**Razones de Uso:**
- Seguridad es paramount en aplicaciones de producción
- BCrypt es 10,000+ iteraciones de salting, no plaintext o MD5
- Previene breaches masivos si BD es comprometida
- Spring Security agiliza configuración de autenticación

---

## Comparativa: Por Qué Estas Tecnologías Juntas

| Aspecto | Beneficio |
|--------|----------|
| **Desarrollo** | Spring Boot → Setup en minutos sin boilerplate |
| **Escalabilidad** | JWT + Stateless → Escala horizontalmente sin problemas |
| **Confiabilidad** | Hibernate + Tests → Menos bugs, cambios seguros |
| **Documentación** | Swagger → API autodocumentada y siempre actualizada |
| **Seguridad** | JWT + BCrypt → Tokens seguros, contraseñas hasheadas |
| **Flexibilidad** | Arquitectura Hexagonal → Cambiar tecnología sin dolor |
| **Testing** | JUnit5 + Mockito → Tests sin dependencias externas |
| **Produción** | Maven + Spring Boot → JAR ejecutable listo para deployment |

---

## Stack Total en Resumen

```
┌─────────────────────────────────────────────┐
│        SPRING BOOT 3.1.5 (Framework)        │
├─────────────────────────────────────────────┤
│  JWT (Autenticación) + BCrypt (Hashing)     │
│  Hibernate/JPA (ORM) + H2 (Base de Datos)   │
│  Spring Security (Autorización)             │
├─────────────────────────────────────────────┤
│  Swagger/OpenAPI (Documentación)            │
│  JUnit5 + Mockito (Testing)                 │
│  Maven 3.11.0 (Build & Dependencias)        │
├─────────────────────────────────────────────┤
│  Arquitectura Hexagonal (Design Pattern)    │
└─────────────────────────────────────────────┘
```

Este stack proporciona una **aplicación enterprise-ready, segura, testeable, escalable y mantenible** ✅

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
