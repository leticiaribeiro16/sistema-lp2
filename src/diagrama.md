---
config:
  theme: default
---
classDiagram
direction TB
title Diagrama de Classes - Sistema Aumigo

    %% --- Namespace Model (Entidades) ---
    namespace model {
        class Pessoa {
            <<abstract>>
            -String nome
            -String cpf
            -String telefone
            +exibirDetalhes()* void
        }

        class Cliente {
            -String endereco
            +exibirDetalhes() void
        }

        class Funcionario {
            -String cargo
            -double salario
            +exibirDetalhes() void
        }

        class Animal {
            <<abstract>>
            -int idAnimal
            -String nome
            -String raca
            -int idade
            +emitirSom()* String
            +exibirDetalhes() void
        }

        class Cachorro {
            -String porte
            +emitirSom() String
            +exibirDetalhes() void
        }

        class Gato {
            -boolean pelagemLonga
            +emitirSom() String
            +exibirDetalhes() void
        }

        class Produto {
            -int idProduto
            -String nome
            -double preco
            -int estoque
        }

        class Servico {
            -int idServico
            -String nome
            -double preco
            -int duracaoEmMinutos
        }

        class Agendamento {
            -int idAgendamento
            -LocalDateTime dataHora
            -boolean concluido
        }
    }

    %% --- Namespace Repository (CRUD) ---
    namespace repository {
        class ClienteRepositorio {
            -List~Cliente~ clientes
            +adicionarCliente(Cliente) void
            +buscarClientePorCpf(String) Cliente
            +removerCliente(String) void
        }
        class FuncionarioRepositorio {
            -List~Funcionario~ funcionarios
            +adicionarFuncionario(Funcionario) void
        }
        class AnimalRepositorio {
            -List~Animal~ animais
            +adicionarAnimal(Animal) void
        }
        class ProdutoRepositorio {
            -List~Produto~ produtos
            +adicionarProduto(Produto) void
        }
        class ServicoRepositorio {
            -List~Servico~ servicos
            +adicionarServico(Servico) void
        }
        class AgendamentoRepositorio {
            -List~Agendamento~ agendamentos
            +adicionarAgendamento(Agendamento) void
        }
    }

    %% --- Namespace Exception ---
    namespace exception {
        class RuntimeException {
            <<Java Built-in>>
        }
        class RecursoNaoEncontradoException {
            +RecursoNaoEncontradoException(String) void
        }
        class ValidacaoException {
            +ValidacaoException(String) void
        }
    }

    %% --- RELACIONAMENTOS ---

    %% Herança (Requisito 3)
    Pessoa <|-- Cliente : "Herda"
    Pessoa <|-- Funcionario : "Herda"
    Animal <|-- Cachorro : "Herda"
    Animal <|-- Gato : "Herda"

    %% Herança das Exceções (Requisito 6)
    RuntimeException <|-- RecursoNaoEncontradoException
    RuntimeException <|-- ValidacaoException

    %% Associação/Composição (Repositórios guardam modelos)
    ClienteRepositorio o-- "0..*" Cliente : "Gerencia"
    FuncionarioRepositorio o-- "0..*" Funcionario : "Gerencia"
    AnimalRepositorio o-- "0..*" Animal : "Gerencia"
    ProdutoRepositorio o-- "0..*" Produto : "Gerencia"
    ServicoRepositorio o-- "0..*" Servico : "Gerencia"
    AgendamentoRepositorio o-- "0..*" Agendamento : "Gerencia"
    
    %% Associações entre modelos
    Animal --> "1" Cliente : "tem como dono"
    Agendamento --> "1" Cliente : "associado a"
    Agendamento --> "1" Animal : "associado a"
    Agendamento --> "1" Funcionario : "associado a"
    Agendamento --> "1" Servico : "associado a"

    %% Dependência (Repositórios usam Exceções)
    ClienteRepositorio ..> RecursoNaoEncontradoException : "Lança"
    ClienteRepositorio ..> ValidacaoException : "Lança"
