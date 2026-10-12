package org.example;
import java.util.List;

public record Product (
    int id,
    String name,
    double price,
    boolean inStock,
    List<String> tags
)
{}
