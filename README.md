# Delivery API

A Delivery API é uma aplicação para gerenciamento de pedidos de entrega. Ela permite registrar pedidos, atualizar informações de pedidos existentes e acompanhar as entregas relacionadas aos pedidos.

## Funcionalidades

A API oferece as seguintes funcionalidades:

- Cadastro de Pedidos: Permite registrar novos pedidos fornecendo os detalhes necessários, como cliente, data do pedido, valor total e status.
- Atualização de Pedidos: Permite atualizar as informações de um pedido existente, como cliente, data do pedido, valor total e status.
- Cadastro de Entregas: Permite registrar informações de entrega relacionadas a um pedido específico.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- JSON Web Token (JWT)
- Swagger
- Banco de Dados (por exemplo, MySQL, PostgreSQL)

## Configuração do Projeto

1. Clone o repositório do projeto.

2. E execute o comando abaixo

```bash
docker compose up --build
```
A aplicação já está pronta para subir e conectar ao banco de dados.

## Swagger

Para acessar o swagger com os endpoints da aplicação acesse:

```url
http://localhost:8080/swagger-ui.html#/
```
Também disponibilizei um arquivo JSON para testar os endpoints através do Insomnia.