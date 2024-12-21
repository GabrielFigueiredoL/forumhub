# Challenge Alura: Forum Hub

API feita para o desafio da Alura forum hub para a trilha de desenvolvimento Back-End em Java. Foi desenvolvido uma API REST utilizando Spring a partir de um diagrama de Entidade-Relacionamento, e possui as seguintes funcionalidades:
1. Usuários
	- Cadastrar usuários no banco de dados
	- Atualizar e apagar usuários
	- Retornar dados de um ou vários usuários
2. Tópicos
	- Cadastrar um tópico no banco de dados
	- Atualizar e apagar usuários
	- Retornar dados de um ou vários tópicos
3. Respostas
	- Cadastrar e apagar respostas relacionadas a um tópico

- Possui autenticação utilizando tokens JWT e Spring Security
- Documentação utilizando Swagger
- Banco de dados MySQL
- Migrations com Flyway
- Validações com validation
- Criptografia de senhas

## Instalação

Para fazer a utilização do projeto:
1. basta clonar este repositório,
2. Instalar as dependências com maven,
3. Criar um banco de dados mysql com o nome forum_hub,
4. Atualizar os dados do banco de dados no application.properties,
5. Executar o arquivo ForumhubApplication na IDE de preferência.

## Aprendizados

- Desenvolvimento de uma API REST utilizando Java
- Controle de autenticação e autorização com Spring Security e tokens JWT
- Criptografia utilizando Bcript
- Controle de migrations utilizando o flyway
- Documentação de uma API utilizando Swagger
- Tratamento de exceções



## Tecnologias utilizadas

- Java
- Maven
- Spring Boot
- Lombok
- Spring Web
- Spring Boot DevTools
- Spring Data JPA
- Flyway Migration
- MySQL Driver
- Validation
- Spring Security
