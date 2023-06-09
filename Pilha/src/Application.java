import java.util.Scanner;
import java.util.ArrayList;

class Jogador {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;
    private ArrayList<Jogador> listJogadores = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

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
        System.out.print(id);
        System.out.print(" ## ");
        System.out.print(nome);
        System.out.print(" ## ");
        System.out.print(altura);
        System.out.print(" ## ");
        System.out.print(peso);
        System.out.print(" ## ");
        System.out.print(anoNascimento);
        System.out.print(" ## ");
        System.out.print(universidade.isEmpty() ? "nao informado" : universidade);
        System.out.print(" ## ");
        System.out.print(cidadeNascimento.isEmpty() ? "nao informado" : cidadeNascimento);
        System.out.print(" ## ");
        System.out.print(estadoNascimento.isEmpty() ? "nao informado" : estadoNascimento);
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
        String entradaDados;
        entradaDados = scan.nextLine();
        while (!entradaDados.equals("FIM")) {
            String[] valores = entradaDados.split(",", -1);
            Jogador jogador = new Jogador();
            jogador.setId(Integer.parseInt(valores[0]));
            jogador.setNome(valores[1]);
            jogador.setAltura(Integer.parseInt(valores[2]));
            jogador.setPeso(Integer.parseInt(valores[3]));
            jogador.setUniversidade(valores[4].isEmpty() ? "nao informado" : valores[4]);
            jogador.setAnoNascimento(Integer.parseInt(valores[5]));
            jogador.setCidadeNascimento(valores[6].isEmpty() ? "nao informado" : valores[6]);
            jogador.setEstadoNascimento(valores[7].isEmpty() ? "nao informado" : valores[7]);
            listJogadores.add(jogador);
            entradaDados = scan.nextLine();
        }
        escrever();
    }

    public void escrever() {
        int idCount = Integer.parseInt(scan.nextLine());

        for (int i = 0; i <= idCount - 1; i++) {
            if (!scan.hasNext())
                break;
            int jogadorId = Integer.parseInt(scan.nextLine());
            for (Jogador jogadorTemp : listJogadores) {
                if (jogadorTemp.getId() == jogadorId) {
                    jogadorTemp.ImprimeDados();
                    break;
                }
            }
        }
    }

    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }

}

class Pilha {
    
}

public class Application {
    public static void main(String[] args) {
        Jogador newJogador = new Jogador();
        newJogador.ler();
    }
}