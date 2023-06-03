import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String coord_points_file = null;
        String coord_round_file = null;
        try {
            coord_round_file = args[0];
            coord_points_file = args[1];
        } catch (ArrayIndexOutOfBoundsException o) {
            System.out.println("Perhaps you are not impart arguments in programm");
            o.getMessage();
        }

        try (BufferedReader bfr_round = new BufferedReader(new FileReader(coord_round_file));
             BufferedReader bfr_points = new BufferedReader(new FileReader(coord_points_file))) {

            String[] round = bfr_round.readLine().split(" "); // this is are KOSTYYLE of read from file
            String tmp = bfr_round.readLine();

            float x_r = Float.valueOf(round[0]);
            float y_r = Float.valueOf(round[1]);
            float r = Float.valueOf(tmp);

            while (bfr_points.ready()) {
                String[] points = bfr_points.readLine().split(" ");
                float x_p = Float.valueOf(points[0]);
                float y_p = Float.valueOf(points[1]);

                float d = (float) Math.sqrt((x_p - x_r) * (x_p - x_r) + (y_p - y_r) * (y_p - y_r)); // formula from the Pythagorean theorem

                if (d == r) System.out.println(0);
                if (d < r) System.out.println(1);
                if (d > r) System.out.println(2);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Some troubles with file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
