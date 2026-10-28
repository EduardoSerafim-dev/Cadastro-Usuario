# Cadastro de Usuário

API REST desenvolvida em Java com Spring Boot para realizar operações de cadastro e gerenciamento de usuários.

O projeto permite cadastrar, consultar, atualizar e excluir usuários utilizando o e-mail ou o identificador do registro. Os dados são gerenciados pelo Spring Data JPA e armazenados em um banco de dados H2 em memória.

## Tecnologias utilizadas

- **Java 23** — linguagem de programação.
- **Spring Boot 4.1.0** — framework para desenvolvimento da aplicação.
- **Spring Web MVC** — criação dos endpoints REST.
- **Spring Data JPA** — persistência e consulta de dados.
- **H2 Database** — banco de dados relacional em memória.
- **Maven** — gerenciamento de dependências e construção do projeto.
- **Lombok** — redução de código repetitivo.

## Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

- [Git](https://git-scm.com/)
- [Java JDK 23](https://jdk.java.net/23/)

O projeto inclui o Maven Wrapper (`mvnw`), portanto não é necessário instalar o Maven separadamente.

Para verificar se o Java está instalado corretamente, execute:

```bash
java -version
```

A versão instalada deve ser compatível com o Java 23 exigido pelo projeto.

## Como executar o projeto

### 1. Clonar o repositório

Abra um terminal e execute:

```bash
git clone https://github.com/EduardoSerafim-dev/Cadastro-Usuario.git
```

Entre na pasta do projeto:

```bash
cd Cadastro-Usuario
```

### 2. Executar a aplicação

**Linux ou macOS:**

```bash
./mvnw spring-boot:run
```

**Windows:**

```powershell
.\mvnw.cmd spring-boot:run
```

Na primeira execução, o Maven Wrapper poderá baixar os componentes necessários e as dependências do projeto.

Após a inicialização, a aplicação estará disponível em:

```text
http://localhost:8080
```

A aplicação disponibiliza uma API REST, sem uma interface gráfica web própria.

## Endpoints da API

Todos os endpoints utilizam o caminho-base `/usuario`.

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| `POST` | `/usuario` | Cadastra um usuário |
| `GET` | `/usuario?email={email}` | Busca um usuário pelo e-mail |
| `PUT` | `/usuario?id={id}` | Atualiza um usuário pelo ID |
| `DELETE` | `/usuario?email={email}` | Exclui um usuário pelo e-mail |

As requisições que enviam dados no corpo devem utilizar o formato JSON.

### 1. Cadastrar usuário

**Requisição:**

```http
POST http://localhost:8080/usuario
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "nome": "João Silva",
  "email": "joao@example.com"
}
```

O cadastro é realizado por meio do método `POST`. O campo `id` é gerado automaticamente pelo banco de dados.

### 2. Consultar usuário por e-mail

**Requisição:**

```http
GET http://localhost:8080/usuario?email=joao@example.com
```

Exemplo de resposta:

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@example.com"
}
```

O identificador retornado é gerado pelo banco de dados e pode variar.

### 3. Atualizar usuário por ID

**Requisição:**

```http
PUT http://localhost:8080/usuario?id=1
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "nome": "João Santos",
  "email": "joao.santos@example.com"
}
```

A atualização é realizada pelo identificador informado na URL.

É possível enviar apenas os campos que deseja alterar. Os campos omitidos ou enviados como `null` preservam os valores atuais.

### 4. Excluir usuário por e-mail

**Requisição:**

```http
DELETE http://localhost:8080/usuario?email=joao.santos@example.com
```

A operação exclui o usuário que possui o e-mail informado.

### Testando a API

Você pode utilizar ferramentas como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/) para enviar requisições HTTP e visualizar as respostas.

Também é possível utilizar o `curl` diretamente no terminal. Por exemplo:

```bash
curl -X POST http://localhost:8080/usuario \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Silva","email":"joao@example.com"}'
```

No Windows, esse exemplo pode ser executado no terminal Git Bash ou adaptado para o PowerShell.

## Banco de dados H2

O projeto utiliza o banco de dados H2 em memória. Isso significa que não é necessário instalar ou configurar um servidor de banco de dados separado.

As configurações estão definidas em `src/main/resources/application.properties`:

```properties
spring.application.name=cadastro-usuario
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:usuario
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=1234
```

### Acessar o console do H2

Com a aplicação em execução, acesse:

```text
http://localhost:8080/h2-console
```

Utilize os seguintes dados de conexão:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:usuario` |
| User Name | `sa` |
| Password | `1234` |

Clique em **Connect** para acessar o banco de dados.

> **Importante:** o banco H2 está configurado em memória. Os registros são perdidos quando a aplicação é encerrada e iniciada novamente. Além disso, as credenciais apresentadas são valores de desenvolvimento, não recomendados para ambientes de produção.

## Comandos úteis

### Executar a aplicação

Linux ou macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

### Compilar o projeto

Linux ou macOS:

```bash
./mvnw clean package
```

Windows:

```powershell
.\mvnw.cmd clean package
```

Esse comando limpa os arquivos de compilação anteriores, compila o projeto, executa as etapas de teste do ciclo Maven e gera o artefato da aplicação, caso a construção seja concluída com sucesso.

### Executar os testes

Linux ou macOS:

```bash
./mvnw test
```

Windows:

```powershell
.\mvnw.cmd test
```

### Encerrar a aplicação

Para parar a aplicação executada no terminal, pressione:

```text
Ctrl + C
```

## Estrutura do projeto

A organização principal do código é a seguinte:

```text
Cadastro-Usuario/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/meugrupo/cadastro_usuario/
│   │   │       ├── business/
│   │   │       │   └── UsuarioService.java
│   │   │       ├── controller/
│   │   │       │   └── UsuarioController.java
│   │   │       ├── infrastructure/
│   │   │       │   ├── entitys/
│   │   │       │   │   └── Usuario.java
│   │   │       │   └── repository/
│   │   │       │       └── UsuarioRepositoy.java
│   │   │       └── CadastroUsuarioApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .mvn/
├── mvnw
├── mvnw.cmd
└── pom.xml
```

### Responsabilidade das camadas

- **Controller:** recebe as requisições HTTP e retorna as respostas da API.
- **Business:** implementa as regras de negócio e coordena as operações de usuários.
- **Infrastructure/Entity:** define a entidade `Usuario`, que representa os registros armazenados.
- **Infrastructure/Repository:** fornece os métodos de acesso aos dados por meio do Spring Data JPA.
- **Resources:** armazena as configurações da aplicação.

## Configuração do ambiente

O projeto não exige um arquivo `.env` para ser executado, pois as configurações atuais estão definidas em `application.properties`.

Se futuramente forem adicionadas credenciais externas, configurações específicas por ambiente ou um banco de dados persistente, essas propriedades poderão ser externalizadas por variáveis de ambiente.

## Solução de problemas

### Erro de versão do Java

Verifique a versão instalada:

```bash
java -version
```

Se a versão for incompatível, instale o JDK 23 e configure a variável de ambiente `JAVA_HOME`, quando necessário.

### Porta 8080 já está em uso

Se outro processo estiver utilizando a porta 8080, a aplicação poderá falhar ao iniciar.

Encerre o processo que ocupa a porta ou configure outra porta em `application.properties`, por exemplo:

```properties
server.port=8081
```

Nesse caso, a aplicação passará a responder em `http://localhost:8081`.

### Dados desapareceram após reiniciar

Esse comportamento é esperado com a configuração atual do H2 em memória. Os registros não são persistidos entre reinicializações.

### E-mail duplicado

A entidade `Usuario` define a coluna `email` como única. Portanto, não é permitido cadastrar dois usuários com o mesmo e-mail.

---

## Licença

Este projeto foi desenvolvido para fins acadêmicos e de aprendizado.
