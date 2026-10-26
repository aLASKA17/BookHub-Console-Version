package org.a1aska17.model;

import org.a1aska17.repository.JsonBookRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Book {
    private int idBook;
    private String titleBook;
    private String authorBook;
    private int currentPageBook;
    private int totalPageBook;
    private Genre genreBook;
    private Status statusBook;
    private LocalDate startedAtBook;
    private LocalDate finishedAtBook;
    private int ratingBook;

    public LocalDateTime getUpdatedAtBook() {
        return updatedAtBook;
    }

    public LocalDateTime getCreatedAtBook() {
        return createdAtBook;
    }

    private LocalDateTime createdAtBook;
    private LocalDateTime updatedAtBook;

    public void setStatusBook(Status statusBook) {
        this.statusBook = statusBook;
        this.updatedAtBook = LocalDateTime.now();
    }

    private String impressionBook;
    private int percentageReadBook;
    private final JsonBookRepository jsonBookRepository = new JsonBookRepository();

    private int generateId() {
        return jsonBookRepository.readFromJsonFile().size() + 1;
    }

    public int getIdBook() {
        return idBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public String getAuthorBook() {
        return authorBook;
    }

    public int getCurrentPageBook() {
        return currentPageBook;
    }

    public int getTotalPageBook() {
        return totalPageBook;
    }

    public Genre getGenreBook() {
        return genreBook;
    }

    public Status getStatusBook() {
        return statusBook;
    }

    public LocalDate getStartedAtBook() {
        return startedAtBook;
    }

    public LocalDate getFinishedAtBook() {
        return finishedAtBook;
    }

    public int getRatingBook() {
        return ratingBook;
    }

    public String getImpressionBook() {
        return impressionBook;
    }

    public Book() {}

    public Book(String titleBook, String authorBook, int totalPageBook, Genre genreBook) {
        this.idBook = generateId();
        this.titleBook = titleBook;
        this.authorBook = authorBook;
        this.currentPageBook = 0;
        this.totalPageBook = totalPageBook;
        this.genreBook = genreBook;
        this.statusBook = Status.PAUSE;
        this.startedAtBook = null;
        this.finishedAtBook = null;
        this.ratingBook = 0;
        this.impressionBook = "";
        this.percentageReadBook = getPercentageReadBook();
        this.createdAtBook = LocalDateTime.now();
        this.updatedAtBook = LocalDateTime.now();
    }

    public void setPercentageReadBook(int percentageReadBook) {
        this.percentageReadBook = percentageReadBook;
    }

    @Override
    public String toString() {
        return "Id книги = " + idBook +
                ", Название книги = '" + titleBook + '\'' +
                ", Автор книги = '" + authorBook + '\'' +
                ", Текущая страница = " + currentPageBook +
                ", Общее кол-во страниц = " + totalPageBook +
                ", Процент прочтения книги = " + percentageReadBook + " %" +
                ", Жанр книги = " + genreBook.getTitle() +
                ", Статус книги = " + statusBook.getTitleStatus() +
                ", Дата начала чтения книги = " + startedAtBook +
                ", Дата окончания чтения книги = " + finishedAtBook +
                ", Рейтинг книги = " + ratingBook +
                ", Отзыв о книги = " + impressionBook;
    }

    public int getPercentageReadBook() {
        if (currentPageBook > 0 && totalPageBook > 0) {
            return Math.round((float) currentPageBook / totalPageBook * 100);
        }
        return 0;
    }

    public void updatePercentageBook(Book book) {
        if (book.currentPageBook > 0 && book.totalPageBook > 0) {
            book.setPercentageReadBook(Math.round((float) book.currentPageBook / book.totalPageBook* 100));
        }
    }
}
