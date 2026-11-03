package game;

import game.hero.Fly;
import game.hero.Hero;
import game.hero.Position;
import game.hero.Split;
import game.hero.Walk;
import game.quest.Quest;
import game.quest.QuestLoader;

import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class Game {
    private static final Logger LOG = Log.get(Game.class);

    private static final String QUESTS_FILE = "Quests.json";
    private static final Map<String, Position> PLACES = Map.of("Рынок", new Position(6, 0));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hero hero = new Hero(new Position(0, 0), new Walk());
        LOG.info("Герой стартует в точке " + hero.getPosition());

        for (Quest quest : QuestLoader.load(QUESTS_FILE)) {
            hero.addQuest(quest);
        }
        printHelp();

        while (true) {
            LOG.info("Введите команду:");
            if (!scanner.hasNextLine()) {
                LOG.warning("Ввод закончился, игра завершена");
                break;
            }
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                LOG.info("Игра окончена. Позиция: " + hero.getPosition()
                        + ", золота: " + hero.getGold());
                break;
            }

            String[] words = command.split("\\s+", 2);
            String name = words[0].toLowerCase();
            String arg = words.length > 1 ? words[1].trim() : "";

            switch (name) {
                case "walk":
                    hero.setMoving(new Walk());
                    LOG.info("Способ передвижения: ходьба");
                    break;
                case "fly":
                    hero.setMoving(new Fly());
                    LOG.info("Способ передвижения: полёт");
                    break;
                case "split":
                    hero.setMoving(new Split());
                    LOG.info("Способ передвижения: отделение части себя");
                    break;
                case "help":
                    printHelp();
                    break;
                case "quests":
                    printQuests(hero);
                    break;
                case "pet":
                    if (arg.isEmpty()) {
                        LOG.warning("Укажите, кого гладить, например: pet Кот");
                    } else if (hero.pet(arg)) {
                        LOG.info("Вы погладили: " + arg + " (засчитано в квесте)");
                        reportFinished(hero);
                    } else {
                        LOG.info("Вы погладили: " + arg + " (ни один квест этого не требует)");
                    }
                    break;
                case "collect":
                    if (arg.isEmpty()) {
                        LOG.warning("Укажите, что собирать, например: collect Целебная трава");
                    } else if (hero.collect(arg)) {
                        LOG.info("Вы собрали: " + arg + " (засчитано в квесте)");
                        reportFinished(hero);
                    } else {
                        LOG.info("Вы собрали: " + arg + " (ни один квест этого не требует)");
                    }
                    break;
                default:
                    int[] parsed = parseCoordinates(command);
                    if (parsed == null) {
                        LOG.warning("Не понял команду. Введите 'help' для списка команд.");
                    } else {
                        hero.move(parsed[0], parsed[1]);
                        LOG.info("Герой переместился в " + hero.getPosition());
                        checkPlaces(hero);
                    }
            }
        }

        scanner.close();
    }

    private static void checkPlaces(Hero hero) {
        for (Map.Entry<String, Position> place : PLACES.entrySet()) {
            if (place.getValue().equals(hero.getPosition())) {
                LOG.info("Герой пришёл в место: " + place.getKey());
                hero.arrive(place.getKey());
                reportFinished(hero);
            }
        }
    }

    private static void reportFinished(Hero hero) {
        for (Quest quest : hero.claimRewards()) {
            LOG.info("Квест выполнен: «" + quest.getName() + "»! Награда: "
                    + quest.getReward() + " золота. Всего золота: " + hero.getGold());
        }
    }

    private static void printQuests(Hero hero) {
        LOG.info("Золото: " + hero.getGold());
        LOG.info("Активные квесты:");
        if (hero.getActiveQuests().isEmpty()) {
            LOG.info("  нет");
        }
        for (Quest quest : hero.getActiveQuests()) {
            LOG.info("  - " + quest.describe());
        }
        LOG.info("Выполнено квестов: " + hero.getDoneQuests().size());
    }

    private static int[] parseCoordinates(String input) {
        String cleaned = input.replace(",", " ").trim();
        String[] parts = cleaned.split("\\s+");
        if (parts.length != 2) {
            return null;
        }
        try {
            int dx = Integer.compare(Integer.parseInt(parts[0]), 0);
            int dy = Integer.compare(Integer.parseInt(parts[1]), 0);
            return new int[]{dx, dy};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static void printHelp() {
        LOG.info("""
                Команды:
                  walk              — ходьба (скорость 1)
                  fly               — полёт (скорость 3)
                  split             — отделение части себя (скорость 5)
                  <dx> <dy>         — направление, например: 1 0  или  -1 1
                  quests            — показать квесты и золото
                  pet <цель>        — погладить, например: pet Кот
                  collect <предмет> — собрать, например: collect Целебная трава
                  help              — это сообщение
                  exit              — выход

                Рынок находится в точке (6,0).""");
    }
}
