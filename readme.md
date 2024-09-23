# Agendamento Veicular API

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgresSQL**
- **Lombok**
- **Swagger OpenAPI**

## Instruções para Rodar o Projeto

### Pré-requisitos

- Java 17;
- Maven;
- PostgreSql;
- IDE (preferencialmente Intellij IDEA);
- Docker;
- Lombok (instale a extensão Lombok no seu IDE);
- Git;
- Postman, insomnia ou a plataforma de testes de sua preferencia;

### Configurando o banco de dados

1. Garanta que você tenha o PostgreSQL server instalado em sua máquina;

2. Altere as devidas permições no arquivo application.properties para condizerem com seu acesso ao PostgreSQL;
    ```
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
   ```

### Executando a API Localmente

1. Faça um clone do repositório:
    ```
   git clone https://github.com/Herick2D/agendamento-veicular-api.git
   ```
2. Navegue até a pasta do projeto:
    ```
    cd agendamento-veicular-api
   ```
3. Compile e rode a aplicação usando o Maven:
    ```
    ./mvnw spring-boot:run
   ```
4. A API estará disponível em http://localhost:8080.

### Executando a API via docker

1. Faça um clone do repositório:
    ```
   git clone https://github.com/Herick2D/agendamento-veicular-api.git
   ```
2. Navegue até a pasta do projeto:
    ```
    cd agendamento-veicular-api
   ```
3. Estando dentro da pasta da API use o seguinte comando:
    ```
    docker-compose up
   ```

### Documentação Swagger

Após rodar o projeto, você pode acessar a documentação da API pelo Swagger em:

```
http://localhost:8080/swagger-ui.html
```

### Testando a API

Usando a plataforma de testes de api de sua preferencia

1. **ENDPOINT CLIENTE**

- Cadastrar um novo cliente:

   Exemplo de payload:

   `POST/ cliente`

    ```json
   {
       "nome": "Roberto Silveira",
       "cpf": "320.331.850-44",
       "veiculos": [
           {
               "placa": "x8t5l",
               "modelo": "HB20",
               "marca": "Hyundai",
               "ano": 2021
           }
       ],
       "endereco": {
           "cep": "25931846",
           "numero": "230"
       }
   }
   ```
- Busca cliente por ID:
    ```
   GET/ cliente/{clienteId}
   ```
- Atualizar cliente:

   Exemplo de payload:

   `PUT/ cliente/{clienteId}`

   ```json
    {
    "nome": "Herick Moreira",
    "cpf": "477.389.900-07",
    "veiculos": [
        {
            "placa": "2h3uh",
            "modelo": "Civic",
            "marca": "Honda",
            "ano": 2003
        },
        {
            "placa": "4g9dk",
            "modelo": "Fusca",
            "marca": "Volkswagem",
            "ano": 1979
        }
    ],
    "endereco": {
        "cep": "25931670",
        "numero": "299"
    }
  }

- Remover veículo de um cliente:
    ```
    DELETE/ cliente/{clienteId}/veiculos
   ```
2. **ENDPOINT AGENDAMENTO**

- Agendar um novo serviço:

    Exemplo de payload:

    `POST/ agendamento`

    ```json
    {
    "clienteId": 1,
    "dataAgendamento": "2024-11-15T10:00:00",
    "descricaoServico": "troca de óleo"
    }
   ```
- Buscar todos os agendamentos de um cliente:

    `GET/ agendamento/{clienteId}`


- Buscar todos os agendamentos paginados:

    `GET/ agendamento`


- Buscar agendamento por período:

    `GET/ agendamento/periodo?inicio=2024-01-01T00:00:00&fim=2024-12-31T23:59:59`


- Atualizar agendamento:

    `PUT/ agendamento/{agendamentoId}`

    ```json
    {
    "clienteId": 1,
    "dataAgendamento": "2024-11-15T14:00:00",
    "descricaoServico": "balanceamento de pneus"
    }
    ```
  
### Observação
Agradeço pela oportunidade de criar esta API como um projeto avaliativo.
Estou à disposição para sanar quaisquer dívdas que você possa ter sobre o projeto.