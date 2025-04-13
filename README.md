# Sistema de Gestão de Estacionamento

Esse projeto é uma API RESTful desenvolvida com Spring Boot para gerenciar veículos em um estacionamento.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Dozer
- Maven

## Funcionalidades

- Cadastro de veículos
- Listagem de veículos
- Filtro com base em uma parte da placa
- Busca específica por placa
- Remoção de veículos, com um pequeno "relatório" de dados como o valor a ser pago, e o tempo gasto
- Validações específicas e personalizadas
- Tratamento de Exceptions e personalização delas
- Atualização dos dados de um determinado veículo com base em sua placa
- DTOs para encapsulamento e segurança

## Regras de Negócio

- Só é possível registrar um veículo que tenha placa válida, e que está placa ainda não exista no sistema.
- O valor a pagar é calculado com base no tempo entre entrada e saída.
- A placa é normalizada para garantir consistência no banco de dados(remoção de hífens, espaços e letras minúsculas).

## Principais Endpoints

| Método | URL                        | Descrição                                       |
|--------|----------------------------|-------------------------------------------------|
| POST   | `/vehicle`                 | Cadastra um novo veículo                        |
| GET    | `/vehicle`                 | Lista todos os veículos                         |
| GET    | `/vehicle?filter=ABC`      | Filtra veículos com placas que contenham "ABC"  |
| GET    | `/vehicle/{licensePlate}` | Busca um veículo específico por placa           |
| PUT    | `/vehicle/{licensePlate}` | Atualiza os dados de um veículo                 |
| DELETE | `/vehicle/{licensePlate}` | Remove o veículo e retorna relatório de saída   |

## Tratamento de Erros

A API possui um sistema de tratamento de exceções personalizadas para garantir que as respostas sejam claras, padronizadas e informativas para o consumidor.

| Exceção                         | Descrição                                                             |
|---------------------------------|-----------------------------------------------------------------------|
| BusinessException               | Utilizada para regras de negócio, como formato de placa inválido      |
| IllegalArgumentException        | Usada quando argumentos inválidos são passados na requisição          |
| ResourceNotFoundException       | Disparada quando nenhum veículo é encontrado com a placa informada    |
| DuplicatedLicensePlateException | Lançada ao tentar cadastrar uma placa que já existe no banco de dados |

## Feito por

Luiz Eduardo Gurgel 
