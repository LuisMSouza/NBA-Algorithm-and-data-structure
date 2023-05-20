import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Jogador implements Comparable<Jogador> {
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
        MyIO.print(sb.toString());
    }

    private String campoNaoInformado(String campo) {
        return campo.isEmpty() ? "nao informado" : campo;
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

    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }

    @Override
    public int compareTo(Jogador outro) {
        int comparacaoEstado = this.estadoNascimento.compareTo(outro.estadoNascimento);
        if (comparacaoEstado != 0) {
            return comparacaoEstado;
        }
        return this.nome.compareTo(outro.nome);
    }
}

class ArquivoTextoLeitura {
    private BufferedReader entrada;

    ArquivoTextoLeitura(String nomeArquivo) {
        try {
            entrada = new BufferedReader(new FileReader(nomeArquivo));
        } catch (IOException excecao) {
            System.out.println("Erro na abertura do arquivo de leitura: " + excecao);
        }
    }

    public String readLine() {
        String textoEntrada = null;
        try {
            textoEntrada = entrada.readLine();
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
        }
        return textoEntrada;
    }

    public void fecharArquivo() {
        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " +
                    excecao);
        }
    }
}

class App {

    public static int partition(Jogador[] jogadores, int inicio, int fim) {
        Jogador pivot = jogadores[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (jogadores[j].compareTo(pivot) <= 0) {
                i++;

                Jogador temp = jogadores[i];
                jogadores[i] = jogadores[j];
                jogadores[j] = temp;
            }
        }

        Jogador temp = jogadores[i + 1];
        jogadores[i + 1] = jogadores[fim];
        jogadores[fim] = temp;

        return i + 1;
    }

    public static void quickSort(Jogador[] jogadores, int inicio, int fim) {
        if (inicio < fim) {
            int pi = partition(jogadores, inicio, fim);

            quickSort(jogadores, inicio, pi - 1);
            quickSort(jogadores, pi + 1, fim);
        }
    }

    public static void main(String[] args) {
        Jogador[] jogadores = lerJogadores("/tmp/jogadores.txt");

        int[] idsEntrada = lerIdsEntrada();
        Jogador[] jogadoresPesquisados = pesquisarJogadores(idsEntrada, jogadores);

        int n = jogadoresPesquisados.length;

        int[] comparacoes = {0};
        int[] movimentacoes = {0};
        long inicio = System.currentTimeMillis();
        quickSort(jogadoresPesquisados, 0, n - 1);
        long fim = System.currentTimeMillis();

        for (Jogador jogador : jogadoresPesquisados) {
            jogador.imprimirDados();
        }

        escreverRelatorio("788315_bolha.txt", (fim - inicio), comparacoes[0], movimentacoes[0]);
    }

    public static Jogador[] lerJogadores(String nomeArquivo) {
        ArrayList<Jogador> jogadoresList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            br.readLine();

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

        Jogador[] jogadores = jogadoresList.toArray(new Jogador[0]);
        return jogadores;
    }

    public static int[] lerIdsEntrada() {
        ArrayList<Integer> idsList = new ArrayList<>();
        String linha = MyIO.readLine();

        while (!linha.isEmpty() && !linha.equals("FIM")) {
            int id = Integer.parseInt(linha);
            idsList.add(id);
            linha = MyIO.readLine();
        }

        int[] idsEntrada = new int[idsList.size()];
        for (int i = 0; i < idsList.size(); i++) {
            idsEntrada[i] = idsList.get(i);
        }

        return idsEntrada;
    }

    public static Jogador[] pesquisarJogadores(int[] idsEntrada, Jogador[] jogadores) {
        ArrayList<Jogador> jogadoresPesquisados = new ArrayList<>();

        for (Jogador jogador : jogadores) {
            if (containsId(idsEntrada, jogador.getId())) {
                jogadoresPesquisados.add(jogador);
            }
        }

        Jogador[] jogadoresArray = jogadoresPesquisados.toArray(new Jogador[0]);
        return jogadoresArray;
    }

    public static boolean containsId(int[] idsEntrada, int id) {
        for (int i = 0; i < idsEntrada.length; i++) {
            if (idsEntrada[i] == id) {
                return true;
            }
        }
        return false;
    }

    public static void escreverRelatorio(String nomeArquivo, long duracao, int comparacoes, int movimentacoes) {
        try (FileWriter fw = new FileWriter(nomeArquivo)) {
            fw.write("788315\t" + duracao + "ms\t" + comparacoes + "\t" + movimentacoes + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}