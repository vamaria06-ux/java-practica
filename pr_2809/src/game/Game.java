package game;

import game.hero.*;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hero hero = new Hero(new Position(0,0), new Walk());
        System.out.println("Старт");
        System.out.println("Герой стартует в точке " + hero.getPosition());
        printHelp();

        while (true) {
            System.out.print("\nВведите команду: ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Игра окончена. Финальная позиция: " + hero.getPosition());
                break;
            }

            switch (command.toLowerCase()) {
                case "walk":
                    hero.setMoving(new Walk());
                    System.out.println("Способ передвижения: ходьба");
                    break;
                case "fly":
                    hero.setMoving(new Fly());
                    System.out.println("Способ передвижения: полёт");
                    break;
                case "split":
                    hero.setMoving(new Split());
                    System.out.println("Способ передвижения: отделение части себя");
                    break;
                case "help":
                    printHelp();
                    break;
                default:
                    int[] parsed = parseCoordinates(command);
                    if (parsed == null) {
                        System.out.println("Не понял команду. Введите 'help' для списка команд.");
                    } else {
                        hero.move(parsed[0], parsed[1]);
                        System.out.println("Герой переместился в " + hero.getPosition());
                    }
            }
        }

        scanner.close();
    }

    private static int[] parseCoordinates(String input) {
        String cleaned = input.replace(",", " ").trim();
        String[] parts = cleaned.split("\\s+");
        if (parts.length != 2) return null;

        try {
            int dx = clamp(Integer.parseInt(parts[0]));
            int dy = clamp(Integer.parseInt(parts[1]));
            return new int[]{dx, dy};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static int clamp(int value) {
        return Integer.compare(value, 0);
    }

    private static void printHelp() {
        System.out.println("""
                Команды:
                  walk        — переключиться на ходьбу (скорость 1)
                  fly         — переключиться на полёт (скорость 3)
                  split       — переключиться на отделение части себя (скорость 5)
                  <dx> <dy>   — направление движения, например: 1 0  или  -1 1
                  help        — показать это сообщение
                  exit        — закончить игру
                """);
    }
}
