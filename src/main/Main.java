package main;

import model.*;
import repository.*;
import util.GerenciadorArquivos;

import exception.RecursoNaoEncontradoException;
import exception.ValidacaoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
    private static final FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio();
    private static final AnimalRepositorio animalRepositorio = new AnimalRepositorio();
    private static final ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();
    private static final ServicoRepositorio servicoRepositorio = new ServicoRepositorio();
    private static final AgendamentoRepositorio agendamentoRepositorio = new AgendamentoRepositorio();

    private static final FornecedorRepositorio fornecedorRepositorio = new FornecedorRepositorio();
    private static final VacinaRepositorio vacinaRepositorio = new VacinaRepositorio();

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter dtfData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static PreferenciasUsuario preferencias;

    public static void main(String[] args) {

        carregarOuPedirPreferencias();

        System.out.println("\n==========================================");
        System.out.println("Bem-vindo ao Sistema Aumigo Pet Shop!");
        System.out.println("Usuário: " + preferencias.getNomeUsuario());
        System.out.println("Tema: " + preferencias.getTema().toUpperCase());
        System.out.println("==========================================");

        while (true) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();

            try {
                switch (opcao) {
                    case 1:
                        menuClientes();
                        break;
                    case 2:
                        menuFuncionarios();
                        break;
                    case 3:
                        menuAnimais();
                        break;
                    case 4:
                        menuProdutos();
                        break;
                    case 5:
                        menuServicos();
                        break;
                    case 6:
                        menuAgendamentos();
                        break;
                    case 7:
                        menuFornecedores();
                        break;
                    case 8:
                        menuVacinas();
                        break;
                    case 9:
                        menuDemonstracoes();
                        break;
                    case 0:
                        System.out.println("Obrigado por usar o sistema!");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (RecursoNaoEncontradoException | ValidacaoException e) {
                // Captura exceções personalizadas
                System.out.println("\n!!! ERRO: " + e.getMessage() + " !!!\n");
            } catch (Exception e) {
                System.out.println("\n!!! ERRO INESPERADO: " + e.getMessage() + " !!!\n");
                e.printStackTrace(); // depurar
            }

            pressioneEnterParaContinuar();
        }
    }

    private static void carregarOuPedirPreferencias() {
        preferencias = GerenciadorArquivos.carregarPreferencias();

        if (preferencias == null) {
            System.out.println(">> Configuração Inicial <<");
            String nome = lerString("Como gostaria de ser chamado? ");

            System.out.println("Escolha seu tema:");
            System.out.println("1 - Claro");
            System.out.println("2 - Escuro");
            int opTema = lerOpcao();
            String tema = (opTema == 2) ? "Escuro" : "Claro";

            GerenciadorArquivos.salvarPreferencias(nome, tema);
            preferencias = new PreferenciasUsuario(nome, tema);
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Gerenciar Clientes");
        System.out.println("2. Gerenciar Funcionários");
        System.out.println("3. Gerenciar Animais");
        System.out.println("4. Gerenciar Produtos");
        System.out.println("5. Gerenciar Serviços");
        System.out.println("6. Gerenciar Agendamentos");
        System.out.println("7. Gerenciar Fornecedores (NOVO)");
        System.out.println("8. Gerenciar Vacinas (NOVO)");
        System.out.println("9. Demonstrações (Polimorfismo e Interfaces)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void menuClientes() {
        System.out.println("\n--- Gerenciar Clientes ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Atualizar Cliente");
        System.out.println("4. Remover Cliente");
        System.out.println("5. Buscar Cliente por CPF");
        System.out.println("0. Voltar ao Menu Principal");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1: cadastrarCliente(); break;
            case 2: listarClientes(); break;
            case 3: atualizarCliente(); break;
            case 4: removerCliente(); break;
            case 5: buscarCliente(); break;
            case 0: System.out.println("Voltando ao menu principal..."); break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        String nome = lerString("Nome: ");
        String cpf = lerString("CPF (será usado como ID): ");
        String telefone = lerString("Telefone: ");
        String endereco = lerString("Endereço: ");

        Cliente cliente = new Cliente(nome, cpf, telefone, endereco);
        clienteRepositorio.adicionarCliente(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> clientes = clienteRepositorio.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente cliente : clientes) {
            cliente.exibirDetalhes(); // Polimorfismo
            System.out.println("---------------------");
        }
    }

    private static void atualizarCliente() {
        System.out.println("\n--- Atualizar Cliente ---");
        String cpf = lerString("Digite o CPF do cliente a ser atualizado: ");
        clienteRepositorio.buscarClientePorCpf(cpf);

        System.out.println("Digite os novos dados:");
        String nome = lerString("Novo Nome: ");
        String telefone = lerString("Novo Telefone: ");
        String endereco = lerString("Novo Endereço: ");

        Cliente clienteAtualizado = new Cliente(nome, cpf, telefone, endereco);
        clienteRepositorio.atualizarCliente(cpf, clienteAtualizado);

        System.out.println("Cliente atualizado com sucesso!");
    }

    private static void removerCliente() {
        System.out.println("\n--- Remover Cliente ---");
        String cpf = lerString("Digite o CPF do cliente a ser removido: ");
        clienteRepositorio.removerCliente(cpf);
        System.out.println("Cliente removido com sucesso!");
    }

    private static void buscarCliente() {
        System.out.println("\n--- Buscar Cliente por CPF ---");
        String cpf = lerString("Digite o CPF: ");
        Cliente cliente = clienteRepositorio.buscarClientePorCpf(cpf);
        System.out.println("Cliente encontrado:");
        cliente.exibirDetalhes();
    }

    private static void menuFuncionarios() {
        System.out.println("\n--- Gerenciar Funcionários ---");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Listar Funcionários");
        System.out.println("3. Atualizar Funcionário");
        System.out.println("4. Remover Funcionário");
        System.out.println("5. Buscar Funcionário por CPF");
        System.out.println("0. Voltar ao Menu Principal");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1: cadastrarFuncionario(); break;
            case 2: listarFuncionarios(); break;
            case 3: atualizarFuncionario(); break;
            case 4: removerFuncionario(); break;
            case 5: buscarFuncionario(); break;
            case 0: break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarFuncionario() {
        System.out.println("\n--- Cadastro de Funcionário ---");
        String nome = lerString("Nome: ");
        String cpf = lerString("CPF (será usado como ID): ");
        String telefone = lerString("Telefone: ");
        String cargo = lerString("Cargo: ");
        double salario = lerDouble("Salário: R$ ");

        // Senha para a interface IAutenticavel
        String senha = lerString("Crie uma senha de acesso: ");

        // Construtor atualizado
        Funcionario f = new Funcionario(nome, cpf, telefone, cargo, salario, senha);
        funcionarioRepositorio.adicionarFuncionario(f);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private static void listarFuncionarios() {
        System.out.println("\n--- Lista de Funcionários ---");
        List<Funcionario> funcionarios = funcionarioRepositorio.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            f.exibirDetalhes(); // Polimorfismo
            System.out.println("---------------------");
        }
    }

    private static void atualizarFuncionario() {
        System.out.println("\n--- Atualizar Funcionário ---");
        String cpf = lerString("Digite o CPF do funcionário a ser atualizado: ");
        funcionarioRepositorio.buscarFuncionarioPorCpf(cpf);

        System.out.println("Digite os novos dados:");
        String nome = lerString("Novo Nome: ");
        String telefone = lerString("Novo Telefone: ");
        String cargo = lerString("Novo Cargo: ");
        double salario = lerDouble("Novo Salário: R$ ");

        Funcionario f = new Funcionario(nome, cpf, telefone, cargo, salario, "");
        funcionarioRepositorio.atualizarFuncionario(cpf, f);

        System.out.println("Funcionário atualizado com sucesso!");
    }

    private static void removerFuncionario() {
        System.out.println("\n--- Remover Funcionário ---");
        String cpf = lerString("Digite o CPF do funcionário a ser removido: ");
        funcionarioRepositorio.removerFuncionario(cpf);
        System.out.println("Funcionário removido com sucesso!");
    }

    private static void buscarFuncionario() {
        System.out.println("\n--- Buscar Funcionário por CPF ---");
        String cpf = lerString("Digite o CPF: ");
        Funcionario f = funcionarioRepositorio.buscarFuncionarioPorCpf(cpf);
        System.out.println("Funcionário encontrado:");
        f.exibirDetalhes();
    }

    private static void menuAnimais() {
        System.out.println("\n--- Gerenciar Animais ---");
        System.out.println("1. Cadastrar Animal (Cachorro/Gato)");
        System.out.println("2. Listar Animais");
        System.out.println("3. Atualizar Animal");
        System.out.println("4. Remover Animal");
        System.out.println("5. Buscar Animal por ID");
        System.out.println("0. Voltar ao Menu Principal");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1: cadastrarAnimal(); break;
            case 2: listarAnimais(); break;
            case 3: atualizarAnimal(); break;
            case 4: removerAnimal(); break;
            case 5: buscarAnimal(); break;
            case 0: break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarAnimal() {
        System.out.println("\n--- Cadastro de Animal ---");
        int id = lerInt("ID (número único): ");
        String nome = lerString("Nome do pet: ");
        String raca = lerString("Raça: ");
        int idade = lerInt("Idade: ");

        String cpfDono = lerString("CPF do dono (cliente): ");
        Cliente dono = clienteRepositorio.buscarClientePorCpf(cpfDono);

        System.out.println("Tipo de animal: 1) Cachorro, 2) Gato");
        int tipo = lerOpcao();

        Animal animal;
        if (tipo == 1) {
            String porte = lerString("Porte (Pequeno/Medio/Grande): ");
            animal = new Cachorro(id, nome, raca, idade, dono, porte);
        } else if (tipo == 2) {
            boolean pelagemLonga = lerBoolean("Tem pelagem longa? (s/n): ");
            animal = new Gato(id, nome, raca, idade, dono, pelagemLonga);
        } else {
            System.out.println("Tipo inválido. Cadastro cancelado.");
            return;
        }

        animalRepositorio.adicionarAnimal(animal);
        System.out.println(animal.getClass().getSimpleName() + " cadastrado com sucesso!");
    }

    private static void listarAnimais() {
        System.out.println("\n--- Lista de Animais ---");
        List<Animal> animais = animalRepositorio.listarAnimais();
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
            return;
        }
        for (Animal a : animais) {
            a.exibirDetalhes(); // Polimorfismo
            System.out.println("Som: " + a.emitirSom()); // Polimorfismo
            System.out.println("---------------------");
        }
    }

    private static void atualizarAnimal() {
        System.out.println("\n--- Atualizar Animal ---");
        int id = lerInt("ID do animal a ser atualizado: ");
        System.out.println("Digite os novos dados:");
        String nome = lerString("Novo Nome: ");
        String raca = lerString("Nova Raça: ");
        int idade = lerInt("Nova Idade: ");
        String cpfDono = lerString("CPF do Novo Dono: ");
        Cliente novoDono = clienteRepositorio.buscarClientePorCpf(cpfDono);
        Animal animalAtualizado = new Cachorro(id, nome, raca, idade, novoDono, "");
        animalRepositorio.atualizarAnimal(id, animalAtualizado);
        System.out.println("Animal atualizado com sucesso!");
    }

    private static void removerAnimal() {
        System.out.println("\n--- Remover Animal ---");
        int id = lerInt("ID do animal a ser removido: ");
        animalRepositorio.removerAnimal(id);
        System.out.println("Animal removido com sucesso!");
    }

    private static void buscarAnimal() {
        System.out.println("\n--- Buscar Animal por ID ---");
        int id = lerInt("ID do animal: ");
        Animal a = animalRepositorio.buscarAnimalPorId(id);
        System.out.println("Animal encontrado:");
        a.exibirDetalhes();
    }

    private static void menuProdutos() {
        System.out.println("\n--- Gerenciar Produtos ---");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Atualizar Produto");
        System.out.println("4. Remover Produto");
        System.out.println("5. Buscar Produto por ID");
        System.out.println("0. Voltar ao Menu Principal");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1: cadastrarProduto(); break;
            case 2: listarProdutos(); break;
            case 3: atualizarProduto(); break;
            case 4: removerProduto(); break;
            case 5: buscarProduto(); break;
            case 0: break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarProduto() {
        System.out.println("\n--- Cadastro de Produto ---");
        int id = lerInt("ID (número único): ");
        String nome = lerString("Nome do produto: ");
        double preco = lerDouble("Preço: R$ ");
        int estoque = lerInt("Quantidade em estoque: ");

        Produto p = new Produto(id, nome, preco, estoque);
        produtoRepositorio.adicionarProduto(p);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        List<Produto> produtos = produtoRepositorio.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto p : produtos) {
            System.out.printf("ID: %d | Nome: %s | Preço: R$ %.2f | Estoque: %d\n",
                    p.getIdProduto(), p.getNome(), p.getPreco(), p.getEstoque());
            // Mostrando o uso da Interface IMonetario
            System.out.println("Imposto estimado (10%): R$ " + p.calcularImposto());
            System.out.println("---------------------");
        }
    }

    private static void atualizarProduto() {
        System.out.println("\n--- Atualizar Produto ---");
        int id = lerInt("ID do produto a ser atualizado: ");
        produtoRepositorio.buscarProdutoPorId(id);
        System.out.println("Digite os novos dados:");
        String nome = lerString("Novo Nome: ");
        double preco = lerDouble("Novo Preço: R$ ");
        int estoque = lerInt("Nova Quantidade em Estoque: ");
        Produto p = new Produto(id, nome, preco, estoque);
        produtoRepositorio.atualizarProduto(id, p);
        System.out.println("Produto atualizado com sucesso!");
    }

    private static void removerProduto() {
        System.out.println("\n--- Remover Produto ---");
        int id = lerInt("ID do produto a ser removido: ");
        produtoRepositorio.removerProduto(id);
        System.out.println("Produto removido com sucesso!");
    }

    private static void buscarProduto() {
        System.out.println("\n--- Buscar Produto por ID ---");
        int id = lerInt("ID do produto: ");
        Produto p = produtoRepositorio.buscarProdutoPorId(id);
        System.out.println("Produto encontrado:");
        System.out.printf("ID: %d | Nome: %s | Preço: R$ %.2f | Estoque: %d\n",
                p.getIdProduto(), p.getNome(), p.getPreco(), p.getEstoque());
    }

    private static void menuServicos() {
        System.out.println("\n--- Gerenciar Serviços ---");
        System.out.println("1. Cadastrar Serviço");
        System.out.println("2. Listar Serviços");
        System.out.println("3. Atualizar Serviço");
        System.out.println("4. Remover Serviço");
        System.out.println("5. Buscar Serviço por ID");
        System.out.println("0. Voltar ao Menu Principal");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1: cadastrarServico(); break;
            case 2: listarServicos(); break;
            case 3: atualizarServico(); break;
            case 4: removerServico(); break;
            case 5: buscarServico(); break;
            case 0: break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarServico() {
        System.out.println("\n--- Cadastro de Serviço ---");
        int id = lerInt("ID (número único): ");
        String nome = lerString("Nome do serviço (ex: Banho, Tosa): ");
        double preco = lerDouble("Preço: R$ ");
        int duracao = lerInt("Duração (em minutos): ");
        Servico s = new Servico(id, nome, preco, duracao);
        servicoRepositorio.adicionarServico(s);
        System.out.println("Serviço cadastrado com sucesso!");
    }

    private static void listarServicos() {
        System.out.println("\n--- Lista de Serviços ---");
        List<Servico> servicos = servicoRepositorio.listarServicos();
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }
        for (Servico s : servicos) {
            System.out.printf("ID: %d | Nome: %s | Preço: R$ %.2f | Duração: %d min\n",
                    s.getIdServico(), s.getNome(), s.getPreco(), s.getDuracaoEmMinutos());
            System.out.println("---------------------");
        }
    }

    private static void atualizarServico() {
        System.out.println("\n--- Atualizar Serviço ---");
        int id = lerInt("ID do serviço a ser atualizado: ");
        servicoRepositorio.buscarServicoPorId(id);
        System.out.println("Digite os novos dados:");
        String nome = lerString("Novo Nome: ");
        double preco = lerDouble("Novo Preço: R$ ");
        int duracao = lerInt("Nova Duração (em minutos): ");
        Servico s = new Servico(id, nome, preco, duracao);
        servicoRepositorio.atualizarServico(id, s);
        System.out.println("Serviço atualizado com sucesso!");
    }

    private static void removerServico() {
        System.out.println("\n--- Remover Serviço ---");
        int id = lerInt("ID do serviço a ser removido: ");
        servicoRepositorio.removerServico(id);
        System.out.println("Serviço removido com sucesso!");
    }

    private static void buscarServico() {
        System.out.println("\n--- Buscar Serviço por ID ---");
        int id = lerInt("ID do serviço: ");
        Servico s = servicoRepositorio.buscarServicoPorId(id);
        System.out.println("Serviço encontrado:");
        System.out.printf("ID: %d | Nome: %s | Preço: R$ %.2f | Duração: %d min\n",
                s.getIdServico(), s.getNome(), s.getPreco(), s.getDuracaoEmMinutos());
    }

    private static void menuAgendamentos() {
        System.out.println("\n--- Gerenciar Agendamentos ---");
        System.out.println("1. Criar Agendamento");
        System.out.println("2. Listar Agendamentos");
        System.out.println("3. Mudar Data/Hora");
        System.out.println("4. Concluir Agendamento");
        System.out.println("5. Cancelar Agendamento");
        System.out.println("0. Voltar ao Menu Principal");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1: cadastrarAgendamento(); break;
            case 2: listarAgendamentos(); break;
            case 3: atualizarAgendamento(); break;
            case 4: marcarAgendamentoConcluido(); break;
            case 5: removerAgendamento(); break;
            case 0: break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarAgendamento() {
        System.out.println("\n--- Novo Agendamento ---");
        int id = lerInt("ID (número único): ");
        LocalDateTime dataHora = lerDataHora("Data e Hora (dd/MM/yyyy HH:mm): ");
        String cpfCliente = lerString("CPF do Cliente: ");
        Cliente c = clienteRepositorio.buscarClientePorCpf(cpfCliente);
        int idAnimal = lerInt("ID do Animal: ");
        Animal a = animalRepositorio.buscarAnimalPorId(idAnimal);
        String cpfFunc = lerString("CPF do Funcionário: ");
        Funcionario f = funcionarioRepositorio.buscarFuncionarioPorCpf(cpfFunc);
        int idServico = lerInt("ID do Serviço: ");
        Servico s = servicoRepositorio.buscarServicoPorId(idServico);

        Agendamento ag = new Agendamento(id, dataHora, c, a, f, s);
        agendamentoRepositorio.adicionarAgendamento(ag);
        System.out.println("Agendamento criado com sucesso!");
    }

    private static void listarAgendamentos() {
        System.out.println("\n--- Lista de Agendamentos ---");
        List<Agendamento> agendamentos = agendamentoRepositorio.listarAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
            return;
        }
        for (Agendamento a : agendamentos) {
            System.out.printf("ID: %d | Data: %s | Concluído: %s\n",
                    a.getIdAgendamento(),
                    a.getDataHora().format(dtfHora),
                    a.isConcluido() ? "Sim" : "Não");
            System.out.printf("  Cliente: %s | Pet: %s\n", a.getCliente().getNome(), a.getAnimal().getNome());
            System.out.printf("  Serviço: %s | Func: %s\n", a.getServico().getNome(), a.getFuncionario().getNome());
            System.out.println("---------------------");
        }
    }

    private static void atualizarAgendamento() {
        System.out.println("\n--- Atualizar Data/Hora do Agendamento ---");
        int id = lerInt("ID do agendamento a ser atualizado: ");
        LocalDateTime novaDataHora = lerDataHora("Nova Data e Hora (dd/MM/yyyy HH:mm): ");
        Agendamento dadosAtualizados = new Agendamento(id, novaDataHora, null, null, null, null);
        agendamentoRepositorio.atualizarAgendamento(id, dadosAtualizados);
        System.out.println("Agendamento reagendado com sucesso!");
    }

    private static void marcarAgendamentoConcluido() {
        System.out.println("\n--- Concluir Agendamento ---");
        int id = lerInt("ID do agendamento a ser concluído: ");
        agendamentoRepositorio.marcarAgendamentoConcluido(id);
        System.out.println("Agendamento marcado como concluído!");
    }

    private static void removerAgendamento() {
        System.out.println("\n--- Remover (Cancelar) Agendamento ---");
        int id = lerInt("ID do agendamento a ser removido: ");
        agendamentoRepositorio.removerAgendamento(id);
        System.out.println("Agendamento removido/cancelado com sucesso!");
    }

    private static void menuFornecedores() {
        System.out.println("\n--- Gerenciar Fornecedores ---");
        System.out.println("1. Cadastrar Fornecedor");
        System.out.println("2. Listar Fornecedores");
        System.out.println("3. Atualizar Fornecedor");
        System.out.println("4. Remover Fornecedor");
        System.out.println("0. Voltar ao Menu Principal");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                int id = lerInt("ID: ");
                String emp = lerString("Empresa: ");
                String cnpj = lerString("CNPJ: ");
                String tel = lerString("Telefone: ");
                fornecedorRepositorio.adicionarFornecedor(new Fornecedor(id, emp, cnpj, tel));
                System.out.println("Fornecedor cadastrado!");
                break;
            case 2:
                for(Fornecedor f : fornecedorRepositorio.listarFornecedores()) {
                    f.exibirDetalhes(); System.out.println("---");
                }
                break;
            case 3:
                int idUp = lerInt("ID para atualizar: ");
                String nEmp = lerString("Nova Empresa: ");
                String nCnpj = lerString("Novo CNPJ: ");
                String nTel = lerString("Novo Telefone: ");
                fornecedorRepositorio.atualizarFornecedor(idUp, new Fornecedor(idUp, nEmp, nCnpj, nTel));
                System.out.println("Atualizado!");
                break;
            case 4:
                int idDel = lerInt("ID para remover: ");
                fornecedorRepositorio.removerFornecedor(idDel);
                System.out.println("Removido!");
                break;
            case 0: break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void menuVacinas() {
        System.out.println("\n--- Gerenciar Vacinas ---");
        System.out.println("1. Registrar Vacina");
        System.out.println("2. Listar Histórico");
        System.out.println("0. Voltar ao Menu Principal");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                int id = lerInt("ID da Vacina: ");
                String nome = lerString("Nome da Vacina (ex: V10): ");
                String lote = lerString("Lote: ");
                LocalDate data = lerData("Data Aplicação (dd/MM/yyyy): ");

                int idAnimal = lerInt("ID do Animal vacinado: ");
                Animal animal = animalRepositorio.buscarAnimalPorId(idAnimal);

                vacinaRepositorio.adicionarVacina(new Vacina(id, nome, lote, data, animal));
                System.out.println("Vacina registrada!");
                break;
            case 2:
                for(Vacina v : vacinaRepositorio.listarVacinas()) {
                    System.out.println("Vacina: " + v.getNomeVacina() + " | Data: " + v.getDataAplicacao().format(dtfData));
                    System.out.println("Animal: " + v.getAnimal().getNome() + " (Dono: " + v.getAnimal().getDono().getNome() + ")");
                    System.out.println("---");
                }
                break;
            case 0: break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void menuDemonstracoes() {
        System.out.println("\n--- Demonstrações do Sistema ---");
        System.out.println("1. Demonstrar Polimorfismo (Herança)");
        System.out.println("2. Demonstrar Interfaces (IAutenticavel e IMonetario)");
        System.out.println("0. Voltar");

        int op = lerOpcao();
        switch(op) {
            case 1: demonstrarPolimorfismo(); break;
            case 2: demonstrarInterfaces(); break;
            case 0: break;
        }
    }

    private static void demonstrarPolimorfismo() {
        System.out.println("\n--- Demonstração de Polimorfismo (Requisito Original) ---");

        Cliente c = new Cliente("Cliente Poli", "111", "1234", "Rua A");
        // Nota: Funcionario precisa de senha agora no construtor
        Funcionario f = new Funcionario("Func Poli", "222", "5678", "Veterinário", 5000.0, "123");
        List<Pessoa> pessoas = List.of(c, f);

        System.out.println("\nChamando 'exibirDetalhes()' de objetos 'Pessoa':");
        for (Pessoa p : pessoas) {
            p.exibirDetalhes();
            System.out.println("---");
        }

        Cachorro dog = new Cachorro(901, "Rex", "Vira-lata", 3, c, "Médio");
        Gato cat = new Gato(902, "Misty", "Siamês", 2, c, true);
        List<Animal> animais = List.of(dog, cat);

        System.out.println("\nChamando 'emitirSom()' de objetos 'Animal':");
        for (Animal a : animais) {
            System.out.println(a.getNome() + " faz: " + a.emitirSom());
        }
    }

    // Método novo para Interfaces
    private static void demonstrarInterfaces() {
        System.out.println("\n--- Demonstração de Interfaces (Novo Requisito) ---");

        // Teste IAutenticavel
        Funcionario f = new Funcionario("Tester", "000", "000", "Gerente", 5000, "1234");
        System.out.println("Senha correta é '1234'");
        System.out.println("Tentativa com 'abcd': " + (f.autenticar("abcd") ? "Logado" : "Negado"));
        System.out.println("Tentativa com '1234': " + (f.autenticar("1234") ? "Logado" : "Negado"));

        // Teste IMonetario
        Produto p = new Produto(1, "Ração Premium", 200.00, 10);
        Servico s = new Servico(1, "Cirurgia", 500.00, 120);
        System.out.println("Produto R$ 200,00 -> Imposto (10%): R$ " + p.calcularImposto());
        System.out.println("Serviço R$ 500,00 -> Imposto (5%): R$ " + s.calcularImposto());
    }

    // --- MÉTODOS AUXILIARES ---
    private static int lerOpcao() {
        try {
            String linha = scanner.nextLine();
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int lerInt(String prompt) {
        while (true) {
            try {
                String linha = lerString(prompt);
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(String prompt) {
        while (true) {
            try {
                String linha = lerString(prompt);
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número decimal (ex: 10.50).");
            }
        }
    }

    private static boolean lerBoolean(String prompt) {
        while (true) {
            String linha = lerString(prompt).toLowerCase();
            if (linha.equals("s") || linha.equals("sim")) return true;
            if (linha.equals("n") || linha.equals("nao") || linha.equals("não")) return false;
            System.out.println("Entrada inválida. Responda com 's' (sim) ou 'n' (não).");
        }
    }

    private static LocalDateTime lerDataHora(String prompt) {
        while (true) {
            try {
                String linha = lerString(prompt);
                return LocalDateTime.parse(linha, dtfHora);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use 'dd/MM/yyyy HH:mm'.");
            }
        }
    }

    // Método auxiliar novo para ler Data (LocalDate) usado em Vacinas
    private static LocalDate lerData(String prompt) {
        while (true) {
            try {
                String linha = lerString(prompt);
                return LocalDate.parse(linha, dtfData);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use 'dd/MM/yyyy'.");
            }
        }
    }

    private static void pressioneEnterParaContinuar() {
        System.out.println("\nPressione [ENTER] para continuar...");
        scanner.nextLine();
    }
}