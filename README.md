# User microservices

## Executar

### rabbit, auth, config e eureka
Dentro de cada um dos serviços executar o comando:
- ./mvnw spring-boot:run

### front
Dentro da pasta do projeto executar os comandos:
- npm install
- npm start

## Dependências
Os serviços `rabbit` e `auth` precisam do Redis e do RabbitMQ rodando localhost nas portas default de cada um (pode ser um container docker)

<p align="center">
  <img height="411" src="https://raw.githubusercontent.com/netodeolino/user-microservices/master/imgs/diagram.png">
</p>