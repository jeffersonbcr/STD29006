# Comandos de testes para utilizar da API

Caso estiver testando localmente substituir endereço ``http://automationcenter:8080`` por ``http://localhost:8080``.

## Registrar um Novo Dispositivo

```bash
curl -X POST http://automationcenter:8080/api/v1/devices \
     -H "Content-Type: application/json" \
     -d '{"id": "123", "type": "AC", "minValue": 16, "maxValue": 30, "functionalities": {"power": "on", "temperature": 22}}'

```

## Consultar todos Dispositivos

```bash
curl http://automationcenter:8080/api/v1/devices
```

## Consultar um Dispositivo

```bash
curl http://automationcenter:8080/api/v1/devices/device1

```

## Atualizar a Funcionalidade de um Dispositivo

```bash
curl -X PUT http://automationcenter:8080/api/v1/devices/device1/settings/temperature \
     -H "Content-Type: application/json" \
     -d '24'

```

## Criar um Novo Ambiente

```bash
curl -X POST http://automationcenter:8080/api/v1/environments \
     -H "Content-Type: application/json" \
     -d '{"local": "sala"}'

```

## Adicionar Dispositivo a um Ambiente

```bash
curl -X PUT http://automationcenter:8080/api/v1/environments/sala/devices \
     -H "Content-Type: application/json" \
     -d '{"id": "123", "type": "AC", "minValue": 16, "maxValue": 30, "functionalities": {"power": "on", "temperature": 22}}'

```

## Criar um Ambiente com Dispositivos

```bash
curl -X POST http://automationcenter:8080/api/v1/environments/devices \
     -H "Content-Type: application/json" \
     -d '{"local": "quarto", "deviceIds": ["device1"]}'

```

## Desligar Todos os Dispositivos de um Ambiente


```bash
curl -X POST http://automationcenter:8080/api/v1/environments/sala/turn-off

```

## Listar Ambientes

```bash
curl http://automationcenter:8080/api/v1/environments

```

## Excluir um Ambiente

```bash
curl -X DELETE http://automationcenter:8080/api/v1/environments/sala

```