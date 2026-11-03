package files;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path path = Path.of("src/main/resources/students");

        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        int count = 0, sum = 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (String str : lines) {
            String[] parts = str.trim().split("\\s+");
            if (parts.length < 2) {
                System.err.println("Пропуск " + str);
                continue;
            }
            int grade;
            try {
                grade = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.err.println("Пропущено не число " + str);
                continue;
            }

            sum += grade;
            min = Math.min(min, grade);
            max = Math.max(max, grade);
            count++;
        }

        if (count == 0) {
            System.err.println("Нет корректных оценок");
            return;
        }

        String result = String.format("Среднее: %.2f, минимум: %d , максимум: %d",
                (double) sum / count, min, max);

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
                append = true;
                break;
            case "-s":
                append = false;
                break;
            case "-n":
                if (args.length < 2) {
                    System.err.println("После -n укажите суффикс");
                    return;
                }
                fileName = "result" + args[1] + ".txt";
                append = false;
                break;
            default:
                System.err.println("Неизвестный ключ: " + args[0]);
                return;
        }

        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(result + System.lineSeparator());
            System.out.println("Записано в " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка в записи: " + e.getMessage());
        }
    }
}