package org.example;

import java.util.List;

public record StoreData (
        String store,
        List<Product> products
)
{ }
