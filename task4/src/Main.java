import java.io.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String file_path = null;
        try {
            file_path = args[0];
        } catch (ArrayIndexOutOfBoundsException x){
            System.out.println("Perhaps you are not impart arguments in program");
            x.getMessage();
        }

        try {
            BufferedReader bufferedFileReader = new BufferedReader(new FileReader(file_path));
            ArrayList <String> ar = new ArrayList<>();
            while (bufferedFileReader.ready()){
                String tmp = bufferedFileReader.readLine();
                ar.add(tmp);
            }
            bufferedFileReader.close();
            Object[] main_mas = ar.toArray();
            int[] mas = new int[main_mas.length];
            for(int z = 0; z < main_mas.length; z++){
                mas[z] = Integer.parseInt((String)main_mas[z]);
            }

            int count = mas.length;
            int sum = 0;
            for(int k = 0; k < count; k ++){
                sum += mas[k];
            }
            int mid = sum / count;
            int count_movie = 0;
            for(int i = 0; i < count; i++){
                int tmp = 0;
                tmp = mid - mas[i];
                tmp = (tmp > 0 ? tmp : tmp * -1);
                count_movie += tmp;
            }

            System.out.println(count_movie);



        } catch (FileNotFoundException e) {
            System.out.println("Some troubles with file");
            e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

