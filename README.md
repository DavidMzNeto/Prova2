Modelo de README.md para seu projeto
markdown
Copiar
Editar
# Prova2 - API Spring Boot com Autenticação JWT

Projeto exemplo de API REST desenvolvida com Spring Boot 3.x, que implementa autenticação via JWT, documentação automática com Swagger / OpenAPI e containerização com Docker.

---

## 🛠️ Tecnologias usadas

- Java 17
- Spring Boot 3.x
- Spring Security com JWT
- Spring Data JPA + H2 Database (para testes)
- Springdoc OpenAPI (Swagger UI)
- Docker para containerização
- Maven para gerenciamento de dependências

---

## 🚀 Como rodar o projeto

### Pré-requisitos

- Java 17 instalado
- Maven instalado
- Docker instalado (opcional, para rodar via container)

---

### Rodando localmente

1. Clone o repositório

```bash
git clone https://github.com/DavidMzNeto/Prova2.git
cd Prova2
Compile e rode a aplicação

bash
Copiar
Editar
./mvnw clean spring-boot:run
Acesse a API

Swagger UI: http://localhost:8080/swagger-ui.html

Endpoints:

POST /auth/register – Registrar usuário

POST /auth/login – Login para obter token JWT

Endpoints protegidos exigem token JWT no header Authorization: Bearer <token>

Rodando via Docker
Construa a imagem Docker:

bash
Copiar
Editar
docker build -t prova2-api .
Rode o container:

bash
Copiar
Editar
docker run -p 8080:8080 prova2-api
📚 Documentação
A documentação Swagger é gerada automaticamente e pode ser acessada via:

bash
Copiar
Editar
http://localhost:8080/swagger-ui.html
🔐 Autenticação
A API utiliza JWT para autenticação e autorização:

Registre um usuário em /auth/register

Faça login em /auth/login para receber um token JWT

Use o token para acessar endpoints protegidos, enviando no header Authorization

🧪 Testes
O projeto inclui testes unitários com JUnit 5 e Mockito para os principais serviços e controladores.

Para rodar os testes:

bash
Copiar
Editar
./mvnw test
📝 Considerações finais
Este projeto serve como base para desenvolvimento de APIs REST seguras e bem documentadas usando Spring Boot.

Contribuições são bem-vindas!

📞 Contato
David Mizrahy Neto
https://github.com/DavidMzNeto
davidmizrahyneto@gmail.com

yaml
Copiar
Editar
