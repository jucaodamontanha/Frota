# 🚚 Sistema de Gestão de Frotas - Backend

Este repositório contém a API do sistema de controle de frotas e manutenções, desenvolvida em Java com Spring Boot. O sistema gerencia veículos, usuários, ordens de manutenção e o fluxo de aprovação de orçamentos (com upload de arquivos).

---

## 🛠️ Tecnologias utilizadas

- Java 17/21
- Spring Boot 3.x
- Spring Data JPA
- Spring Security (BCrypt)
- MySQL 8.0
- Docker \& Docker Compose
- Lombok
- Maven

---

## 🚀 Como rodar o projeto

### 1. Pré-requisitos

- Docker instalado
- JDK 17 ou superior
- Maven

### 2. Subir o banco de dados

Na raiz do projeto (onde está o arquivo `docker-compose.yml`), execute:

```bash
docker-compose up -d
```
Isso irá iniciar um container com o MySQL.
### 3. Configurar o banco de dados
No arquivo `src/main/resources/application.properties`, configure as credenciais do banco de dados conforme necessário:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fleet_management
spring.datasource.username=root
spring.datasource.password=yourpassword
``` 
### 4. Rodar a aplicação
Na raiz do projeto, execute:
```bash
mvn spring-boot:run
```
A aplicação estará disponível em `http://localhost:8080`.
---
## 📦 Estrutura do Projeto
- `com.fleetmanagement`: Pacote raiz
  - `controller`: Contém os controladores REST
  - `service`: Lógica de negócio
  - `repository`: Interfaces de acesso a dados
  - `model`: Entidades JPA
  - `dto`: Objetos de transferência de dados
  - `config`: Configurações do Spring Security e outras
  - `exception`: Tratamento de exceções personalizadas
---
## 📂 Endpoints principais

| Método | Endpoint                         | Descrição |
|--------|----------------------------------|-----------|
| POST   | `/api/usuarios`                  | Cadastra um novo usuário (Gestor/Mecânico) |
| POST   | `/api/veiculos`                  | Cadastro de novos veículos na frota |
| GET    | `/api/veiculos`                  | Listagem de todos os veículos (via DTO) |
| POST   | `/api/manutencoes`               | Abre uma nova ordem de manutenção |
| POST   | `/api/orcamentos`                | Envia orçamento (Multipart: JSON + Arquivo) |
| PUT    | `/api/orcamentos/{id}/aprovar`   | Aprova um orçamento e altera status da frota |

---
## 📁 Armazenamento de Arquivos
Os orçamentos enviados (PDF/Imagens) são armazenados localmente na pasta:
``
./uploads/orcamentos/
``
## 🛡️ Segurança
CORS: Configurado para aceitar requisições do Frontend React (http://localhost:5173).

Senhas: Criptografadas utilizando o algoritmo BCrypt.

Erros: Tratamento global via GlobalExceptionHandler para respostas JSON padronizadas.

---
## 🤝Contatos
- **Nome:** Lineker Xavier
- **Email:** eng.linekerx@gmail.com
---
## 📄 Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
---

