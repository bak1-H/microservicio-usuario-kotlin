# Microservicio Usuario (Java Spring Boot)

Pequeño servicio REST para registrar y autenticar usuarios.

## Tecnologías
- Java 17
- Spring Boot 4 (Web MVC, Data JPA)
- PostgreSQL (driver)
- Maven

## Estructura
- `src/main/java/com/kotlin/usuario1`
  - `controller/UsuarioController.java`: Endpoints REST
  - `service/UsuarioService.java`: Lógica de negocio
  - `repository/UsuarioRepository.java`: Acceso a datos (Spring Data JPA)
  - `model/Usuario.java`: Entidad JPA (`id`, `nombre`, `email`, `password`)
- `src/main/resources/application.properties`: Configuración
- `render.yaml` y `Dockerfile`: Despliegue/contenerización

## Endpoints
- `POST /api/usuarios/register`
  - Crea un usuario.
  - Body ejemplo:
    ```json
    {
      "nombre": "Juan Perez",
      "email": "juan.perez@example.com",
      "password": "MiPasswordSegura123"
    }
    ```
- `GET /api/usuarios/listar`
  - Lista todos los usuarios.
- `GET /api/usuarios/login?email=...&password=...`
  - Autenticación simple por email y password.
  - Respuesta `401` si credenciales inválidas.

## Seguridad
- Este proyecto compara contraseñas en texto plano (solo demo).
- En producción: usar `PasswordEncoder`, almacenar hash y NO devolver `password` en respuestas.

## Requisitos previos
- JDK 17
- Maven
- Base de datos PostgreSQL (opcional: se puede iniciar sin conexión si no accedes a datos).

## Configuración
Editar `src/main/resources/application.properties` con tu conexión DB:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/usuarios
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

## Construir y ejecutar
```powershell
# Compilar
mvn clean package

# Ejecutar
mvn spring-boot:run

# O usando el jar
java -jar target/usuario1-0.0.1-SNAPSHOT.jar
```

## Docker
Construir y correr con Docker:
```powershell
# Build
docker build -t usuario1:latest .

# Run
docker run -p 8080:8080 --env SPRING_PROFILES_ACTIVE=default usuario1:latest
```

## Pruebas rápidas con curl
```powershell
# Registrar ejemplo
curl -X POST http://localhost:8080/api/usuarios/register -H "Content-Type: application/json" -d '{"nombre":"Juan","email":"juan@example.com","password":"123"}'

# Listar ejemplo
curl http://localhost:8080/api/usuarios/listar

# Login ejemplo
curl "http://localhost:8080/api/usuarios/login?email=juan@example.com&password=123"
```

## El endpoint en render es

## https://microservicio-usuario-kotlin.onrender.com
