package model;

import java.time.LocalDateTime;

public class Agendamento {

    private int idAgendamento;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private Animal animal;
    private Funcionario funcionario;
    private Servico servico;
    private boolean concluido;

    public Agendamento(int idAgendamento, LocalDateTime dataHora, Cliente cliente,
                       Animal animal, Funcionario funcionario, Servico servico) {
        this.idAgendamento = idAgendamento;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.animal = animal;
        this.funcionario = funcionario;
        this.servico = servico;
        this.concluido = false;
    }

    // Getters e Setters
    public int getIdAgendamento() {
        return idAgendamento;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Servico getServico() {
        return servico;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void marcarConcluido() {
        this.concluido = true;
    }
}