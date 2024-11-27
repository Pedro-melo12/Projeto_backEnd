Projeto de Gestão de Pedidos

Este projeto é uma aplicação de gestão de pedidos onde os usuários podem fazer pedidos de produtos. Os pedidos podem ser criados, atualizados e visualizados, com associação aos usuários que realizam as compras. A aplicação é construída com Spring Boot, JPA e JWT para autenticação.

Tecnologias Utilizadas
Spring Boot - Framework para o desenvolvimento da aplicação backend.
Spring Security - Para autenticação e autorização (usando JWT).
JPA/Hibernate - Para a persistência de dados no banco de dados.
PostgreSQL - Banco de dados utilizado.
Lombok - Para reduzir a verbosidade do código.
Jackson - Para serialização e desserialização de objetos Java para JSON.
Maven - Gerenciador de dependências e build.
Pré-requisitos
Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

Java 17+ (ou qualquer versão LTS do JDK)
Maven
PostgreSQL (ou outro banco de dados de sua escolha)
Postman para testar os endpoints da API.
Configuração do Projeto
1. Clonando o repositório
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
2. Configuração do Banco de Dados
Crie um banco de dados no PostgreSQL com o nome order_management (ou outro de sua escolha).

3. Configuração das Variáveis de Ambiente
Altere o arquivo application.properties ou application.yml para definir as configurações do banco de dados:

spring.datasource.url=jdbc:postgresql://localhost:5432/order_management
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
4. Configuração de Autenticação (JWT)
Caso você utilize autenticação JWT, defina as variáveis de ambiente para a chave secreta do JWT e outros parâmetros de segurança no arquivo application.properties:

jwt.secret=your_jwt_secret_key
jwt.expiration=3600000
5. Executando o Projeto
Para rodar o projeto, use o comando:

mvn spring-boot:run
Ou, para compilar e rodar o jar:

mvn clean install
java -jar target/seu-projeto.jar
A aplicação estará disponível em http://localhost:8080.

Endpoints da API
1. Autenticação (Login)
URL: /auth/login
Método: POST
Body (JSON):
{
  "username": "usuário",
  "password": "senha"
}
Resposta (200 OK):
{
  "token": "seu_jwt_token_aqui"
}
2. Criar Pedido
URL: /orders
Método: POST
Autenticação: Requer token JWT no cabeçalho Authorization: Bearer {token}
Body (JSON):
{
  "products": [
    {
      "id": "produto_id_1",
      "name": "Chuteira",
      "description": "Produto para jogadores de futebol",
      "price": 159
    },
    {
      "id": "produto_id_2",
      "name": "Bola de Futebol",
      "description": "Bola oficial",
      "price": 99
    }
  ]
}
Resposta (201 Created):
{
  "id": "id_do_pedido",
  "user": {
    "id": "id_usuario",
    "username": "usuario",
    "role": "ROLE_USER"
  },
  "products": [
    {
      "id": "produto_id_1",
      "name": "Chuteira",
      "description": "Produto para jogadores de futebol",
      "price": 159
    }
  ],
  "totalPrice": 159
}
3. Atualizar Pedido
URL: /orders/{orderId}
Método: PUT
Autenticação: Requer token JWT no cabeçalho Authorization: Bearer {token}
Body (JSON):
{
  "products": [
    {
      "id": "produto_id_1",
      "name": "Chuteira",
      "description": "Produto para jogadores de futebol",
      "price": 159
    },
    {
      "id": "produto_id_2",
      "name": "Bola de Futebol",
      "description": "Bola oficial",
      "price": 99
    }
  ]
}
Resposta (200 OK):
{
  "id": "id_do_pedido",
  "user": {
    "id": "id_usuario",
    "username": "usuario",
    "role": "ROLE_USER"
  },
  "products": [
    {
      "id": "produto_id_1",
      "name": "Chuteira",
      "description": "Produto para jogadores de futebol",
      "price": 159
    }
  ],
  "totalPrice": 159
}
4. Obter Pedido
URL: /orders/{orderId}
Método: GET
Autenticação: Requer token JWT no cabeçalho Authorization: Bearer {token}
Resposta (200 OK):
{
  "id": "id_do_pedido",
  "user": {
    "id": "id_usuario",
    "username": "usuario",
    "role": "ROLE_USER"
  },
  "products": [
    {
      "id": "produto_id_1",
      "name": "Chuteira",
      "description": "Produto para jogadores de futebol",
      "price": 159
    }
  ],
  "totalPrice": 159
}
Testando a API com o Postman
Faça o login usando o endpoint /auth/login para obter um token JWT.
Crie um pedido enviando um POST para /orders com o token JWT no cabeçalho Authorization.
Atualize um pedido enviando um PUT para /orders/{orderId} com o token JWT no cabeçalho.
Recupere um pedido enviando um GET para /orders/{orderId} com o token JWT no cabeçalho.
Contribuições
Contribuições são bem-vindas! Se você deseja contribuir para este projeto, por favor, siga estas etapas:

Faça um fork deste repositório.
Crie uma branch para sua funcionalidade (git checkout -b feature/nova-funcionalidade).
Faça suas modificações e adicione testes, se necessário.
Commit suas mudanças (git commit -am 'Adiciona nova funcionalidade').
Push para a branch (git push origin feature/nova-funcionalidade).
Abra uma pull request.
