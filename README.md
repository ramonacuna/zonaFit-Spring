# Zona Fit (GYM) - Spring Boot Console Application

Este proyecto es una aplicación de consola en **Java** utilizando el framework **Spring Boot** para gestionar los clientes de un gimnasio llamado **Zona Fit**. Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) interactuando directamente con una base de datos MySQL a través de Spring Data JPA.

---

## 🚀 ¿De qué trata el proyecto?

El sistema proporciona una interfaz interactiva en la consola que permite a los usuarios:
1. **Listar Clientes**: Muestra todos los clientes registrados en la base de datos con sus respectivos detalles.
2. **Buscar Cliente**: Encuentra la información detallada de un cliente en específico mediante su ID.
3. **Agregar Cliente**: Registra un nuevo cliente solicitando su nombre, apellido y número de membresía.
4. **Modificar Cliente**: Actualiza los datos de un cliente existente buscando primero por su ID.
5. **Eliminar Cliente**: Remueve un cliente del sistema mediante su ID.
6. **Salir**: Termina la ejecución de la aplicación de manera segura.

---

## 🛠️ ¿Cómo se hizo? (Tecnologías y Arquitectura)

El proyecto se estructuró bajo una arquitectura limpia y modular utilizando las siguientes tecnologías:

*   **Java 21**: La versión del lenguaje utilizada para el desarrollo del proyecto.
*   **Spring Boot 4.1.0**: Framework principal para la inyección de dependencias y el inicio rápido de la aplicación (configurada como aplicación de consola desactivando el servidor web Tomcat incorporado).
*   **Spring Data JPA**: Para la abstracción de la capa de persistencia de datos (ORM), facilitando las consultas a la base de datos sin escribir SQL manual complejo.
*   **MySQL**: Motor de base de datos relacional para persistir la información.
*   **Lombok**: Biblioteca que reduce el código boilerplate en las clases de modelo mediante anotaciones (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@ToString`, etc.).
*   **Maven**: Gestor de dependencias y compilador del proyecto.

### Estructura de Paquetes
Ubicada bajo el directorio principal [src/main/java/gm/zona_fit](src/main/java/gm/zona_fit):
*   📁 **`modelo`**: Contiene la entidad de negocio [Cliente.java](src/main/java/gm/zona_fit/modelo/Cliente.java), que mapea directamente a la tabla `clientes` de la base de datos.
*   📁 **`repositorio`**: Contiene [RepositorioCliente.java](src/main/java/gm/zona_fit/repositorio/RepositorioCliente.java), interfaz que extiende de `JpaRepository` para proveer métodos de acceso a datos de forma directa.
*   📁 **`servicio`**: Capa lógica de negocio compuesta por la interfaz [ClienteServicio.java](src/main/java/gm/zona_fit/servicio/ClienteServicio.java) y su implementación [ClienteServicioImpl.java](src/main/java/gm/zona_fit/servicio/ClienteServicioImpl.java), desacoplando el controlador/consola de la base de datos.
*   📁 **`utils`**: Utilidades como [Utils.java](src/main/java/gm/zona_fit/utils/Utils.java) para la captura segura de datos por consola, evitando excepciones de formato.
*   📄 **[ZonaFitApplication.java](src/main/java/gm/zona_fit/ZonaFitApplication.java)**: Clase principal que implementa `CommandLineRunner`, la cual arranca la aplicación de consola y maneja el flujo del menú de usuario.

---

## ⚙️ Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado y configurado:
1.  **Java JDK 21** o superior.
2.  **MySQL Server** en ejecución.
3.  Un gestor de base de datos como MySQL Workbench, DBeaver o phpMyAdmin (opcional, para visualización).
4.  **Maven** (opcional, ya que el proyecto incluye los wrappers `mvnw` y `mvnw.cmd`).

---

## 🗄️ Configuración de la Base de Datos

1. Abre tu cliente MySQL favorito y ejecuta la siguiente consulta para crear la base de datos y la tabla necesaria:

```sql
-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS zona_fit_db;
USE zona_fit_db;

-- Crear la tabla clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    membresia INT NOT NULL UNIQUE
);
```

2. Verifica y modifica la configuración de conexión en el archivo [application.properties](src/main/resources/application.properties) si es necesario:

```properties
spring.application.name=zona_fit

# Conexión MySQL
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/zona_fit_db
spring.datasource.username=tu_usuario_mysql  # Por defecto: root
spring.datasource.password=tu_contraseña_mysql # Por defecto en este proyecto: admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Evitar que Hibernate altere el esquema de la base de datos automáticamente
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false

# Desactivar el servidor web de Tomcat para mantener la app en consola
spring.main.web-application-type=none
```

---

## 🏁 Cómo Ejecutar el Proyecto

Puedes compilar y ejecutar el proyecto desde tu terminal o consola de comandos siguiendo estos pasos:

### 1. Clonar o abrir la carpeta del proyecto
Abre una terminal (PowerShell o CMD) en la carpeta raíz del proyecto `ZonaFitSpring`.

### 2. Compilar el proyecto
Utiliza el comando Maven Wrapper para compilar y descargar las dependencias del proyecto:

*   **Windows (cmd/PowerShell):**
    ```bash
    .\mvnw.cmd clean compile
    ```
*   **Linux / macOS:**
    ```bash
    ./mvnw clean compile
    ```

### 3. Ejecutar la aplicación
Para iniciar la aplicación, ejecuta el siguiente comando:

*   **Windows (cmd/PowerShell):**
    ```bash
    .\mvnw.cmd spring-boot:run
    ```
*   **Linux / macOS:**
    ```bash
    ./mvnw spring-boot:run
    ```

O bien, puedes abrir e iniciar el proyecto de forma directa desde tu IDE favorito (como IntelliJ IDEA, Eclipse o VS Code) ejecutando la clase principal `ZonaFitApplication.java`.

---

## 📄 Licencia

Este proyecto se desarrolló con fines educativos y de aprendizaje personal. ¡Siéntete libre de utilizarlo, modificarlo y compartirlo! 😊
