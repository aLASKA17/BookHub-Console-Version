package org.a1aska17.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.a1aska17.constant.BookHubConstant.*;

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
    private LocalDateTime createdAtBook;
    private LocalDateTime updatedAtBook;
    private String impressionBook;
    private int percentageReadBook;

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

    public LocalDateTime getCreatedAtBook() {
        return createdAtBook;
    }

    public LocalDateTime getUpdatedAtBook() {
        return updatedAtBook;
    }

    public String getImpressionBook() {
        return impressionBook;
    }

    public void setStatusBook(Status statusBook) {
        this.statusBook = statusBook;
        this.updatedAtBook = LocalDateTime.now();
    }

    public void setTotalPageBook(int totalPageBook) {
        this.totalPageBook = totalPageBook;
        this.updatedAtBook = LocalDateTime.now();
    }

    public void setRatingBook(int ratingBook) {
        this.ratingBook = ratingBook;
        this.updatedAtBook = LocalDateTime.now();
    }

    public void setImpressionBook(String impressionBook) {
        this.impressionBook = impressionBook;
        this.updatedAtBook = LocalDateTime.now();
    }

    public void setGenreBook(Genre genreBook) {
        this.genreBook = genreBook;
        this.updatedAtBook = LocalDateTime.now();
    }

    public void setUpdatedAtBook(LocalDateTime updatedAtBook) {
        this.updatedAtBook = updatedAtBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdBook() {
        return idBook;
    }

    public Book() {}

    public void setStartedAtBook(LocalDate startedAtBook) {
        this.startedAtBook = startedAtBook;
    }

    public void setFinishedAtBook(LocalDate finishedAtBook) {
        this.finishedAtBook = finishedAtBook;
    }

    public void setCurrentPageBook(int currentPageBook) {
        this.currentPageBook = currentPageBook;
        this.updatedAtBook = LocalDateTime.now();
    }

    public void setAuthorBook(String authorBook) {
        this.authorBook = authorBook;
        this.updatedAtBook = LocalDateTime.now();
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
        this.updatedAtBook = LocalDateTime.now();
    }

    public Book(int idBook, String titleBook, String authorBook, int totalPageBook, Genre genreBook) {
        this.idBook = idBook;
        this.titleBook = titleBook;
        this.authorBook = authorBook;
        this.currentPageBook = 0;
        this.totalPageBook = totalPageBook;
        this.genreBook = genreBook;
        this.statusBook = Status.PAUSE;
        this.startedAtBook = null;
        this.finishedAtBook = null;
        this.ratingBook = 0;
        this.impressionBook = "Отзыв не написан";
        this.percentageReadBook = 0;
        this.createdAtBook = LocalDateTime.now();
        this.updatedAtBook = LocalDateTime.now();
    }

    public void setPercentageReadBook(int percentageReadBook) {
        this.percentageReadBook = percentageReadBook;
    }

    @Override
    public String toString() {
        return  "-------------------------------------------------------------------------------------------------" +
                "\n" + ID_BOOK + " = " + idBook + "\n" +
                TITLE_BOOK + " = " + titleBook + "\n" +
                AUTHOR_BOOK + " = " + authorBook + "\n" +
                CURRENT_PAGE + " = " + currentPageBook + "\n" +
                TOTAL_PAGE + " = " + totalPageBook + "\n" +
                PRECENT_READ_BOOK + " = " + percentageReadBook + " %" + "\n" +
                GENRE + " = " + genreBook.getTitle() + "\n" +
                STATUS + " = " + statusBook.getTitle() + "\n" +
                DATA_START + " = " + startedAtBook + "\n" +
                DATA_FINISH + " = " + finishedAtBook + "\n" +
                RATING_BOOK + " = " + ratingBook + "\n" +
                IMPRESSION_BOOK + " = " + impressionBook + "\n";
    }
}
