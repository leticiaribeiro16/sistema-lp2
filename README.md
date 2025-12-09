# Sistema "Aumigo" Pet Shop 🐶

![Java](https://img.shields.io/badge/Java-17-blue.svg?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-Concluído-green.svg?style=for-the-badge)

Aplicação de console em Java para gerenciamento de um Pet Shop. Este projeto foi desenvolvido como atividade avaliativa, aplicando conceitos avançados de **Programação Orientada a Objetos (POO)**.

---

## 📝 Contexto do Projeto

Este trabalho foi desenvolvido para a **Atividade da Unidade - 03** da disciplina de Linguagem de Programação 2, ministrada pelo Professor **Jefferson Gomes Dutra**.

O objetivo foi expandir a aplicação anterior para incluir **11 classes**, **Interfaces**, **Persistência de Arquivos** e validações robustas.

---

## ✨ Funcionalidades (Features)

O sistema possui um menu interativo que permite ao usuário gerenciar **8 entidades** e recursos avançados:

### 📦 CRUDs (Gerenciamento)
* **Clientes:** Cadastro completo com validação de CPF.
* **Funcionários:** Cadastro com senha de acesso (Implementação de Login).
* **Animais:** Cadastro com herança e polimorfismo (Cachorro/Gato).
* **Produtos:** Controle de estoque e cálculo de impostos via interface.
* **Serviços:** Agendamento de serviços com cálculo de taxas.
* **Agendamentos:** Integração entre Cliente, Animal, Funcionário e Serviço.
* **Fornecedores (Novo):** Cadastro de empresas parceiras com validação de CNPJ.
* **Vacinas (Novo):** Histórico de vacinação vinculado ao animal.

### ⚙️ Recursos Técnicos
* **Persistência de Arquivos:** O sistema salva e carrega automaticamente as preferências do usuário (Nome de Exibição e Tema Claro/Escuro) em um arquivo `.txt`.
* **Interfaces:**
    * `IAutenticavel`: Implementada por Funcionários para permitir login seguro.
    * `IMonetario`: Implementada por Produtos e Serviços para padronizar cálculos financeiros.
* **Tratamento de Exceções:** Exceções personalizadas (`RecursoNaoEncontradoException`, `ValidacaoException`) para impedir dados duplicados ou inválidos.

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

O projeto foi organizado utilizando pacotes para separar as responsabilidades e contém mais de **11 classes**:
 ```
sistema-aumigo/
├── src/ │
├── exception/ (Exceções personalizadas) │
│ ├── RecursoNaoEncontradoException.java │
│ └── ValidacaoException.java │
│
├── main/ (Classe principal) │
│ └── Main.java │
│
├── model/ (Entidades e Interfaces) │
│ ├── Pessoa.java (Abstrata) │
│ ├── Animal.java (Abstrata) │
│ ├── Cliente.java │
│ ├── Funcionario.java (Implementa IAutenticavel) │
│ ├── Cachorro.java │
│ ├── Gato.java │
│ ├── Produto.java (Implementa IMonetario) │
│ ├── Servico.java (Implementa IMonetario) │
│ ├── Agendamento.java │
│ ├── Fornecedor.java (Novo) │
│ ├── Vacina.java (Novo) │
│ ├── PreferenciasUsuario.java │
│ ├── IAutenticavel.java (Interface) │
│ └── IMonetario.java (Interface) │
│
├── repository/ (Camada de Dados - CRUD) │
│ ├── ClienteRepositorio.java │
│ ├── FuncionarioRepositorio.java │
│ ├── AnimalRepositorio.java │
│ ├── ProdutoRepositorio.java │
│ ├── ServicoRepositorio.java │
│ ├── AgendamentoRepositorio.java │
│ ├── FornecedorRepositorio.java (Novo) │
│ └── VacinaRepositorio.java (Novo) │
│
├── util/ (Utilitários)
│ └── GerenciadorArquivos.java (Persistência txt) │
├── diagrama.md (Diagrama UML atualizado)
└── README.md (Este arquivo)
```

---

## 📊 Diagrama UML

O Diagrama de Classes UML atualizado, exigido na atividade, está no arquivo `diagrama.md` na raiz do projeto. Ele foi escrito em código **Mermaid**.

Para visualizar o diagrama renderizado, você pode:
1.  Instalar uma extensão (plugin) "Mermaid" no seu editor de código.
2.  Copiar o código do arquivo e colar no [**Editor Online do Mermaid**](https://mermaid.live).

---

## 👨‍💻 Autores (Grupo)

* [Maria Letícia Bandeira Ribeiro]
* [João Vitor Morais de Souza]
* [Ian Lucas Melo Trindade]
* [Lívia Rízia da Rocha Silva]
