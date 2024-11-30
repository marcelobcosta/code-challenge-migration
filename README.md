
# DummyJSON Client - Java 17 e Spring Boot 3.2.5

## Descrição do Projeto

Este projeto foi desenvolvido como parte de um desafio técnico, com o objetivo de consumir a API pública [DummyJSON](https://dummyjson.com/docs/products) e realizar operações de consulta de produtos. O microsserviço foi implementado usando **Java 17** e **Spring Boot 3.2.5**, e oferece funcionalidades de consulta de produtos, integração com WebClient, validação de dados e testes unitários.

## Funcionalidades

- **Consulta de Produtos**: A aplicação permite buscar todos os produtos ou consultar um produto específico por ID, realizando chamadas para a API do DummyJSON e retornando os dados formatados.
- **Integração com `WebClient`**: Utiliza `WebClient` para realizar chamadas HTTP assíncronas para a API DummyJSON, permitindo escalabilidade e melhor desempenho.
- **Validação de Dados**: Usa Bean Validation (`jakarta.validation`) para validar os dados de entrada nas requisições, garantindo integridade e segurança.
- **Gestão de Dependências**: O Spring Boot é configurado com `@Autowired` para a injeção de dependências, facilitando a manutenção do código.
- **Testes Unitários e Funcionais**: Inclui testes unitários com `@SpringBootTest` e testes funcionais realizados com o Thunder Client para verificar a integração com a API.


## Estrutura do Projeto

```bash
dummyjson-client
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.dummyjsonclient
│   │   │       ├── DummyJsonClientApplication.java
│   │   │       ├── config
│   │   │       │   └── ValidationConfig.java
│   │   │       │   └── WebClientConfig.java
│   │   │       ├── controller
│   │   │       │   └── ProductController.java
│   │   │       ├── dto
│   │   │       │   └── Product.java
│   │   │       │   └── ProductResponse.java
│   │   │       ├── service
│   │   │       │   └── ProductService.java
│   │   └── resources
│   │       └── application.yaml
│   └── test
│       ├── java
│       │   └── com.example.dummyjsonclient
│       │       ├── config
│       │       │   └── ValidationConfigTest.java
│       │       │   └── WebClientConfigTest.java
│       │       └── controller
│       │       │   └── ProductControllerTest.java
│       │       ├── dto
│       │       │   └── ProductTest.java
│       │       │   └── ProductResponseTest.java
│       │       └── service
│       │           └── ProductServiceTest.java
│       └── resources
└── pom.xml
```

## Tecnologias usadas
- Java 17: Versão do Java utilizada para garantir compatibilidade com as versões mais recentes e recursos modernos de desenvolvimento.
- Spring Boot 3.2.5: Framework utilizado para simplificar a configuração e o desenvolvimento de microsserviços.
- WebClient: Utilizado para realizar chamadas HTTP assíncronas à API DummyJSON.
- Spring Boot Test (@SpringBootTest): Usado para realizar testes de integração e garantir que os componentes estão funcionando corretamente.
- Maven: Gerenciador de dependências e build automation.


## Passos para Executar o Projeto

### Pré-requisitos

- **Java 17**
- **Maven 3.8.x**
- **Dependências do projeto** (especificadas no pom.xml).

### Executar a Aplicação

1. Clone o repositório:

    ```bash
    git clone https://github.com/marcelobcosta/code-challenge-migration
    cd dummyjson-client
    ```

2. Compile e execute o projeto:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3. Acesse o serviço:

    O serviço estará disponível em `http://localhost:8080` Você pode testar os endpoints da API através de um navegador ou cliente HTTP.

4. Verifique a disponibilidade do microsserviço:

    A saúde do microsserviço pode ser verificada no endpoint `http://localhost:8080/actuator/health`.


### Executar a Aplicação

- **URL da API:** No arquivo `application.yaml`, você pode configurar o URL da API DummyJSON, caso queira rodar o projeto em outro ambiente.

```bash
api:
  url: https://dummyjson.com
```

### Executar Testes

Para executar os testes unitários:

```bash
mvn clean test
```

### Testes Funcionais com Thunder Client
Uma collection de requisições à API foi adicionada no projeto, usando o Thunder Client do VSCode. Para testar a API:

- Abra o Thunder Client no VSCode.
- Importe a collection de requisições [DummyJson](thunder-collection_DummyJson.json).
- Execute as requisições para testar os endpoints da API.

![alt text](image.png)
![alt text](image-1.png)
![alt text](image-2.png)
![alt text](image-3.png)

### Para rodar em outro ambiente

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=<nome-do-ambiente>
```

## Extras

- Entregar o projeto em container será um diferencial.
