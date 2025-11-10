# Sistema "Aumigo" Pet Shop 🐶

![Java](https://img.shields.io/badge/Java-17-blue.svg?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-Concluído-green.svg?style=for-the-badge)

Aplicação de console em Java para gerenciamento de um Pet Shop. Este projeto foi desenvolvido como atividade avaliativa, aplicando os principais conceitos de **Programação Orientada a Objetos (POO)**.

---

## Contexto do Projeto

Este trabalho foi desenvolvido para a **Atividade da Unidade - 02** da disciplina de Linguagem de Programação 2, ministrada pelo Professor **Jefferson Gomes Dutra**.

O objetivo era criar uma aplicação funcional em Java que implementasse um CRUD completo e demonstrasse o uso correto de Encapsulamento, Herança, Polimorfismo e Tratamento de Exceções.

---

## ✨ Funcionalidades (Features)

O sistema possui um menu interativo que permite ao usuário gerenciar 6 entidades principais:

* **Gerenciamento de Clientes:** CRUD completo (Cadastrar, Listar, Atualizar, Remover, Buscar por CPF).
* **Gerenciamento de Funcionários:** CRUD completo.
* **Gerenciamento de Animais:** CRUD completo (com distinção de tipos, ex: Cachorro e Gato).
* **Gerenciamento de Produtos:** CRUD completo (controle de estoque e preço).
* **Gerenciamento de Serviços:** CRUD completo (ex: Banho, Tosa).
* **Gerenciamento de Agendamentos:** CRUD completo, associando Clientes, Animais, Funcionários e Serviços.
* **Tratamento de Exceções:** Validação de dados (ex: CPFs duplicados, IDs não encontrados, estoque negativo) com exceções personalizadas, garantindo que o programa não "quebre" durante o uso.
* **Demonstração de POO:** Um menu específico (Opção 7) demonstra o Polimorfismo das classes `Pessoa` e `Animal`.

---

## 🛠️ Tecnologias Utilizadas

* **[Java (JDK 17)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html):** Linguagem principal do projeto.
* **[IntelliJ IDEA](https://www.jetbrains.com/idea/):** IDE utilizada para o desenvolvimento.
* **[Git & GitHub](https://github.com/):** Para controle de versão.
* **[Mermaid](https://mermaid.live):** Para a geração do Diagrama UML.

---

## ⚙️ Como Executar o Projeto

Existem duas formas de executar o projeto:

### 1. Via IDE (Recomendado)

1.  **Pré-requisitos:** Ter o **Java JDK (versão 11 ou superior)** e uma IDE Java (como **IntelliJ** ou **Eclipse**) instalados.
2.  **Clone o repositório:**
    ```bash
    git clone [LINK PARA O REPOSITÓRIO DO SEU GRUPO NO GITHUB]
    ```
3.  **Abra o projeto:** Abra o projeto na sua IDE. O IntelliJ deve reconhecer a estrutura de pastas automaticamente.
4.  **Execute:** Encontre o arquivo `Main.java` dentro da pasta `src/main/` e execute o método `main()`.

### 2. Via Terminal (Compilando manualmente)

1.  **Clone o repositório** (como no passo anterior).
2.  **Navegue até a pasta `src`:**
    ```bash
    cd sistema-aumigo/src
    ```
3.  **Compile o projeto** (criando uma pasta `bin` para os `.class`):
    ```bash
    javac -d ../bin $(find . -name "*.java")
    ```
4.  **Execute a classe `Main`:**
    ```bash
    java -cp ../bin main.Main
    ```

---

## 📁 Estrutura de Pastas

O projeto foi organizado utilizando pacotes para separar as responsabilidades:

```sistema-aumigo/ 
├── src/ │ 
├── exception/ (Exceções personalizadas) │ 
│ ├── RecursoNaoEncontradoException.java │ 
│ └── ValidacaoException.java │ 
│ 
│ 
├── main/ (Classe principal com o menu) │ 
│ └── Main.java │ 
│ 
│ 
├── model/ (Classes de entidade e POO) │ 
│ ├── Pessoa.java │ 
│ ├── Cliente.java │ 
│ ├── Funcionario.java │ 
│ ├── Animal.java │ 
│ ├── Cachorro.java │ 
│ ├── Gato.java │ 
│ ├── Produto.java │ 
│ ├── Servico.java │ 
│ └── Agendamento.java │ 
│ 
│ └── repository/ (Classes de acesso a dados - CRUD) │ 
├── ClienteRepositorio.java │ 
├── FuncionarioRepositorio.java │ 
├── AnimalRepositorio.java │ 
├── ProdutoRepositorio.java │ 
├── ServicoRepositorio.java │ 
└── AgendamentoRepositorio.java │ 
├── diagrama.md (Diagrama UML) 
└── README.md (Este arquivo)
```

---

## 📊 Diagrama UML

O Diagrama de Classes UML, exigido na atividade, está no arquivo `diagrama.md`. Ele foi escrito em código **Mermaid**.

Para visualizar o diagrama renderizado, você pode:
1.  Instalar uma extensão (plugin) "Mermaid" no seu VSCode ou IntelliJ.
2.  Copiar o código do arquivo e colar no [**Editor Online do Mermaid**](https://mermaid.live).

---

## 👨‍💻 Autores (Grupo)

* [Maria Letícia Bandeira Ribeiro]
* [João Vitor Morais de Souza]
* [Ian Lucas Melo Trindade]
* [Lívia Rízia da Rocha Silva]
