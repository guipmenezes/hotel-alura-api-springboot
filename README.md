# REST API - Hotel Alura

## 🖥️ Tecnologias utilizadas:
* Java 17
* Spring Boot 3.0
* Maven
* Spring Boot Data JPA
* Docker Compose
* PostgreSQL

## Sobre o projeto:
O projeto é a extensão de um projeto anterior, o projeto do Hotel Alura. Como o Hotel Alura é um projeto desktop e que faz a conexão própria com a API de banco de dados, essa REST API foi utilizada para a possível criação de um Web App do Hotel Alura. Diferente do Hotel Alura desktop, essa API faz a conexão com o PostgresSQL, outro banco de dados relacional e utilizou-se o docker compose para fazer a orquestração do Container Docker da API.
<br>
<br>
Como o próprio título diz, a arquitetura utilizada foi a REST (Representational State Transfer) e pode ser representada na imagem abaixo:
<br>
<img align="center" src="Diagrama Backend API Hotel Alura.png"/>
<br>
<br>
### Sobre a API
* Na camada de API (API Layer), temos os Controllers de Reserva e de Hospede.
<br>
* Na camada de Negócio (Business Layer), temos as regras de negócio.
<br>
* Na camada DAO (DAO Layer), temos a camada de acesso aos dados realizada por uma DAO, no caso do projeto é feita por interfaces repositório utilizando a API JPA.
<br>
* Por último temos o banco de dados, que foi criado no PostgresSQL e possui duas tabelas relacionais, a tabela de Hospedes e a de Reserva, que possuem o id como chave primária e chave estrangeira, assim como o projeto Hotel Alura de desktop.


