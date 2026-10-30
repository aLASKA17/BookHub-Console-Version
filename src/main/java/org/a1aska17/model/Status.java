package org.a1aska17.model;

public enum Status implements GenreOrStatus{
    PLANNED ("В планах"),
    READING ("Читаю"),
    READ ("Прочитана"),
    PAUSE ("Отложена");

    private final String title;
    private final int id;
    private static int countId = 0;

    public int getId() {
        return id;
    }

    Status(String title) {
        this.title = title;
        this.id = generateId();
    }
    private int generateId() {
        return ++countId;
    }

    public String getTitle() {
        return title;
    }
}
