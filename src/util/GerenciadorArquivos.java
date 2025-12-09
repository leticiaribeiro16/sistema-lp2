package util;

import model.PreferenciasUsuario;
import java.io.*;

public class GerenciadorArquivos {

    private static final String NOME_ARQUIVO = "preferencias.txt";

    // Requisito: Salvar as informações
    public static void salvarPreferencias(String nome, String tema) {
        // O bloco 'try-with-resources' fecha o arquivo automaticamente
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            writer.write(nome);
            writer.newLine(); // Pula para a próxima linha
            writer.write(tema);
            System.out.println("Preferências salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar preferências: " + e.getMessage());
        }
    }

    // Requisito: Carregar as informações
    public static PreferenciasUsuario carregarPreferencias() {
        File arquivo = new File(NOME_ARQUIVO);

        // Se o arquivo não existir (primeira vez que roda), retorna null
        if (!arquivo.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String nome = reader.readLine();
            String tema = reader.readLine();

            if (nome != null && tema != null) {
                return new PreferenciasUsuario(nome, tema);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de preferências: " + e.getMessage());
        }
        return null;
    }
}