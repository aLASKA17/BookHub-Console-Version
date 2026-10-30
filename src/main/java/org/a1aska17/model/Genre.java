package org.a1aska17.model;

public enum Genre implements GenreOrStatus{
    FANTASY("Фэнтези"),
    SCIENCE_FICTION("Научная фантастика"),
    DYSTOPIAN("Антиутопия"),
    UTOPIAN("Утопия"),
    SPECULATIVE_FICTION("Спекулятивная фантастика"),
    FANTASY_AND_SCIENCE_FICTION("Фэнтези и научная фантастика"),
    MYSTERY("Детектив"),
    THRILLER("Триллер"),
    MYSTERY_AND_SUSPENSE("Детектив и саспенс"),
    HORROR("Ужасы"),
    ROMANCE("Романтическая проза"),
    HISTORICAL_ROMANCE("Исторический любовный роман"),
    HISTORICAL_FICTION("Историческая проза"),
    ADVENTURE("Приключения"),
    ACTION("Боевик"),
    DRAMA("Драма"),
    COMEDY("Комедия"),
    SATIRE("Сатира"),
    TRAGEDY("Трагедия"),
    REALIST_FICTION("Реалистическая проза"),
    MAGICAL_REALISM("Магический реализм"),
    CONTEMPORARY_FICTION("Современная проза"),
    CLASSIC_LITERATURE("Классическая литература"),
    PHILOSOPHICAL_FICTION("Философская проза"),
    PSYCHOLOGICAL_FICTION("Психологическая проза"),
    GOTHIC_FICTION("Готическая литература"),
    MILITARY_FICTION("Военная проза"),
    EPISTOLARY_FICTION("Эпистолярный роман"),
    FAMILY_SAGA("Семейная сага"),
    YOUNG_ADULT("Подростковая литература"),
    CHILDRENS_LITERATURE("Детская литература"),
    MIDDLE_GRADE("Книги для среднего школьного возраста"),
    FAIRY_TALE("Сказка"),
    POETRY("Поэзия"),
    PLAY("Пьеса"),
    ESSAY("Эссе"),
    BIOGRAPHY("Биография"),
    AUTOBIOGRAPHY("Автобиография"),
    MEMOIR("Мемуары"),
    HISTORY("История"),
    PHILOSOPHY("Философия"),
    PSYCHOLOGY("Психология"),
    SELF_HELP("Саморазвитие"),
    BUSINESS("Бизнес"),
    SCIENCE("Наука"),
    POPULAR_SCIENCE("Популярная наука"),
    TECHNOLOGY("Технологии"),
    ART("Искусство"),
    RELIGION("Религия"),
    TRAVEL("Путешествия"),
    COOKBOOK("Кулинария"),
    HEALTH_AND_WELLNESS("Здоровье и благополучие"),
    EDUCATION("Образование"),
    COMIC_BOOK("Комикс"),
    GRAPHIC_NOVEL("Графический роман"),
    MANGA("Манга"),
    OTHER("Другое");

    private final String title;
    private final int id;
    private static int countId = 0;

    Genre(String title) {
        this.title = title;
        this.id = generateId();
    }

    private int generateId() {
       return ++countId;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}