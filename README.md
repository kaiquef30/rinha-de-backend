# Rinha de Backend 2024 - Desafio

![Rinha de Backend](https://github.com/zanfranceschi/rinha-de-backend-2024-q1/raw/main/misc/arte.jpg)

## 📌 Sobre o Projeto
Este projeto foi desenvolvido como parte do desafio **Rinha de Backend 2024**. O objetivo é criar uma API altamente eficiente para suportar um alto volume de requisições, seguindo as regras estabelecidas na competição.

## 🚀 Tecnologias Utilizadas
- **[Dropwizard](https://www.dropwizard.io/)** - Framework para desenvolvimento de serviços REST
- **Java** - Linguagem principal do projeto
- **PostgreSQL** - Banco de dados relacional
- **HikariCP** - Gerenciador de pool de conexões

## 📂 Estrutura do Projeto
```
📦 rinha-backend
├── 📁 src
│   ├── 📁 main
│   │   ├── 📁 java/com/seuusuario/rinhabackend
│   │   │   ├── 📁 config        # Configurações da aplicação
│   │   │   ├── 📁 controllers   # Endpoints REST
│   │   │   ├── 📁 models        # Modelos de dados
│   │   │   ├── 📁 repository    # Repositórios para acesso ao banco
│   │   │   ├── 📁 service       # Regras de negócio
│   │   │   ├── Application.java # Classe principal
├── 📄 pom.xml                    # Dependências Maven
└── 📄 README.md                   # Documentação do projeto
```

## ⚙️ Configuração e Execução
### 📌 Pré-requisitos
Antes de iniciar, certifique-se de ter instalado:
- **Java 17+**
- **PostgreSQL**
- **Maven**

### 📥 Clonando o repositório
```bash
git clone https://github.com/seuusuario/rinha-backend.git
cd rinha-backend
```

### 🛠 Configuração do Banco de Dados
1. Crie o banco de dados no PostgreSQL:
```sql
CREATE DATABASE rinha;
```
2. Configure as credenciais no arquivo `config.yml`:
```yaml
database:
  driverClass: org.postgresql.Driver
  user: seu_usuario
  password: sua_senha
  url: jdbc:postgresql://localhost:5432/rinha
```

### 🚀 Rodando a aplicação
```bash
mvn clean package
java -jar target/rinha-backend.jar server config.yml
```

## 📌 Endpoints
### ➤ Criar Transação
```http
POST /transacoes
```
- **Request Body:**
```json
{
  "valor": 1000,
  "descricao": "Depósito",
  "tipo": "C"
}
```
- **Response:** `200 Sucess`

### ➤ Obter Extrato
```http
GET /clientes/{id}/extrato
```
- **Response:**
```json
{
  "saldo": 5000,
  "últimas transacoes": [
    { "valor": 1000, "descricao": "Depósito", "tipo": "C" }
  ]
}
```

## 🏆 Performance
O projeto foi otimizado para um alto volume de requisições utilizando:
- **HikariCP** para otimização do pool de conexões
- **Batch Inserts** para minimizar round-trips no banco
- **Consultas indexadas** para melhorar a performance

## 📜 Licença
Este projeto foi desenvolvido para fins educacionais e de competição.

---
Feito com ❤️ para o desafio **Rinha de Backend 2024**.

