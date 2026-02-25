Perfeito — aqui vai a versão **README nível GitHub que recrutador ama** 🚀
(com badges, visual mais profissional e estrutura forte).

Você pode copiar direto para seu `README.md`.

---

# 📚 Literalura

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot">
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql">
  <img src="https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow?style=for-the-badge">
</p>

---

## 🧠 Sobre o projeto

O **Literalura** é uma aplicação Java com Spring Boot que consome a API do Gutendex (Project Gutenberg), persiste livros e autores em banco PostgreSQL e permite consultas via menu interativo no terminal.

Este projeto foca em **boas práticas de backend**, consumo de API e modelagem JPA.

---

## ✨ Funcionalidades

✅ Buscar livro por título (API externa)
✅ Salvar livros no PostgreSQL
✅ Evitar duplicidade de autores
✅ Listar livros cadastrados
✅ Listar autores cadastrados
✅ Buscar autores vivos em determinado ano
✅ Filtrar livros por idioma
✅ Menu interativo no terminal

---

## 🏗️ Arquitetura

```
src/main/java/com/alura/literalura
│
├── principal      → Menu e fluxo da aplicação
├── service        → Consumo da API + conversão JSON
├── dto            → Records de mapeamento
├── models         → Entidades JPA
└── repository     → Interfaces Spring Data
```

---

## 🔌 API externa

A aplicação consome a API pública:

👉 [https://gutendex.com/](https://gutendex.com/)

### Exemplo de requisição

```
GET https://gutendex.com/books/?search=emma
```

---

## 🗄️ Modelo de domínio

### 📘 Livro

| Campo           | Tipo      |
| --------------- | --------- |
| id              | Long      |
| titulo          | String    |
| idioma          | String    |
| numeroDownloads | Integer   |
| autor           | ManyToOne |

---

### 👤 Autor

| Campo         | Tipo      |
| ------------- | --------- |
| id            | Long      |
| nome          | String    |
| anoNascimento | Integer   |
| anoMorte      | Integer   |
| livros        | OneToMany |

---

## ⚙️ Configuração do banco

No `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

⚠️ **Pré-requisitos**

* Java 17+
* PostgreSQL rodando
* Maven ou Gradle

---

## ▶️ Como executar

### 1️⃣ Clone o projeto

```bash
git clone https://github.com/seu-usuario/literalura.git
cd literalura
```

---

### 2️⃣ Crie o banco

```sql
CREATE DATABASE literalura;
```

---

### 3️⃣ Execute

Via Maven:

```bash
./mvnw spring-boot:run
```

Ou rode pela IDE.

---

## 🖥️ Menu da aplicação

```
Escolha o número de sua opção:

1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros em um determinado idioma
6 - Top 10 livros mais baixados

0 - Sair
```

---

## 🔍 Conceitos aplicados

Este projeto demonstra domínio de:

* ✔️ Consumo de API com HttpClient
* ✔️ Desserialização com Jackson
* ✔️ Records (Java moderno)
* ✔️ Relacionamentos JPA
* ✔️ Lazy vs Eager loading
* ✔️ Streams e Optional
* ✔️ Spring Data JPA
* ✔️ Tratamento de exceções
* ✔️ Boas práticas de persistência

---

## 🧑‍💻 Autor

**Gabriel de Souza Guimarães**

📌 Estudante de Java Backend
📌 Foco em Spring Boot e APIs REST

---

