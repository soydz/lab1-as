
# 🏦 Banco Simulado

[![Backend](https://img.shields.io/badge/backend-SpringBoot-brightgreen)](https://spring.io/projects/spring-boot)
[![Frontend](https://img.shields.io/badge/frontend-Svelte-orange)](https://svelte.dev/)
[![Database](https://img.shields.io/badge/database-MySQL-blue)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/license-MIT-lightgrey)](LICENSE)

Aplicación que simula un banco, permitiendo a los usuarios:
- Crear nuevos clientes
- Listar todos los clientes
- Realizar transacciones entre cuentas
- Consultar el historial de transacciones

Consta de **backend** (API REST en Spring Boot) y **frontend** (svelte).

---

## 🚀 Funcionalidades

### Backend (Spring Boot)
- CRUD completo de **clientes** y **transacciones**
- Arquitectura por capas:
  - **Entity**: define las tablas de la base de datos
  - **Repository**: interfaces JPA
  - **DTO**: transferencia de datos
  - **Mapper**: mapea entidades ↔ DTOs
  - **Service**: lógica de negocio
  - **Controller**: endpoints REST
- Tecnologías: Spring Boot, MySQL, JPA, Lombok, MapStruct

### Frontend (Svelte)
- Gestión de clientes y transacciones
- Componentes:
  - **Routes (Pages)**: rutas y páginas
  - **Services**: llamadas a la API (`fetch`)
  - **Components**: elementos UI reutilizables
- Interfaz intuitiva y amigable

---

## ⚙️ Instalación y ejecución

### Backend
```bash
git clone <tu-repo-url>
cd backend
# Configura la DB en application.properties
mvn clean install
mvn spring-boot:run
```

### Frontend
``` bash
cd frontend
pnpm install
pnpm dev
# Abrir en http://localhost:5173
```

## 🗂 Estructura del proyecto

### Backend
```
src/
 └─ main/
     ├─ java/com/soydz/
     │    ├─ entity/
     │    ├─ repository/
     │    ├─ dto/
     │    ├─ mapper/
     │    ├─ service/
     │    └─ controller/
     |    └- Main
     └─ resources/
          └─ application.properties
```

### Frontend
```
src/
 ├─ routes/      # Páginas y rutas
 ├─ services/    # Llamadas a la API
 └─ components/  # Componentes UI reutilizables
 └─ App.svelte
```
