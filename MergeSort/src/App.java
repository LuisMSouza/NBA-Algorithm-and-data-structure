import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Arrays;

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

    public void ler(String nomeArquivo) {
        ArquivoTextoLeitura arquivo = new ArquivoTextoLeitura(nomeArquivo);
        arquivo.readLine();
        String entradaDados = arquivo.readLine();

        while (entradaDados != null && !entradaDados.equals("FIM")) {
            String[] valores = entradaDados.split(",", -1);
            int id = Integer.parseInt(valores[0]);

            if (id != 28) {
                preencherJogador(valores);
            }

            entradaDados = arquivo.readLine();
        }

        arquivo.fecharArquivo();
        escrever();
    }

    public void preencherJogador(String[] valores) {
        setId(Integer.parseInt(valores[0]));
        setNome(valores[1]);
        setAltura(Integer.parseInt(valores[2]));
        setPeso(Integer.parseInt(valores[3]));
        setUniversidade(campoNaoInformado(valores[4]));
        setAnoNascimento(valores[5].equals("nao informado") ? 0 : Integer.parseInt(valores[5]));
        setCidadeNascimento(campoNaoInformado(valores[6]));
        setEstadoNascimento(campoNaoInformado(valores[7]));
    }

    public void escrever() {
        int[] ids = new int[10];
        int idCount = 0;

        String linha = MyIO.readLine();
        while (!linha.isEmpty() && !linha.equals("FIM")) {
            if (idCount == ids.length) {
                ids = Arrays.copyOf(ids, ids.length * 2);
            }
            ids[idCount++] = Integer.parseInt(linha);
            linha = MyIO.readLine();
        }

        for (int i = 0; i < idCount; i++) {
            imprimirJogadorPorId(ids[i]);
        }
    }

    private void imprimirJogadorPorId(int jogadorId) {
        if (getId() == jogadorId) {
            imprimirDados();
        }
    }

    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }

    @Override
    public int compareTo(Jogador outro) {
        int comparacaoUniversidade = this.universidade.compareTo(outro.universidade);
        if (comparacaoUniversidade != 0) {
            return comparacaoUniversidade;
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
            System.out.println("Erro na abertura do arquivo: " + excecao.getMessage());
        }
    }

    public String readLine() {
        String textoEntrada = null;
        try {
            textoEntrada = entrada.readLine();
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao.getMessage());
        }
        return textoEntrada;
    }

    public void fecharArquivo() {
        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo: " + excecao.getMessage());
        }
    }
}

class App {

    public static void mergeSort(Jogador[] jogadores, int inicio, int fim, int[] comparacoes, int[] movimentacoes) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(jogadores, inicio, meio, comparacoes, movimentacoes);
            mergeSort(jogadores, meio + 1, fim, comparacoes, movimentacoes);
            merge(jogadores, inicio, meio, fim, comparacoes, movimentacoes);
        }
    }

    public static void merge(Jogador[] jogadores, int inicio, int meio, int fim, int[] comparacoes, int[] movimentacoes) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        Jogador[] L = new Jogador[n1];
        Jogador[] R = new Jogador[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = jogadores[inicio + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = jogadores[meio + 1 + j];
        }

        int i = 0, j = 0;
        int k = inicio;

        while (i < n1 && j < n2) {
            comparacoes[0]++;
            if (L[i].compareTo(R[j]) <= 0) {
                jogadores[k] = L[i];
                i++;
            } else {
                jogadores[k] = R[j];
                j++;
                movimentacoes[0]++;
            }
            k++;
        }

        while (i < n1) {
            jogadores[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            jogadores[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        Jogador[] jogadores = new Jogador[3923];
        int jogadorCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/jogadores.txt"))) {
            String linha;

            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(",", -1);
                int id = Integer.parseInt(valores[0]);

                if (id != 28) {
                    jogadores[jogadorCount] = new Jogador();
                    jogadores[jogadorCount].preencherJogador(valores);
                    jogadorCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] idsEntrada = new int[1000];
        int idCount = 0;

        String linha = MyIO.readLine();
        while (!linha.isEmpty() && !linha.equals("FIM")) {
            idsEntrada[idCount] = Integer.parseInt(linha);
            idCount++;
            linha = MyIO.readLine();
        }

        Jogador[] jogadoresPesquisados = new Jogador[idCount];
        int jogadorPesquisadoCount = 0;

        for (int i = 0; i < idCount; i++) {
            int jogadorId = idsEntrada[i];
            for (int j = 0; j < jogadorCount; j++) {
                if (jogadores[j].getId() == jogadorId) {
                    jogadoresPesquisados[jogadorPesquisadoCount] = jogadores[j].clone();
                    jogadorPesquisadoCount++;
                    break;
                }
            }
        }

        int[] comparacoes = {0};
        int[] movimentacoes = {0};
        long inicio = System.currentTimeMillis();
        mergeSort(jogadoresPesquisados, 0, jogadorPesquisadoCount - 1, comparacoes, movimentacoes);
        long fim = System.currentTimeMillis();

        for (int i = 0; i < jogadorPesquisadoCount; i++) {
            jogadoresPesquisados[i].imprimirDados();
        }

        try (FileWriter fw = new FileWriter("788315_bolha.txt")) {
            fw.write("788315\t" + (fim - inicio) + "ms\t" + comparacoes[0] + "\t" + movimentacoes[0] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}