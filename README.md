# Piliteje Mobile – App Android (Kotlin + XML) + Backend Spring Boot

App móvil de e-commerce para **Piliteje Amigurumis**, con dos roles:

* **Cliente**: catálogo, carrito, pago simulado, solicitud de envío y perfil.
* **Admin**: gestión de productos (con múltiples imágenes), usuarios y pedidos/pagos.

La app consume un backend REST desarrollado en **Spring Boot (Java)** y usa **JWT** para autenticación.

---

## 1. Requisitos

### Android

* Android Studio **Narwhal/Koala** o superior
* JDK 11 (o compatible con tu Gradle)
* Emulador o dispositivo físico con Android 8.0+
* Conexión a Internet (para hablar con el backend)

### Backend

* JDK 17 (o la versión que uses en tu proyecto Spring Boot)
* Maven o Gradle (según tu proyecto)
* **MariaDB/MySQL** en local (XAMPP / Docker / servicio standalone)

---

## 2. Inicializar el backend (Spring Boot)

### 2.1. Clonar el proyecto

```bash
git clone <URL_DEL_REPO_BACKEND>
cd pilitejeamigurumis-backend
```

### 2.2. Configurar base de datos

En `src/main/resources/application.properties` (o `application.yml`) configurar:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/piliteje_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Ajusta `piliteje_db`, usuario y contraseña según tu entorno.

### 2.3. Puerto y contexto

El backend expone la API en:

```properties
server.port=8080
server.servlet.context-path=/api
```

Con esto, un endpoint típico queda así:

* `http://localhost:8080/api/auth/login`
* `http://localhost:8080/api/productos`
* `http://localhost:8080/api/cliente/carrito`
* `http://localhost:8080/api/admin/ordenes` (ejemplo)

### 2.4. Ejecutar el backend

#### Opción 1: Línea de comandos

```bash
mvn spring-boot:run
```

#### Opción 2: IDE

Abrir el proyecto en IntelliJ / STS y ejecutar la clase:

```text
com.pilitejeamigurumis.PilitejeAmigurumisApplication
```

Cuando la app arranca, deberías ver algo como:

```text
Tomcat started on port 8080
Started PilitejeAmigurumisApplication in ...
```

---

## 3. Configuración de la app Android

### 3.1. Clonar el proyecto

```bash
git clone <URL_DEL_REPO_ANDROID>
cd PilitejeMobile
```

Abrir la carpeta en Android Studio.

### 3.2. URL base para Retrofit (emulador)

En `app/build.gradle` (module), la URL base está definida en `buildConfigField`:

```gradle
android {
    ...

    defaultConfig {
        ...

        buildConfigField(
                "String",
                "BASE_URL",
                "\"http://10.0.2.2:8080/api/\""
        )
    }
}
```

* `10.0.2.2` es la IP especial para que el **emulador Android** acceda a tu `localhost`.
* Si levantas el backend en otra máquina / cloud, cambia esta URL (ej: `"https://piliteje-backend.onrender.com/api/"`).

### 3.3. URL base para entorno de producción (opcional)

Si despliegas el backend en Railway/Render/etc., puedes:

* Cambiar la `BASE_URL` en `build.gradle`, **o**
* Leerla desde `local.properties` usando otra `buildConfigField` (explicado en el README si lo implementas).

### 3.4. Dependencias clave

La app usa:

* **Retrofit + Gson**
* **OkHttp + LoggingInterceptor**
* **ViewBinding**
* **RecyclerView**
* **SharedPreferences** para sesión/rol

Todo viene ya configurado en `build.gradle` y en la clase `ApiClient`.

---

## 4. Autenticación y manejo de sesión

### 4.1. Login

La app llama a:

```text
POST /auth/login
Content-Type: application/json
```

Body (ejemplo):

```json
{
  "email": "cliente@piliteje.cl",
  "password": "Cliente123*"
}
```

El backend responde con un JSON similar a:

```json
{
  "token": "jwt_aquí",
  "role": "CLIENTE",
  "userId": 2,
  "name": "Cliente Demo",
  "email": "cliente@piliteje.cl"
}
```

### 4.2. SessionManager (SharedPreferences)

En Android se guarda:

* `accessToken`
* `role`
* `userId`
* `userName`
* `userEmail`

Con eso se maneja:

* **Redirección automátia** desde `MainActivity`:

  * `ADMIN` → `AdminMainActivity`
  * `CLIENTE`/otro → `ClienteMainActivity`
* **Logout**: se llama a `SessionManager.clear()` y se vuelve a `LoginActivity`.

### 4.3. Retrofit + JWT

`ApiClient` crea el `OkHttpClient` con un `AuthInterceptor` que agrega:

```http
Authorization: Bearer <token>
```

a todas las rutas protegidas (productos, carrito, admin, etc.).

---

## 5. Usuarios de prueba (demo)

Estos usuarios se crean llamando a `/auth/register` (ver JSON más abajo) o dejando un **CommandLineRunner** en el backend.

### Admin demo

* **Email**: `admin@piliteje.cl`
* **Password**: `Admin123*`
* **Rol**: `ADMIN`

### Cliente demo

* **Email**: `cliente@piliteje.cl`
* **Password**: `Cliente123*`
* **Rol**: `CLIENTE`

> Importante: ajusta estos valores si tu entidad de usuario usa otros campos (`username`, `rut`, etc.) y actualiza también el código de login de Android.

---

## 6. Almacenamiento de imágenes

Las imágenes de productos se manejan así:

* El backend expone un endpoint de subida de archivos, por ejemplo:
  `POST /admin/productos/{id}/imagenes` (Multipart).
* Los archivos se guardan en el servidor de Spring Boot en una carpeta local, por ejemplo:

```text
<proyecto-backend>/uploads/
```

* En la base de datos se guarda el **nombre del archivo** o la **URL relativa**.
* El frontend (web y móvil) carga las imágenes con URLs del tipo:

```text
http://10.0.2.2:8080/api/uploads/<nombre-archivo>.jpg
```

> En producción, documenta en el README del backend dónde se encuentra esa carpeta en el servidor (ej: `/var/www/piliteje/uploads`).

---

## 7. Flujo de prueba recomendado (para el video)

1. **Login Admin**

   * Inicia con `admin@piliteje.cl / Admin123*`.
   * Crea un producto con varias imágenes.
   * Listar/buscar productos.
   * Bloquear/desbloquear un usuario.
   * Revisar un pago/orden pendiente y marcarlo como **enviado** y **rechazado** en otro caso.

2. **Logout**

3. **Login Cliente**

   * Inicia con `cliente@piliteje.cl / Cliente123*`.
   * Navega por el catálogo y agrega productos al carrito.
   * Edita cantidades / elimina ítems.
   * Realiza **pago simulado** y especifica dirección de envío.
   * Ve el estado del pedido.
   * Edita su perfil (nombre, teléfono, dirección).

4. **Logout**

---

## 8. Cómo compilar la APK

En Android Studio:

1. **Build > Build Bundle(s) / APK(s) > Build APK(s)**
2. La APK generada se encuentra en:

```text
app/build/outputs/apk/debug/app-debug.apk
```

o en `release` si compilas en modo release.

Esa APK es la que se entrega junto al video demo.

---

## 9. Variables / URLs a modificar según el entorno

* **Android – `BASE_URL`**

  * `app/build.gradle`:

    ```gradle
    buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:8080/api/\"")
    ```

* **Backend – DB y puerto**

  * `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`
  * `server.port`, `server.servlet.context-path`
