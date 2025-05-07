# 🤝 TeachSafeSpace - Comunidad Tech para Mujeres

**TeachSafeSpace** es una iniciativa que nace de la necesidad de crear un espacio seguro, accesible y sororo para mujeres y disidencias en el sector tecnológico.

Este proyecto no es solo una plataforma técnica, sino una declaración de intenciones: visibilizar, acompañar y empoderar a quienes tradicionalmente han sido infrarrepresentadas en el mundo digital. Desde el código hasta la comunidad, todo está pensado para fomentar la inclusión y el apoyo mutuo.

## 👩‍💻 Nuestro equipo

![Equipo TeachSafeSpace](.https://videos.openai.com/vg-assets/assets%2Ftask_01jtpamehxfkabncc953ejex0x%2F1746653641_img_0.webp?st=2025-05-07T20%3A05%3A00Z&se=2025-05-13T21%3A05%3A00Z&sks=b&skt=2025-05-07T20%3A05%3A00Z&ske=2025-05-13T21%3A05%3A00Z&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skoid=aa5ddad1-c91a-4f0a-9aca-e20682cc8969&skv=2019-02-02&sv=2018-11-09&sr=b&sp=r&spr=https%2Chttp&sig=R3i4Y0vNiK8tsG1fnPJfSzUDWTY3kIZw30dl%2BV5m6QM%3D&az=oaivgprodscus)

| Nombre           | GitHub                                      |                                     
|------------------|---------------------------------------------|
| Mariona          | [@tuGitHub](cuyass)    | 
| Larissa          | [@nombre1](https://github.com/saudlari)      | 
| Milena           | [@nombre2](https://github.com/MilenaOcoro)      | 
| Einar            | [@nombre2](https://github.com/MilenaOcoro)      | 
| Alba             | [@nombre2](https://github.com/rieradipe)      | 

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- JPA / Hibernate
- PostgreSQL
- Maven
- Dotenv para variables de entorno

---

## 📦 Instalación y ejecución

1. Clona el repositorio:

```bash
git clone https://github.com/tu-usuario/backend-eventos-inclusivos.git
cd backend-eventos-inclusivos
```

2. Asegúrate de tener PostgreSQL activo y crea la base de datos.

3. Configura el archivo `.env` en la raíz del proyecto:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/eventosdb
SPRING_DATASOURCE_USERNAME=tu_usuario
SPRING_DATASOURCE_PASSWORD=tu_contraseña
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

4. Ejecuta la aplicación:

```bash
mvn spring-boot:run
```

---

## 🔐 Endpoints disponibles

### POST /api/v1/users

Crea un nuevo usuario.

**Cuerpo de la petición:**

```json
{
  "name": "AdaLovelace",
  "email": "ada@lovelace.dev",
  "password": "SecurePass1!",
  "userImageUrl": "https://i.pravatar.cc/150?img=3"
}
```

**Ejemplo en el controlador:**

```java
@PostMapping
public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
    User createdUser = userService.createUser(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
}
```

---

## 🧪 Pruebas realizadas

- Se probó el endpoint `POST` con Postman.
- Verificado que devuelve código `201` y guarda en PostgreSQL (pgAdmin).
- Validaciones activas con anotaciones como `@Email`, `@NotBlank`, `@Pattern`.

---

## 📁 Estructura de carpetas relevante

```
src/
├── controller/
│   └── UserController.java
├── model/
│   └── User.java
├── repository/
│   └── UserRepository.java
├── service/
│   └── UserService.java
```

---

## 👩‍💻 Autoría y contribución

Proyecto realizado por el equipo de **Mujeres y disidentes - CodeCraft F5**  
Contacto: `factoriaf5@Barcelona`

---

## 🌍 Impacto social y visión a futuro

TeachSafeSpace busca ir más allá del desarrollo de software. Este proyecto representa un punto de encuentro para mujeres que desean formarse, colaborar y crecer juntas en el mundo tecnológico.

Nuestro objetivo es:

- Reducir la brecha de género en el sector tech
- Fomentar el aprendizaje colaborativo desde un enfoque empático
- Ofrecer recursos, acompañamiento y visibilidad a nuevas voces en tecnología

Este es solo el comienzo. Creemos en un futuro donde todas tengamos un lugar seguro y reconocido en el universo digital.




