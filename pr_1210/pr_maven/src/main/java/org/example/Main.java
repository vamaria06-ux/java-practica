package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonFile = new File("pr_maven/src/main/resources/data.json");
            StoreData storeData = mapper.readValue(jsonFile, StoreData.class);

            System.out.println("Магазин" + storeData.store());
            System.out.println("Товары в наличии:");
            storeData.products().stream()
                    .filter(Product::inStock)
                    .forEach(p -> System.out.println("- " + p.name() + " (" + p.price() + " руб.)"));
            double totalPrice = storeData.products().stream()
                    .mapToDouble(Product::price)
                    .sum();
            System.out.println("Общая стоимость всех товаров: " + totalPrice + " руб.");

            Set<String> uniqueTags = storeData.products().stream()
                    .flatMap(p -> p.tags().stream())
                    .collect(Collectors.toSet());

            System.out.println("Уникальные теги:");
            uniqueTags.forEach(tag -> System.out.println("- " + tag));

        } catch (IOException e) {
            System.err.println("Ошибка при чтении или парсинге JSON: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
