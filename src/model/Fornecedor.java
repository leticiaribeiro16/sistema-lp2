package model;

public class Fornecedor {
    // Encapsulamento
    private int idFornecedor;
    private String nomeEmpresa;
    private String cnpj;
    private String telefone;

    public Fornecedor(int idFornecedor, String nomeEmpresa, String cnpj, String telefone) {
        this.idFornecedor = idFornecedor;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Método auxiliar para facilitar a exibição
    public void exibirDetalhes() {
        System.out.println("ID: " + idFornecedor);
        System.out.println("Empresa: " + nomeEmpresa);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Telefone: " + telefone);
    }
}