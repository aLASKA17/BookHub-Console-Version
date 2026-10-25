package org.a1aska17.model;

public enum Status {
    PLANNED ("В планах"),
    READING ("Читаю"),
    RAED ("Прочитана"),
    PAUSE ("Отложена");

    private final String titleStatus;

    Status(String titleStatus) {
        this.titleStatus = titleStatus;
    }

    public String getTitleStatus() {
        return titleStatus;
    }
}
