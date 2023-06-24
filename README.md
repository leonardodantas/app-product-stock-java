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

### Exemplo de execução de teste

<p>
  O trecho de código a seguir apresenta um exemplo de teste integrado que utiliza a biblioteca TestContainer. Nesse teste, é realizado uma chamada REST utilizando a classe TestRestTemplate, onde é enviado um objeto de produto recuperado usando a classe GetMockJson. Durante a execução do teste, o produto é persistido no banco de dados MongoDB e também enviado para uma fila Kafka.

O objetivo desse teste é verificar se o fluxo completo de salvamento do produto está funcionando corretamente, desde a persistência no banco de dados até o envio para o Kafka.
</p>

```
    @Test
    @Order(1)
    @DisplayName(value = "Salvando um novo produto com sucesso")
    public void shouldCreateProduct() {

        final var request = GetMockJson.getObject("products/product_to_save_success_request", ProductCreateRequest.class);

        final var response =
                restTemplate.postForEntity(getBaseURL() + "v1/product", request, ProductResponse.class);

        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());

        final var productResponse = response.getBody();

        assertNotNull(productResponse.id(), "ID validado");
        assertEquals(request.code().toUpperCase(), productResponse.code(), "Codigo validado em upperCase");
        assertEquals(request.name(), productResponse.name(), "Nome validado");
        assertEquals(request.description(), productResponse.description(), "Descrição validado");
        assertEquals(request.price().compareTo(productResponse.price()), 0, "Preço validado");
        assertEquals(3, request.details().size());

        final var productExistInDataBase = mongoTemplate.exists(Query.query(Criteria.where("id")
                .is(productResponse.id()).and("active").is(true)), ProductDocument.class);

        assertTrue(productExistInDataBase, "Produto inserido na base de dados");

        mongoTemplate.dropCollection("products");
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
