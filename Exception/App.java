package Exception;

public class App {
    public static void convert(String[] args) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        int i;

        i = Integer.parseInt(args[0]);
        System.out.println("Valor capturado: " + i);
    }

    public static void main(String[] args) {
        try {
            convert(args);
        } catch (Exception e) {
            System.out.println("KKKKKKKKK OTARIO");
        }
    }
}