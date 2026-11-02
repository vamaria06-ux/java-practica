package org.example;

public class Main {
    public static void main(String[] args) {
        Data dataObj = new Data();
        SaveData.save(dataObj, "data.json");
    }
}
