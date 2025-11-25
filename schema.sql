-- Script de Creación de Base de Datos - API de Usuarios
-- Base de Datos: H2 (En memoria)
-- Este script se ejecuta automáticamente al iniciar la aplicación

-- Tabla de Usuarios
CREATE TABLE usuarios (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    token VARCHAR(500) NOT NULL,
    creado TIMESTAMP NOT NULL,
    modificado TIMESTAMP NOT NULL,
    ultimo_login TIMESTAMP NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT unique_correo UNIQUE (correo)
);

-- Tabla de Teléfonos
CREATE TABLE telefonos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    numero VARCHAR(255) NOT NULL,
    codigo_ciudad VARCHAR(255) NOT NULL,
    codigo_pais VARCHAR(255) NOT NULL,
    usuario_id VARCHAR(36) NOT NULL,
    CONSTRAINT fk_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

-- Índices para mejorar el rendimiento
CREATE INDEX idx_usuarios_correo ON usuarios(correo);
CREATE INDEX idx_telefonos_usuario_id ON telefonos(usuario_id);

-- Datos de prueba (opcional)
-- Estos datos se pueden usar para pruebas iniciales

-- INSERT INTO usuarios (id, nombre, correo, contrasena, token, creado, modificado, ultimo_login, activo)
-- VALUES (
--     'test-user-001',
--     'Usuario Prueba',
--     'prueba@usuario.org',
--     'encodedPassword',
--     'jwt_token_example',
--     CURRENT_TIMESTAMP,
--     CURRENT_TIMESTAMP,
--     CURRENT_TIMESTAMP,
--     true
-- );

-- INSERT INTO telefonos (numero, codigo_ciudad, codigo_pais, usuario_id)
-- VALUES (
--     '1234567',
--     '1',
--     '57',
--     'test-user-001'
-- );
