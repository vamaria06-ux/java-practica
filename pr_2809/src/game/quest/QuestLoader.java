package game.quest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class QuestLoader {
    private static final Logger LOG = Log.get(QuestLoader.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private QuestLoader() {
    }

    public static List<Quest> load(String path) {
        try {
            List<Quest> quests = MAPPER.readValue(
                    new File(path), new TypeReference<List<Quest>>() {});
            LOG.info("Загружено квестов: " + quests.size());
            return quests;
        } catch (IOException e) {
            LOG.severe("Не удалось загрузить квесты из " + path + ": " + e.getMessage());
            return List.of();
        }
    }
}