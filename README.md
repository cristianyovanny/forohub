# Foro Hub API 💻

Esta es una API Rest para el proyecto final de Foro Hub de Alura.

## Descripción 📃

Esta API ayuda a manejar usuarios, cursos, respuestas y temas en la plataforma Foro Hub. Puedes registrar usuarios, crear y gestionar cursos, y responder a temas.

## Funcionalidades Principales 📑📎

- **Usuarios:**
    - Crear, actualizar y ver información de usuarios.
    - Iniciar sesión usando JWT para obtener un token.

- **Cursos:**
    - Crear, actualizar y ver información de cursos.
    - Listar cursos de forma paginada.

- **Respuestas:**
    - Crear, actualizar y ver respuestas a temas.
    - Listar respuestas de forma paginada.

- **Temas:**
    - Crear, actualizar, ver y eliminar temas.
    - Listar temas de forma paginada.

## Tecnologías Utilizadas 📟

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Web
- Flyway para migraciones de base de datos
- DevTools para desarrollo
- MySQL Connector/J
- Lombok para generar código automáticamente
- Spring Boot Test y Spring Security Test para pruebas
- Auth0 Java JWT para manejar tokens JWT
- Springdoc OpenAPI para documentación de la API con Swagger UI

## Requisitos Previos 🔙

Antes de comenzar, asegúrate de tener instalados:

- Java JDK 17
- Maven
- MySQL (o un sistema de gestión de bases de datos compatible)

## Configuración y Uso ⚙️

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/tu_usuario/foro-hub-api.git

2. Configurar la Base de Datos:
* Crea una base de datos MySQL llamada foro-hub.
* Ajusta la configuración de la base de datos en application.properties según sea necesario.

3. Ejecutar la aplicación en tu IDE.

4. Documentación de la API:

Accede a Swagger UI para explorar y probar los endpoints de la API:

    ```bash
    https://localhost:8080/swagger-ui.html

