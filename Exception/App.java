package Exception;

public class App {

    // public static void convert(String[] args) throws NumberFormatException,
    // ArrayIndexOutOfBoundsException {
    // int i;

    // i = Integer.parseInt(args[0]);
    // System.out.println("Valor capturado: " + i);
    // }

    // public static void main(String[] args) {
    // try {
    // convert(args);
    // } catch (Exception e) {
    // System.out.println("KKKKKKKKK OTARIO");
    // }
    // }

    public static void itsPair(int i) throws Exception {
        if ((i % 2) == 0) {
            System.out.println(i + " é par");
        } else
            throw new Exception("O número informado não é par");
    }

    public static void main(String[] args) {
        try {
            itsPair(Integer.parseInt(args[0]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}