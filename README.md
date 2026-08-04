# Zona Fit (GYM) - Spring Boot Java Swing Application

Este proyecto es una aplicación de escritorio con interfaz gráfica en **Java Swing** utilizando el framework **Spring Boot** para gestionar los clientes de un gimnasio llamado **Zona Fit**. Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) interactuando directamente con una base de datos MySQL a través de Spring Data JPA, utilizando un tema moderno en modo oscuro gracias a **FlatLaf**.

---

## 🚀 ¿De qué trata el proyecto?

El sistema proporciona una interfaz gráfica moderna (Desktop GUI) que permite a los usuarios:
1. **Visualizar Clientes**: Una tabla interactiva (`JTable`) que lista todos los clientes registrados en la base de datos en tiempo real.
2. **Seleccionar Cliente**: Al hacer clic en cualquier fila de la tabla, los datos del cliente se cargan automáticamente en el formulario para su edición o eliminación.
3. **Agregar / Actualizar Cliente**: Formulario integrado para registrar un nuevo cliente o actualizar los datos de uno existente (validando que la membresía sea única).
4. **Limpiar Formulario**: Botón para limpiar los campos de texto del formulario y restablecer la selección actual.
5. **Eliminar Cliente**: Remueve al cliente seleccionado permanentemente del sistema.

---

## 🛠️ ¿Cómo se hizo? (Tecnologías y Arquitectura)

El proyecto se estructuró bajo una arquitectura limpia y modular utilizando las siguientes tecnologías:

*   **Java 21**: La versión del lenguaje utilizada para el desarrollo del proyecto.
*   **Spring Boot 4.1.0**: Framework principal para la inyección de dependencias y el inicio rápido de la aplicación (configurada en modo no-headless para la GUI desktop).
*   **Spring Data JPA**: Para la persistencia de datos (ORM), facilitando las operaciones CRUD en la base de datos sin escribir SQL complejo.
*   **Java Swing**: Framework estándar de Java para la construcción de interfaces de usuario de escritorio.
*   **FlatLaf (FlatDarculaLaf)**: Look and Feel moderno que proporciona un aspecto profesional en modo oscuro.
*   **MySQL**: Motor de base de datos relacional para persistir la información.
*   **Lombok**: Biblioteca para reducir el código boilerplate en las clases de modelo.
*   **Maven**: Gestor de dependencias y compilador del proyecto.

### Estructura de Paquetes
Ubicada bajo el directorio principal [gm/zona_fit](src/main/java/gm/zona_fit):
*   📁 **`gui`**: Contiene la interfaz gráfica de usuario construida con Swing:
    *   [ZonaFitForm.java](src/main/java/gm/zona_fit/gui/ZonaFitForm.java): Lógica y eventos del formulario de la interfaz.
    *   [ZonaFitForm.form](src/main/java/gm/zona_fit/gui/ZonaFitForm.form): Archivo de diseño visual de la interfaz.
*   📁 **`modelo`**: Contiene la entidad [Cliente.java](src/main/java/gm/zona_fit/modelo/Cliente.java), que mapea directamente a la tabla `clientes` de la base de datos.
*   📁 **`repositorio`**: Contiene [RepositorioCliente.java](src/main/java/gm/zona_fit/repositorio/RepositorioCliente.java), interfaz que extiende de `JpaRepository` para el acceso a datos.
*   📁 **`servicio`**: Capa lógica de negocio compuesta por la interfaz [ClienteServicio.java](src/main/java/gm/zona_fit/servicio/ClienteServicio.java) y su implementación [ClienteServicioImpl.java](src/main/java/gm/zona_fit/servicio/ClienteServicioImpl.java).
*   📁 **`utils`**: Utilidades adicionales.
*   📄 **[ZonaFitSwing.java](src/main/java/gm/zona_fit/ZonaFitSwing.java)**: Nueva clase principal y punto de entrada de la aplicación que arranca el contexto de Spring en modo no-headless y lanza el formulario Swing.
*   📄 **[ZonaFitApplication.java](src/main/java/gm/zona_fit/ZonaFitApplication.java)**: Antigua clase principal para la versión de consola (actualmente desactivada / comentada).

---

## ⚙️ Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener configurado:
1.  **Java JDK 21** o superior.
2.  **MySQL Server** en ejecución.
3.  Un gestor de base de datos como MySQL Workbench o DBeaver.
4.  **Maven** (opcional, incluido en el wrapper del proyecto).

---

## 🗄️ Configuración de la Base de Datos

1. Abre tu cliente MySQL y ejecuta la siguiente consulta para crear la base de datos y la tabla correspondiente:

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

2. Configura los parámetros de conexión en el archivo [application.properties](src/main/resources/application.properties):

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

# Mantener la aplicación de escritorio activa sin servidor web Tomcat
spring.main.web-application-type=none
```

---

## 🏁 Cómo Ejecutar el Proyecto

Puedes compilar y ejecutar la interfaz gráfica desde tu terminal siguiendo estos pasos:

### 1. Abrir la terminal
Abre una consola (PowerShell o CMD) en el directorio raíz del proyecto `ZonaFitSpring`.

### 2. Compilar el proyecto
Descarga dependencias y compila utilizando Maven Wrapper:

*   **Windows (cmd/PowerShell):**
    ```bash
    .\mvnw.cmd clean compile
    ```
*   **Linux / macOS:**
    ```bash
    ./mvnw clean compile
    ```

### 3. Ejecutar la aplicación Swing
Inicia la aplicación ejecutando:

*   **Windows (cmd/PowerShell):**
    ```bash
    .\mvnw.cmd spring-boot:run
    ```
*   **Linux / macOS:**
    ```bash
    ./mvnw spring-boot:run
    ```

También puedes ejecutar el proyecto directamente desde tu IDE ejecutando la clase principal [ZonaFitSwing.java](src/main/java/gm/zona_fit/ZonaFitSwing.java).

---

## 📄 Licencia

Este proyecto se desarrolló con fines educativos y de aprendizaje personal. ¡Siéntete libre de utilizarlo, modificarlo y compartirlo! 😊
