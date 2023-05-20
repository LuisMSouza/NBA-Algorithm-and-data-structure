import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Jogador implements Comparable<Jogador> {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
            String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
        this.universidade = universidade;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void imprimirDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(id).append(" ## ")
                .append(nome).append(" ## ")
                .append(altura).append(" ## ")
                .append(peso).append(" ## ")
                .append(anoNascimento).append(" ## ")
                .append(campoNaoInformado(universidade)).append(" ## ")
                .append(campoNaoInformado(cidadeNascimento)).append(" ## ")
                .append(campoNaoInformado(estadoNascimento))
                .append("]\n");
        System.out.print(sb.toString());
    }

    private String campoNaoInformado(String campo) {
        return campo.isEmpty() ? "nao informado" : campo;
    }

    public int getId() {
        return id;
    }

    public int compareTo(Jogador outro) {
        if (this.altura != outro.altura) {
            return Integer.compare(this.altura, outro.altura);
        }
        return this.nome.compareTo(outro.nome);
    }
}

class App {
    public static void heapSort(Jogador[] jogadores, int[] comparacoes, int[] movimentacoes) {
        int n = jogadores.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(jogadores, n, i, comparacoes, movimentacoes);
        }

        for (int i = n - 1; i >= 0; i--) {
            movimentacoes[0]++;
            Jogador temp = jogadores[0];
            jogadores[0] = jogadores[i];
            jogadores[i] = temp;

            heapify(jogadores, i, 0, comparacoes, movimentacoes);
        }
    }

    public static void heapify(Jogador[] jogadores, int n, int i, int[] comparacoes, int[] movimentacoes) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n) {
            comparacoes[0]++;
            if (jogadores[left].compareTo(jogadores[largest]) > 0) {
                largest = left;
            }
        }

        if (right < n) {
            comparacoes[0]++;
            if (jogadores[right].compareTo(jogadores[largest]) > 0) {
                largest = right;
            }
        }

        if (largest != i) {
            movimentacoes[0]++;
            Jogador swap = jogadores[i];
            jogadores[i] = jogadores[largest];
            jogadores[largest] = swap;

            heapify(jogadores, n, largest, comparacoes, movimentacoes);
        }
    }

    public static void main(String[] args) {
        ArrayList<Jogador> jogadoresList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/jogadores.txt"))) {
            String linha = br.readLine(); // Descarta a primeira linha

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(",", -1);
                int id = Integer.parseInt(valores[0]);
                Jogador jogador = new Jogador(
                        id,
                        valores[1],
                        Integer.parseInt(valores[2]),
                        Integer.parseInt(valores[3]),
                        valores[4].isEmpty() ? "nao informado" : valores[4],
                        valores[5].isEmpty() ? 0 : Integer.parseInt(valores[5]),
                        valores[6].isEmpty() ? "nao informado" : valores[6],
                        valores[7].isEmpty() ? "nao informado" : valores[7]
                );
                jogadoresList.add(jogador);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Jogador> jogadoresPesquisados = new ArrayList<>();
        String linha = MyIO.readLine();
        while (!linha.isEmpty() && !linha.equals("FIM")) {
            int id = Integer.parseInt(linha);
            for (Jogador jogador : jogadoresList) {
                if (jogador.getId() == id) {
                    jogadoresPesquisados.add(jogador);
                    break;
                }
            }
            linha = MyIO.readLine();
        }

        Jogador[] jogadores = jogadoresPesquisados.toArray(new Jogador[0]);
        int n = jogadores.length;

        int[] comparacoes = {0};
        int[] movimentacoes = {0};
        long inicio = System.currentTimeMillis();
        heapSort(jogadores, comparacoes, movimentacoes);
        long fim = System.currentTimeMillis();

        for (Jogador jogador : jogadores) {
            jogador.imprimirDados();
        }

        try (FileWriter fw = new FileWriter("788315_heapsort.txt")) {
            fw.write("788315\t" + (fim - inicio) + "ms\t" + comparacoes[0] + "\t" + movimentacoes[0] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}