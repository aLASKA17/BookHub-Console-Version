package org.a1aska17.model;

public enum Status {
    PLANNED ("В планах"),
    READING ("Читаю"),
    RAED ("Прочитана"),
    PAUSE ("Отложена");

    private final String titleStatus;
    private final int id;
    private static int countId = 1;

    public int getId() {
        return id;
    }

    Status(String titleStatus) {
        this.titleStatus = titleStatus;
        this.id = generateId();
    }
    private int generateId() {
        return countId++;
    }

    public String getTitleStatus() {
        return titleStatus;
    }
}
