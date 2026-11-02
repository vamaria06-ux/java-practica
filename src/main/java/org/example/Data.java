package org.example;

public class Data {
    @Ok
    public int firstField = 10;

    @Ok
    public int secondField = 20;

    @Ugly(value = 10)
    public int thirdField = 30;

    @Ugly
    public int fourthField = 40;

    public int fifthField = 50;
}
