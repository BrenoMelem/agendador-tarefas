# agendador-tarefas

Microsserviço de **agendamento de tarefas**, construído em **Java 21 + Spring Boot**, parte de uma arquitetura de **microsserviços**.

## 📖 Sobre o projeto

O `agendador-tarefas` expõe uma API REST para criar e gerenciar tarefas agendadas, com persistência em **MongoDB**, autenticação via **JWT** e comunicação com outros microsserviços através de **OpenFeign**. O mapeamento entre entidades e DTOs é feito com **MapStruct**, reduzindo código repetitivo (boilerplate). O projeto já vem pronto para rodar em containers via Docker.

### 🧩 Arquitetura do ecossistema

Este serviço faz parte de um conjunto de microsserviços que trabalham juntos:

| Serviço | Porta | Responsabilidade | Banco |
|---|---|---|---|
| [`usuario`](https://github.com/BrenoMelem/usuario) | 8080 | Cadastro/autenticação de usuários | PostgreSQL |
| **`agendador-tarefas`** | **8081** | **Agendamento de tarefas (este repositório)** | MongoDB |
| [`notificacao`](https://github.com/BrenoMelem/notificacao) | 8082 | Envio de e-mails/notificações | — |
| [`bff-agendador`](https://github.com/BrenoMelem/bff-agendador) | 8083 | Agrega as chamadas aos serviços acima | — |

## 🚀 Tecnologias utilizadas

**Linguagem e build**
- Java 21
- Gradle (Gradle Wrapper incluído — não precisa ter o Gradle instalado)

**Framework e core**
- Spring Boot 4.0.6
- Spring Web MVC — camada REST
- Spring Data MongoDB — persistência de dados
- Spring Security — autenticação e autorização
- Spring Cloud OpenFeign — comunicação HTTP entre microsserviços

**Autenticação**
- JJWT (`io.jsonwebtoken`) — geração e validação de tokens JWT

**Mapeamento de objetos**
- MapStruct — conversão entre entidades e DTOs

**Banco de dados**
- MongoDB

**Produtividade**
- Lombok (integrado ao MapStruct via `lombok-mapstruct-binding`)

**Testes**
- Spring Boot Starter Test (MongoDB, Security e Web)
- JUnit 5 (JUnit Platform)

**Infraestrutura**
- Docker (build multi-stage)
- Docker Compose (app + MongoDB)
- GitHub Actions (CI/CD)

## 📂 Estrutura do projeto

```
agendador-tarefas/
├── .github/workflows/     # Pipelines de CI (GitHub Actions)
├── gradle/wrapper/         # Gradle Wrapper
├── src/main/                # Código-fonte da aplicação
├── build.gradle             # Configuração de build e dependências
├── docker-compose.yml       # Orquestração local (app + MongoDB)
├── Dockerfile                # Build multi-stage da imagem da aplicação
└── settings.gradle
```

## ⚙️ Pré-requisitos

- Java 21 (JDK)
- Docker e Docker Compose (para rodar via container)

## ▶️ Como executar

### Opção 1 — Via Docker Compose (recomendado)

1. Crie um arquivo `.env` na raiz do projeto com as variáveis de configuração necessárias (ex.: dados de conexão do MongoDB, segredo do JWT, etc.).
2. Suba os containers:

   ```bash
   docker-compose up --build
   ```

3. A aplicação estará disponível em `http://localhost:8081`.

### Opção 2 — Localmente com Gradle

1. Suba uma instância do MongoDB local (ou via Docker) e configure a conexão em `application.properties`/`application.yml`.
2. Execute:

   ```bash
   ./gradlew bootRun
   ```

## 🧪 Testes

```bash
./gradlew test
```

## 🔁 CI/CD

O repositório conta com workflows em `.github/workflows` para automatizar build e verificações a cada push/pull request.

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma *issue* ou enviar um *pull request*.
