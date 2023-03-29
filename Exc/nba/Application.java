import java.util.Scanner;

class Jogador {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento,
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
        System.out.println(universidade.isEmpty() ? "nao informado" : universidade);
        System.out.println(" ## ");
        System.out.println(anoNascimento);
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

    public void ler() {
        System.out.println("entre com os dados");
        Scanner scan = new Scanner(System.in);
        String entradaDados;

        while (scan.hasNext()) {
            if ()
            entradaDados = scan.nextLine();
            String[] valores = entradaDados.split(",");
            System.out.println(valores);
            this.id = Integer.parseInt(valores[0]);
            this.nome = valores[1];
            this.altura = Integer.parseInt(valores[2]);
            this.peso = Integer.parseInt(valores[3]);
            this.universidade = valores[4].equals("") ? "nao informado" : valores[4];
            this.anoNascimento = Integer.parseInt(valores[5]);
            this.cidadeNascimento = valores[6].isEmpty() ? "nao informado" : valores[6];
            this.estadoNascimento = valores[7].isEmpty() ? "nao informado" : valores[7];
            ImprimeDados();
        }
    }

    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }

}

public class Application {
    public static void main(String[] args) {
        Jogador newJogador = new Jogador();
        newJogador.ler();
    }
}
