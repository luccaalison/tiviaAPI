# Documentação da API

Este documento fornece instruções sobre como configurar, executar e usar a API desenvolvida.

## Configuração

Para executar a aplicação, siga as etapas abaixo:

1. Certifique-se de ter o **Maven** instalado em seu sistema. Caso contrário, instale-o a partir deste link para instalação do Maven.
https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip

3. Navegue até o diretório do projeto em seu terminal.

4. Execute o seguinte comando para iniciar a aplicação Spring Boot:

    ```bash
    mvn spring-boot:run
    ```

5. Após a inicialização bem-sucedida, a API estará disponível em http://localhost:8080.

## Rotas da API

[**Collection do Postman**](https://raw.githubusercontent.com/luccaalison/tiviaAPI/main/apiPlanoSaude.postman_collection.json?raw=true)

A API possui as seguintes rotas:

### Beneficiários

- **GET /beneficiarios**: Retorna uma lista de todos os beneficiários cadastrados.

- **GET /beneficiarios/{id}**: Retorna os detalhes de um beneficiário específico com o ID fornecido.

- **POST /beneficiarios**: Cria um novo beneficiário. Corpo da requisição:

    ```json
    {
        "nome": "Nome do beneficiário",
        "telefone": "Número de telefone",
        "dataNascimento": "Data de nascimento (AAAA-MM-DD)"
    }
    ```

- **PUT /beneficiarios/{id}**: Atualiza os detalhes de um beneficiário existente com o ID fornecido. Corpo da requisição similar ao de criação.

- **DELETE /beneficiarios/{id}**: Exclui um beneficiário existente com o ID fornecido.

### Documentos

- **GET /documentos**: Retorna uma lista de todos os documentos cadastrados.

- **GET /documentos/{id}**: Retorna os detalhes de um documento específico com o ID fornecido.

- **POST /documentos**: Cria um novo documento. Corpo da requisição:

    ```json
    {
        "tipoDocumento": "Tipo de documento",
        "descricao": "Descrição do documento",
        "beneficiarioId": "ID do beneficiário associado"
    }
    ```

- **PUT /documentos/{id}**: Atualiza os detalhes de um documento existente com o ID fornecido. Corpo da requisição similar ao de criação.

- **DELETE /documentos/{id}**: Exclui um documento existente com o ID fornecido.

## Observações

Certifique-se de passar os valores corretos no corpo da requisição ao criar ou atualizar um beneficiário ou documento.
Ao criar um documento, o beneficiarioId deve corresponder ao ID de um beneficiário existente na base de dados.
