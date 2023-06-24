# APP-PRODUCT-STOCK-JAVA

<p>
Este é um exemplo de aplicação para demonstrar o uso do Testcontainers em testes de integração com Kafka e MongoDB. 
  O Testcontainers é uma biblioteca Java que permite criar e gerenciar contêineres Docker durante a execução dos testes, facilitando a realização de testes de integração que dependem de serviços externos, como bancos de dados, sistemas de mensageria, entre outros.
</p>

### Objetivo

O objetivo deste projeto é mostrar como utilizar o Testcontainers para criar e configurar contêineres Docker para o Kafka e o MongoDB, e realizar testes de integração com esses serviços.

### Pré-requisitos

Certifique-se de ter os seguintes pré-requisitos instalados em sua máquina:

- Java 17
- Maven
- Docker 

### Funcionalidades

- Configuração do ambiente de teste com o uso do Testcontainers
- Testes de integração com o Kafka
- Testes de integração com o MongoDB
- Exemplos de produtores e consumidores Kafka

### Como executar os testes

- Faça o clone deste repositório em sua máquina local.
- Navegue até o diretório raiz do projeto.
- Execute o seguinte comando para compilar e executar os testes:

```bash
mvn test
```
<p>
  Os testes de integração serão executados, e o Testcontainers irá gerenciar a criação e destruição dos contêineres Docker do Kafka e MongoDB durante a execução dos testes.
</p>

### Configuração do Testcontainers

A configuração do Testcontainers é feita utilizando a classe TestContainerConfiguration que é responsavel por subir um
container do MongoDB e um container do Kafka durante a execução dos testes integrados e como algumas propriedades
são inseridas de forma dinamica durante a subida do container.


```
    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.6");

    @Container
    private static final KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @DynamicPropertySource
    static void configDynamicProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("spring.kafka.producer.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("spring.kafka.consumer.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

```

## Tecnologias

<div style="display: inline_block">
  <img align="center" alt="java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" />
  <img align="center" alt="spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" />
  <img align="center" alt="docker" src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" />
</div>

### :sunglasses: Autor

Criado por Leonardo Rodrigues Dantas.

[![Linkedin Badge](https://img.shields.io/badge/-Leonardo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/leonardo-rodrigues-dantas/)](https://www.linkedin.com/in/leonardo-rodrigues-dantas/)
[![Gmail Badge](https://img.shields.io/badge/-leonardordnt1317@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:leonardordnt1317@gmail.com)](mailto:leonardordnt1317@gmail.com)

## Licença

Este projeto esta sobe a licença MIT.
