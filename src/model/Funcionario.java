package model;

// Implementa a interface
public class Funcionario extends Pessoa implements IAutenticavel {

    private String cargo;
    private double salario;
    private String senha; // novo atributo para login

    public Funcionario(String nome, String cpf, String telefone, String cargo, double salario, String senha) {
        super(nome, cpf, telefone);
        this.cargo = cargo;
        this.salario = salario;
        // Atualizou o construtor pra receber senha
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    // Implementação do método interface
    @Override
    public boolean autenticar(String senhaInput) {
        // Verifica se a senha digitada é igual à senha do funcionário
        return this.senha.equals(senhaInput);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Detalhes do Funcionário ---");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Cargo: " + this.cargo);
        System.out.println("Salário: R$ " + this.salario);
    }
}