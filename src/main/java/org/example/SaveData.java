package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveData {
    private static final Logger logger = LoggerFactory.getLogger(SaveData.class);

    public static void save(Object obj, String jsonPath) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Random random = new Random();

        StringBuilder jsonBuilder = new StringBuilder("{\n");
        boolean firstJsonField = true;

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                String fieldName = field.getName();
                Object value = field.get(obj);

                if (field.getType() == int.class || field.getType() == Integer.class) {
                    int intValue = (Integer) value;

                    if (field.isAnnotationPresent(Ok.class)) {
                        if (!firstJsonField) jsonBuilder.append(",\n");
                        jsonBuilder.append("  \"").append(fieldName).append("\": ").append(intValue);
                        firstJsonField = false;

                    } else if (field.isAnnotationPresent(Ugly.class)) {
                        Ugly uglyAnn = field.getAnnotation(Ugly.class);

                        int max = uglyAnn.value();
                        int randomValue;

                        if (max > 0) {
                            randomValue = random.nextInt(max) + 1;
                        } else {
                            logger.warn("В аннотации @Ugly указано некорректное значение max ({}). Значение скорректировано.", max);
                            int safeMax = Math.abs(max);
                            randomValue = random.nextInt(safeMax) + 1;
                        }

                        if (!firstJsonField) jsonBuilder.append(",\n");
                        jsonBuilder.append("  \"").append(fieldName).append("\": ").append(randomValue);
                        firstJsonField = false;

                    } else {
                        logger.info("Поле без аннотации: {} = {}", fieldName, intValue);
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error("Ошибка доступа к полю: {}", e.getMessage(), e);
            }
        }
        jsonBuilder.append("\n}");

        try (FileWriter writer = new FileWriter(jsonPath)) {
            writer.write(jsonBuilder.toString());
            logger.info("JSON успешно записан в файл: {}", jsonPath);
        } catch (IOException e) {
            logger.error("Ошибка записи в JSON файл {}: {}", jsonPath, e.getMessage());
        }
    }
}
