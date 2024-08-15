# Projeto prático 1: Automação residencial com dispositivos inteligentes

Esse repositório contém a Resolução da lista de exercício 1 da disciplina de SISTEMAS DISTRIBUÍDOS - STD029006

O objetivo é desenvolver um sistema que permite a comunicação e o controle eficiente de dispositivos inteligentes (como cortinas, TVs, lâmpadas, etc.) em uma residência O cenário proposto consiste em dispositivos inteligentes, um servidor hospedado na nuvem, e clientes (aplicativos para dispositivos móveis ou computadores), o sistema permite que, sempre que um novo dispositivo inteligente for adquirido, ele inicie um processo de registro no servidor. Este registro inclui informações como o tipo de dispositivo, identificador único e operações que ele é capaz de realizar.

O usuário, após registrar todos os dispositivos, pode enviar comandos para controlá-los individualmente ou em grupos, criando ambientes como "sala" ou "cozinha" para facilitar a interação. Todos os comandos são intermediados pelo servidor na nuvem. A solução também oferece a possibilidade de o usuário agrupar dispositivos por ambiente, facilitando o controle coletivo.Este projeto aplica os conceitos e habilidades em sistemas distribuídos, programação, uso de APIs RESTful e tecnologias de comunicação, com o objetivo de criar um sistema de automação residencial sofisticado, funcional e de fácil uso para o usuário final.

## Requisitos para compilação do projeto

- Gradle
- Jdk 17
- Docker

# Guia de execução de Controle de automação residêncial

## Iniciar os Serviços

Abra um terminal e navegue até a raiz do projeto, onde o arquivo `docker-compose.yml` está localizado. Execute o seguinte comando para iniciar todos os serviços definidos:

```bash
docker compose up
```

## Verificar os Serviços

Para verificar se todos os serviços estão funcionando corretamente, execute:

```bash
docker compose ps
```

## Acessando o Container do Cliente

Para acessar o container do cliente interativamente, execute:

```bash
docker exec -it entrega-04-client-1 ash
```

## Executando Comandos de Teste

Dentro do container do cliente, você pode usar o curl para interagir com a API,pois não foi implementado uma interface interativa para o cliente. Abaixo estão os comandos de teste que você pode executar, alguns comandos de exemplo neste arquivo [Doc](automationcenter/Readme.md)

## Execução do docker-compose

Após executar o docker-compose se obtera este resultado que cada device foi criado:

```
entrega-04-eletronicgate-1     | Response status code: 200
entrega-04-eletronicgate-1     | Response body: {"id":"0e98873c-20bd-4411-9eab-e7e67d42a6f8","type":"eletronicgate","functionalities":{}}
entrega-04-eletronicgate-1     | Device functionaliyties updated from API response:
entrega-04-television-1        | Response status code: 200
entrega-04-television-1        | Response body: {"id":"d9c99bbb-8254-489c-b250-5b8b6a8fc725","type":"television","functionalities":{"volume":30,"power":"off"}}
entrega-04-television-1        | Device functionaliyties updated from API response:
entrega-04-television-1        | volume: 30
entrega-04-television-1        | power: off
entrega-04-soundsystem-1       | Response status code: 200
entrega-04-soundsystem-1       | Response body: {"id":"8ab065e6-871a-43bb-b857-caf37d1d4a30","type":"soundsystem","functionalities":{"volume":30,"power":"off"}}
entrega-04-soundsystem-1       | Device functionaliyties updated from API response:
entrega-04-soundsystem-1       | volume: 30
entrega-04-soundsystem-1       | power: off
entrega-04-airconditioning-1   | Response status code: 200
entrega-04-airconditioning-1   | Response body: {"id":"7214cd94-96b3-432b-aab0-13a46cd5a293","type":"airconditioning","functionalities":{"temperature":22,"power":"off"}}
entrega-04-airconditioning-1   | Device functionaliyties updated from API response:
entrega-04-airconditioning-1   | temperature: 22
entrega-04-airconditioning-1   | power: off
entrega-04-lightbulb-1         | Response status code: 200
entrega-04-lightbulb-1         | Response body: {"id":"c5102b85-0ad5-4a08-8bf3-5d7cc5056a46","type":"lightbulb","functionalities":{"intensity":50,"power":"off"}}
entrega-04-lightbulb-1         | Device functionaliyties updated from API response:
entrega-04-lightbulb-1         | intensity: 50
entrega-04-lightbulb-1         | power: off
entrega-04-curtain-1           | Response status code: 200
entrega-04-curtain-1           | Response body: {"id":"01d32c1e-99f4-42e8-8fbc-702d564efd62","type":"curtain","functionalities":{"open":50}}
entrega-04-curtain-1           | Device functionaliyties updated from API response:
entrega-04-curtain-1           | open: 50
```

- Alteração de uma funcionalidade de um dispositivo por exemplo alterar a temperatura do ar condicionado para 23°C com id 7214cd94-96b3-432b-aab0-13a46cd5a293:

```bash
curl -X PUT http://automationcenter:8080/api/v1/devices/7214cd94-96b3-432b-aab0-13a46cd5a293/settings/temperature \
     -H "Content-Type: application/json" \
     -d '23'
```

- Criação de um ambiente chamado "sala" e adição de dispositivos a ele:

```bash
curl -X POST http://automationcenter:8080/api/v1/environments \
     -H "Content-Type: application/json" \
     -d '{"local":"sala","devices":[{"id":"d9c99bbb-8254-489c-b250-5b8b6a8fc725"},{"id":"8ab065e6-871a-43bb-b857-caf37d1d4a30"},{"id":"c5102b85-0ad5-4a08-8bf3-5d7cc5056a46"}]}'
```

- Desligar todos os dispositivos de um ambiente chamado "sala":

```bash
curl -X POST http://automationcenter:8080/api/v1/environments/sala/turn-off \
     -H "Content-Type: application/json"
```

- Excluir um ambiente chamado "sala":

```bash
curl -X DELETE http://automationcenter:8080/api/v1/environments/sala \
     -H "Content-Type: application/json"
```

- Log de atualização de informações do device pela API:

```
entrega-04-curtain-1           | Device functionaliyties updated from API response:
entrega-04-curtain-1           | open: 50
entrega-04-eletronicgate-1     | Device functionaliyties updated from API response:
entrega-04-television-1        | Device functionaliyties updated from API response:
entrega-04-television-1        | volume: 30
entrega-04-television-1        | power: off
entrega-04-soundsystem-1       | Device functionaliyties updated from API response:
entrega-04-soundsystem-1       | volume: 30
entrega-04-soundsystem-1       | power: off
entrega-04-airconditioning-1   | Device functionaliyties updated from API response:
entrega-04-airconditioning-1   | temperature: 22
entrega-04-airconditioning-1   | power: off
entrega-04-lightbulb-1         | Device functionaliyties updated from API response:
entrega-04-lightbulb-1         | intensity: 50
entrega-04-lightbulb-1         | power: off
```

<h1 align='center'>Autor</h1>

<a href="https://github.com/jeffersonbcr">
    <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/58866006?v=4" width="100px;" alt=""/><br/>
    <sub><b>Jefferson Botitano Calderon Romero</b></sub></a>

<br><br/>
