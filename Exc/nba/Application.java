import java.util.Scanner;

class Jogador {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    Jogador(int id, String nome, int altura, int peso, int anoNascimento, String universidade, String cidadeNascimento,
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

    Jogador(int id, String nome, int altura, int peso, int anoNascimento) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
    }

    public void ImprimeDados() {
        System.out.print("[");
        System.out.println(id);
        System.out.println(" ## ");
        System.out.println(nome);
        System.out.println(" ## ");
        System.out.println(altura);
        System.out.println(" ## ");
        System.out.println(peso);
        System.out.println(" ## ");
        System.out.println(anoNascimento);
        System.out.println(" ## ");
        System.out.println(universidade.isEmpty() ? "nao informado" : universidade);
        System.out.println(" ## ");
        System.out.println(cidadeNascimento.isEmpty() ? "nao informado" : cidadeNascimento);
        System.out.println(" ## ");
        System.out.println(estadoNascimento.isEmpty() ? "nao informado" : estadoNascimento);
        System.out.println("]");
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }

    public void setAnoNascimento(int ano) {
        this.anoNascimento = ano;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setCidadeNascimento(String cidade) {
        this.cidadeNascimento = cidade;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setEstadoNascimento(String estado) {
        this.estadoNascimento = estado;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

}

public class Application {
    public static void main(String[] args) {

    }
}
