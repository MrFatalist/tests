public class Main {
    public static void main(String[] args) {
        try {
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);

            int current = 1;
            do {
                System.out.print(current);
                current = (current + m - 2) % n + 1;

            } while (current != 1);

        } catch (ArrayIndexOutOfBoundsException x){
            System.out.println("Programm had no arguments");
        }

    }
}
