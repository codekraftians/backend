# 🤝 TechSafeSpace - Comunidad Tech para Mujeres

**TechSafeSpace** es una iniciativa que nace de la necesidad de crear un espacio seguro, accesible y sororo para mujeres y disidencias en el sector tecnológico.

Este proyecto no es solo una plataforma técnica, sino una declaración de intenciones: visibilizar, acompañar y empoderar a quienes tradicionalmente han sido infrarrepresentadas en el mundo digital. Desde el código hasta la comunidad, todo está pensado para fomentar la inclusión y el apoyo mutuo.

## 👩‍💻 Nuestro equipo



| Nombre           | GitHub                                      |                                     
|------------------|---------------------------------------------|
| Mariona          | [@cuyass](https://github.com/cuyass)    | 
| Larissa          | [@saudlari](https://github.com/saudlari)      | 
| Milena           | [@MilenaOcoro](https://github.com/MilenaOcoro)      | 
| Einar            | [@einartech](https://github.com/einartech)      | 
| Alba             | [@rieradipe](https://github.com/rieradipe)      | 

---

## Enlace al frontend

[Frontend](https://github.com/codekraftians/front_end)

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3.4.5
- JPA / Hibernate
- PostgreSQL 17
- Maven 4
- Dotenv para variables de entorno

---

## 📦 Instalación y ejecución

1. Clona el repositorio:

```bash
git clone https://github.com/tu-usuario/backend
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
Sitio web: [FactoríaF5](https://factoriaf5.org/)

---

## 🌍 Impacto social y visión a futuro

TechSafeSpace busca ir más allá del desarrollo de software. Este proyecto representa un punto de encuentro para mujeres que desean formarse, colaborar y crecer juntas en el mundo tecnológico.

Nuestro objetivo es:

- Reducir la brecha de género en el sector tech
- Fomentar el aprendizaje colaborativo desde un enfoque empático
- Ofrecer recursos, acompañamiento y visibilidad a nuevas voces en tecnología

Este es solo el comienzo. Creemos en un futuro donde todas tengamos un lugar seguro y reconocido en el universo digital.
