import java.util.Scanner;

class Jogador {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    Jogador(int id, int altura, int peso, int anoNascimento, String nome, String universidade, String cidadeNascimento,
            String estadoNascimento) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
        this.universidade = universidade;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    Jogador() {

    }
}

public class Application {
    public static void main(String[] args) {
        Jogador jogador;

        jogador = new Jogador();
    }
}
