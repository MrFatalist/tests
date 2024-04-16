import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class main {
    private static String last_id;

    public static void main(String[] args) throws IOException {
        //Получение путей к файлам из переданных аргументов
        Path pathV = Path.of(args[0]);
        Path pathT = Path.of(args[1]);
        Path pathR = Path.of(args[2]);
//Преобразование содержимого файлов в массивы построчно
        ArrayList <String> values = (ArrayList<String>) Files.readAllLines(pathV);
        ArrayList <String> tests = (ArrayList<String>) Files.readAllLines(pathT);

//Основной цикл
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(String.valueOf(pathR)))){ // подключаем поток на запись
            for(String str: values) { // пробегаем по документу построчно
                String writenStr = stringChecker(str, tests); // полученную строку проверяем подпрограммой
                writter.write(writenStr + "\n"); // запись результата в файл
            }
        }catch (Exception a){
                a.printStackTrace();
        }
    }

    //Методы
    public static String stringChecker(String str, ArrayList<String> tests){ // метод проверяет есть ли в переданной строке подстрока со значениями id и value
        String current_id = "";
        String current_value;
        StringTokenizer stv = new StringTokenizer(str); // подключение токенайзера для анализа содержимого
        while(stv.hasMoreTokens()){
            String tmp = stv.nextToken();
            if(tmp.equalsIgnoreCase("\"id\":")){ //если строка содержит id, то номер сохраняется в глобальной переменной для поиска значения value в документе values
                current_id = stv.nextToken(); // получаем id
                current_id.replaceAll(" ", ""); //убираем пробел
                last_id = current_id; // присваеваем значение глобальной переменной
                return str;
            }
            if(tmp.equalsIgnoreCase("\"value\":")){ // если строка содержит value, то она передается в подпрограмму которая дополнит эту строку результирующим значением из values
                current_value = idValueFinder(getLast_id(), tests); // присвоение результата работы подпрограммы
                str = str.replaceAll("\"\"", current_value);
                return str;
            }
        }

        return str;
    }

    public static String idValueFinder(String cur_id, ArrayList<String> tests){ // метод ищет результат value по номеру id и возвращает его
        String value = "";
        for(int k = 0; k < tests.size(); k++){
            int string_numb = k;
            String str = tests.get(k);
            StringTokenizer stt = new StringTokenizer(str);
            while(stt.hasMoreTokens()){
                String tmp = stt.nextToken();
                if (tmp.equalsIgnoreCase("\"id\":")){
                    String numb = stt.nextToken();
                    numb.replaceAll(" ", "");
                    //numb.replaceAll(",", "");
                    if(numb.equals(main.getLast_id())){
                        value = tests.get(k+1);
                        StringTokenizer x = new StringTokenizer(value);
                        String z = x.nextToken();
                        z = x.nextToken();
                        value = z;
                        return value;
                    }
                }
            }
        }
        return "";
    }

    public static String getLast_id() {
        return last_id;
    }
}


