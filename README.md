# 📚 DevBooks - Backend

Bem-vindo ao repositório backend do **DevBooks**, um e-commerce de livros. Esta aplicação é responsável por gerenciar toda a lógica de negócios, persistência de dados e autenticação para a plataforma.

---

## 🚀 Tecnologias Utilizadas

* **Java 21**: Linguagem de programação.
* **Spring Boot**: Framework para construção de aplicações robustas.
* **PostgreSQL**: Banco de dados relacional.
* **Spring Data JPA**: Para persistência de dados.
* **JWT (JSON Web Tokens)**: Para autenticação e autorização.

---

## ⚙️ Configuração do Ambiente

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

* **JDK 21**
* **PostgreSQL**

### Banco de Dados

1.  Crie um banco de dados PostgreSQL com o nome `devbooks`.
2.  Configure as credenciais do banco de dados no arquivo `src/main/resources/application.properties` ou `application.yml`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/devbooks
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

    Mude `seu_usuario` e `sua_senha` para as suas credenciais do PostgreSQL.

## 🌐 Repositório Frontend

Acesse o repositório do frontend (DevBooks - Frontend) construído com Angular:

[DevBooks - Frontend](https://github.com/BrunoFujisaki/devbooks-front-end)
