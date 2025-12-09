package model;

public class PreferenciasUsuario {
    private String nomeUsuario;
    private String tema; // "Claro" ou "Escuro"

    public PreferenciasUsuario(String nomeUsuario, String tema) {
        this.nomeUsuario = nomeUsuario;
        this.tema = tema;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getTema() {
        return tema;
    }
}