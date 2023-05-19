import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.Date;

class Jogador implements Comparable<Jogador> {
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

    public void imprimeDados() {
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

    public void ler(String nomeArquivo) {
        ArquivoTextoLeitura arquivo = new ArquivoTextoLeitura(nomeArquivo);
        arquivo.readLine();
        String entrada = arquivo.readLine();

        while (entrada != null && !entrada.equals("FIM")) {
            String[] valores = entrada.split(",", -1);
            int id = Integer.parseInt(valores[0]);

            if (id != 28) {
                listJogadores.add(preencherDados(valores));
            }

            entrada = arquivo.readLine();
        }

        arquivo.fecharArquivo();
        escrever();
    }

    public void escrever() {
        int[] ids = new int[10];
        int idCount = 0;

        String linha = scan.nextLine();
        while (!linha.isEmpty() && !linha.equals("FIM")) {
            if (idCount == ids.length) {
                ids = Arrays.copyOf(ids, ids.length * 2);
            }
            ids[idCount++] = Integer.parseInt(linha);
            linha = scan.nextLine();
        }

        for (int i = 0; i < idCount; i++) {
            ImprimeJogador(ids[i]);
        }
    }

    @Override
    public int compareTo(Jogador jog) {
        int comparacaoCidade = this.cidadeNascimento.compareTo(jog.cidadeNascimento);
        if (comparacaoCidade != 0) {
            return comparacaoCidade;
        }
        return this.nome.compareTo(jog.nome);
    }

    public Jogador clone() {
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }

    private Jogador preencherDados(String[] valores) {
        Jogador jogador = new Jogador();
        jogador.setId(Integer.parseInt(valores[0]));
        jogador.setNome(valores[1]);
        jogador.setAltura(Integer.parseInt(valores[2]));
        jogador.setPeso(Integer.parseInt(valores[3]));
        jogador.setUniversidade(valores[4].isEmpty() ? "nao informado" : valores[4]);
        jogador.setAnoNascimento(Integer.parseInt(valores[5]));
        jogador.setCidadeNascimento(valores[6].isEmpty() ? "nao informado" : valores[6]);
        jogador.setEstadoNascimento(valores[7].isEmpty() ? "nao informado" : valores[7]);
        return jogador;
    }

    private void ImprimeJogador(int jogadorId) {
        for (Jogador jogadorTemp : listJogadores) {
            if (jogadorTemp.getId() == jogadorId) {
                jogadorTemp.imprimeDados();
                break;
            }
        }
    }
}

class ArquivoTextoLeitura {
    private BufferedReader entrada;

    ArquivoTextoLeitura(String nomeArquivo) {
        try {
            entrada = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), "UTF-8"));
        } catch (UnsupportedEncodingException excecao) {
            System.out.println(excecao.getMessage());
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public String readLine() {
        String textoEntrada = null;
        try {
            textoEntrada = entrada.readLine();
        } catch (EOFException excecao) {
            textoEntrada = null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura\n" + excecao);
            textoEntrada = null;
        } finally {
            return textoEntrada;
        }
    }

    public void fecharArquivo() {
        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo..." +
                    excecao);
        }
    }
}

public class App {
    private static int numComparacoes = 0;
    private static int numMovimentacoes = 0;

    public static void bubble(ArrayList<Jogador> jogadores) {
        int n = jogadores.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                numComparacoes++;
                if (jogadores.get(j).compareTo(jogadores.get(j + 1)) > 0) {
                    Jogador temp = jogadores.get(j);
                    jogadores.set(j, jogadores.get(j + 1));
                    jogadores.set(j + 1, temp);
                    numMovimentacoes++;
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        boolean linhaRepetidaAdicionada = false;
        Scanner scan = new Scanner(System.in);
        String linhaRepetida = "[222 ## Max Zaslofsky ## 188 ## 77 ## 1925 ## St. John's University ## Brooklyn ## New York]";

        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/jogadores.txt"))) {
            String linha;

            br.readLine();

            while ((linha = br.readLine()) != null) {
                if (!linha.equals(linhaRepetida) || !linhaRepetidaAdicionada) {
                    if (linha.equals(linhaRepetida)) {
                        linhaRepetidaAdicionada = true;
                    }
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
                            valores[7].isEmpty() ? "nao informado" : valores[7]);
                    jogadores.add(jogador);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Jogador> jogadoresPesquisados = new ArrayList<>();
        String linha = scan.nextLine();
        while (!linha.isEmpty() && !linha.equals("FIM")) {
            int id = Integer.parseInt(linha);
            for (Jogador jogador : jogadores) {
                if (jogador.getId() == id) {
                    jogadoresPesquisados.add(jogador);
                    break;
                }
            }
            linha = scan.nextLine();
        }

        long startTime = new Date().getTime();
        bubble(jogadoresPesquisados);
        long endTime = new Date().getTime();

        for (Jogador jogador : jogadoresPesquisados) {
            jogador.imprimeDados();
        }

        try (FileWriter fw = new FileWriter("788315_bolha.txt")) {
            fw.write("788315\t" + jogadoresPesquisados.size() + "\t" + numComparacoes + "\t" + numMovimentacoes + "\t"
                    + (endTime - startTime)
                    + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}