/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

 package leiaarray;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 
 
 /**
  *
  * @author William
  */
 public class LeiaArray {
     public static void main(String[] args) {
         char[][] valores = lerEntradaDoUsuario();
         // Processar os valores lidos...
     }
 
     public static char[][] lerEntradaDoUsuario() {
         Scanner scanner = new Scanner(System.in);
         List<char[]> linhas = new ArrayList<>();
         System.out.println("Digite os dados separados por vírgulas (Digite FIM para encerrar):");
         String entrada;
         do {
             entrada = scanner.nextLine();
             if (!entrada.equalsIgnoreCase("FIM")) {
                 char[] valoresLinha = entrada.toCharArray();
                 linhas.add(valoresLinha);
             }
         } while (!entrada.equalsIgnoreCase("FIM"));
 
         char[][] valores = new char[linhas.size()][];
         for (int i = 0; i < linhas.size(); i++) {
             valores[i] = linhas.get(i);
         }
         return valores;
     }
 }
 