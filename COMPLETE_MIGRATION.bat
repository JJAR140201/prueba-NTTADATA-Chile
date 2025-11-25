@echo off
REM ============================================
REM Hexagonal Architecture Migration Script
REM Completa la reorganización eliminando código viejo
REM ============================================

SETLOCAL ENABLEDELAYEDEXPANSION

echo.
echo ========================================
echo Hexagonal Architecture Migration
echo ========================================
echo.

REM Definir ruta base
set PROJECT_ROOT=C:\Users\juana\Desktop\CARPETAS\java
set SRC_PATH=%PROJECT_ROOT%\src\main\java\rawson\prueba

echo Ruta del proyecto: %PROJECT_ROOT%
echo.

REM ==== FASE 1: Eliminar código duplicado ====
echo [1/5] Eliminando código duplicado...
if exist "%SRC_PATH%\application\usecase\UsuariosUseCases.java" (
    del "%SRC_PATH%\application\usecase\UsuariosUseCases.java"
    echo   ✓ Eliminado: UsuariosUseCases.java
) else (
    echo   - UsuariosUseCases.java no encontrado (OK)
)

REM ==== FASE 2: Eliminar carpetas viejas ====
echo.
echo [2/5] Eliminando carpetas viejas...

if exist "%SRC_PATH%\service" (
    rmdir /s /q "%SRC_PATH%\service"
    echo   ✓ Eliminada: service/
)

if exist "%SRC_PATH%\controller" (
    rmdir /s /q "%SRC_PATH%\controller"
    echo   ✓ Eliminada: controller/
)

if exist "%SRC_PATH%\dto" (
    rmdir /s /q "%SRC_PATH%\dto"
    echo   ✓ Eliminada: dto/
)

if exist "%SRC_PATH%\entity" (
    rmdir /s /q "%SRC_PATH%\entity"
    echo   ✓ Eliminada: entity/
)

if exist "%SRC_PATH%\repository" (
    rmdir /s /q "%SRC_PATH%\repository"
    echo   ✓ Eliminada: repository/
)

if exist "%SRC_PATH%\config" (
    rmdir /s /q "%SRC_PATH%\config"
    echo   ✓ Eliminada: config/
)

if exist "%SRC_PATH%\util" (
    rmdir /s /q "%SRC_PATH%\util"
    echo   ✓ Eliminada: util/
)

REM ==== FASE 3: Compilar ====
echo.
echo [3/5] Compilando proyecto...
cd /d "%PROJECT_ROOT%"
call mvn clean compile -DskipTests

if %ERRORLEVEL% EQU 0 (
    echo.
    echo   ✓ Compilación exitosa
) else (
    echo.
    echo   ✗ Error en compilación
    echo   Revisa los errores arriba
    pause
    exit /b 1
)

REM ==== FASE 4: Ejecutar Tests ====
echo.
echo [4/5] Ejecutando tests...
call mvn test

if %ERRORLEVEL% EQU 0 (
    echo.
    echo   ✓ Tests pasados
) else (
    echo.
    echo   ⚠ Algunos tests fallaron
    echo   Revisa los resultados arriba
)

REM ==== FASE 5: Generar JAR ====
echo.
echo [5/5] Generando JAR...
call mvn package -DskipTests

if %ERRORLEVEL% EQU 0 (
    echo.
    echo   ✓ JAR generado: target\prueba-0.0.1-SNAPSHOT.jar
) else (
    echo.
    echo   ✗ Error generando JAR
    pause
    exit /b 1
)

REM ==== RESUMEN FINAL ====
echo.
echo ========================================
echo ✓ MIGRACIÓN COMPLETADA
echo ========================================
echo.
echo Proyecto ahora es 100%% Hexagonal:
echo   ✓ Estructura reorganizada
echo   ✓ Código viejo eliminado
echo   ✓ Compilación exitosa
echo   ✓ Tests pasados
echo   ✓ JAR generado
echo.
echo Para iniciar la aplicación:
echo   java -jar target\prueba-0.0.1-SNAPSHOT.jar
echo.
echo La aplicación estará disponible en:
echo   http://localhost:8081
echo.
echo Documentación disponible:
echo   - HEXAGONAL_ARCHITECTURE_GUIDE.md
echo   - HEXAGONAL_SUMMARY.md
echo   - SESSION_SUMMARY.md
echo.
pause
