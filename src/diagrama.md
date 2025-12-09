---
title: Diagrama de Classes - Sistema Aumigo
---
classDiagram
direction TB

    %% ==========================================
    %% INTERFACES
    %% ==========================================
    class IAutenticavel {
        <<interface>>
        +autenticar(String senha) boolean
    }

    class IMonetario {
        <<interface>>
        +calcularImposto() double
    }

    %% ==========================================
    %% NAMESPACE MODEL
    %% ==========================================
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
            -String senha
            +autenticar(String) boolean
            +exibirDetalhes() void
        }

        class Animal {
            <<abstract>>
            -int idAnimal
            -String nome
            -String raca
            -int idade
            -Cliente dono
            +emitirSom()* String
            +exibirDetalhes() void
        }

        class Cachorro {
            -String porte
            +emitirSom() String
        }

        class Gato {
            -boolean pelagemLonga
            +emitirSom() String
        }

        class Produto {
            -int idProduto
            -String nome
            -double preco
            -int estoque
            +darBaixaEstoque(int) void
            +calcularImposto() double
        }

        class Servico {
            -int idServico
            -String nome
            -double preco
            -int duracaoEmMinutos
            +calcularImposto() double
        }

        class Agendamento {
            -int idAgendamento
            -LocalDateTime dataHora
            -boolean concluido
            +marcarConcluido() void
        }

        class Fornecedor {
            -int idFornecedor
            -String nomeEmpresa
            -String cnpj
            -String telefone
        }

        class Vacina {
            -int idVacina
            -String nomeVacina
            -String lote
            -LocalDate dataAplicacao
        }

        class PreferenciasUsuario {
            -String nomeUsuario
            -String tema
        }
    }

    %% ==========================================
    %% NAMESPACE UTIL
    %% ==========================================
    namespace util {
        class GerenciadorArquivos {
            +salvarPreferencias(String, String)$ void
            +carregarPreferencias()$ PreferenciasUsuario
        }
    }

    %% ==========================================
    %% NAMESPACE REPOSITORY
    %% ==========================================
    namespace repository {
        class ClienteRepositorio
        class FuncionarioRepositorio
        class AnimalRepositorio
        class ProdutoRepositorio
        class ServicoRepositorio
        class AgendamentoRepositorio
        class FornecedorRepositorio
        class VacinaRepositorio
    }

    %% ==========================================
    %% NAMESPACE EXCEPTION
    %% ==========================================
    namespace exception {
        class RecursoNaoEncontradoException
        class ValidacaoException
    }

    %% ==========================================
    %% RELACIONAMENTOS
    %% ==========================================

    %% Herança
    Pessoa <|-- Cliente
    Pessoa <|-- Funcionario
    Animal <|-- Cachorro
    Animal <|-- Gato

    %% Realização (Interfaces)
    IAutenticavel <|.. Funcionario : Implementa
    IMonetario <|.. Produto : Implementa
    IMonetario <|.. Servico : Implementa

    %% Associações
    Animal --> "1" Cliente : Dono
    Vacina --> "1" Animal : Vacinado
    Agendamento --> "1" Cliente
    Agendamento --> "1" Animal
    Agendamento --> "1" Funcionario
    Agendamento --> "1" Servico

    %% Dependências Repositórios
    ClienteRepositorio o-- Client
    FuncionarioRepositorio o-- Funcionario
    AnimalRepositorio o-- Animal
    ProdutoRepositorio o-- Produto
    ServicoRepositorio o-- Servico
    AgendamentoRepositorio o-- Agendamento
    FornecedorRepositorio o-- Fornecedor
    VacinaRepositorio o-- Vacina

    %% Dependências Diversas
    GerenciadorArquivos ..> PreferenciasUsuario : Persiste
    ClienteRepositorio ..> ValidacaoException : Lanca
    ClienteRepositorio ..> RecursoNaoEncontradoException : Lanca