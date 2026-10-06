package files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String path = "src/main/resources/students";

        BufferedReader reader = null;
        int count = 0, sum = 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        try {
            reader = new BufferedReader(new FileReader(path));
            String str;
            while ((str = reader.readLine()) != null) {
                String[] parts = str.trim().split("\\s+");
                if(parts.length < 2) {
                    System.err.println("Пропуск " + str);
                    continue;
                }
                try {
                    int grade = Integer.parseInt(parts[1]);

                    sum += grade;
                    min = Math.min(min, grade);
                    max = Math.max(max, grade);
                    count++;
                }
                catch (NumberFormatException e) {
                    System.err.println("Пропущено не число " + str);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return;
        } finally {
            if (reader != null) {
                try {
                    reader.close(); //вроде так нельзя
                } catch (IOException e) {
                    System.err.println("Не удалось закрыть файл: " + e.getMessage());
                }
            }
        }
        if (count == 0) {
            System.err.println("Нет корректных оценок");
            return;
        }

        String result = String.format("Среднее: %.2f, минимум: %d , максимум: %d", (double) sum /count, min, max);

        writeResult(args, result);
    }

    private static void writeResult(String[] args, String result) {
        if (args.length == 0) {
            System.out.println(result);
            return;
        }

        String fileName = "result.txt";
        boolean append;

        switch (args[0]) {
            case "-a":
                append =true;
                break;
            case "-s":
                append = false;
                break;
            case "-n":
                if (args.length < 2) {
                    System.err.println("После -n укажите суффикы");
                    return;
                }
                fileName = "result" + args[1] +".txt";
                append = false;
                break;
            default:
                System.err.println("Неизвестный ключ: "+ args[0]);
                return;
        }

        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(result +System.lineSeparator());
            System.out.println("Записано в " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка в записи: " + e.getMessage());
        }
    }
}
