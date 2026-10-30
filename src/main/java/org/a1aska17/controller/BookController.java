package org.a1aska17.controller;

import org.a1aska17.model.Book;
import org.a1aska17.model.Genre;
import org.a1aska17.model.Status;
import org.a1aska17.repository.JsonBookRepository;

import static org.a1aska17.constant.BookHubConstant.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class BookController {
    private final JsonBookRepository jsonBookRepository = new JsonBookRepository();
    private final Scanner scanner = new Scanner(in);
    private List<Book> books = new ArrayList<>();
    private final InputController inputController = new InputController();

    private void updateIdBook() {
        books = jsonBookRepository.readFromJsonFile();
        for (int i = 1; i < books.size() + 1; i++) {
            books.get(i - 1).setIdBook(i);
        }
        jsonBookRepository.saveToJsonFile(books);
    }

    private Book searchBookById(int idBook) {
        Book book = books.stream()
                .filter(bookFilter -> bookFilter.getIdBook() == idBook)
                .findFirst()
                .orElse(null);
        if (book == null) {
            out.println(BOOK_NOT_FOUND);
            return null;
        }
        return book;
    }

    public void changeStatus() {
        displayBooks();
        int idBook = inputController.inputBookId();
        Status statusBook = inputController.chooseGenreOrStatus(Status.values(), ID_STATUS);
        Book book = searchBookById(idBook);
        if (book != null) {
            if (statusBook == Status.READING) {
                book.setStartedAtBook(LocalDate.now());
            }
            if (statusBook == Status.RAED) {
                book.setFinishedAtBook(LocalDate.now());
            }
            book.setStatusBook(statusBook);
            book.setUpdatedAtBook(LocalDateTime.now());
            jsonBookRepository.saveToJsonFile(books);
        }
    }

    public void changeGenre() {
        displayBooks();
        int idBook = inputController.inputBookId();
        Genre genreBook = inputController.chooseGenreOrStatus(Genre.values(), ID_GENRE);
        Book book = searchBookById(idBook);
        if (book != null) {
            book.setGenreBook(genreBook);
            book.setUpdatedAtBook(LocalDateTime.now());
            jsonBookRepository.saveToJsonFile(books);
        }
    }

    public void changeTitleBook() {
        displayBooks();
        int idBook = inputController.inputBookId();
        String titleBook = inputController.inputString(TITLE_BOOK);
        Book book = searchBookById(idBook);
        if (book != null) {
            book.setTitleBook(titleBook);
            book.setUpdatedAtBook(LocalDateTime.now());
            jsonBookRepository.saveToJsonFile(books);
        }
    }

    public void changeRating() {
        displayBooks();
        int idBook = inputController.inputBookId();
        int ratingBook = inputController.inputNumber(MAX_RATING_FOR_BOOK, RATING_BOOK);
        Book book = searchBookById(idBook);
        if (book != null) {
            book.setRatingBook(ratingBook);
            book.setUpdatedAtBook(LocalDateTime.now());
            jsonBookRepository.saveToJsonFile(books);
        }
    }

    public void changeAuthorBook() {
        displayBooks();
        int idBook = inputController.inputBookId();
        String authorBook = inputController.inputString(AUTHOR_BOOK);
        Book book = searchBookById(idBook);
        if (book != null) {
            book.setAuthorBook(authorBook);
            book.setUpdatedAtBook(LocalDateTime.now());
            jsonBookRepository.saveToJsonFile(books);
        }
    }

    public void changeImpressionBook() {
        displayBooks();
        int idBook = inputController.inputBookId();
        String impressionBook = inputController.inputString(IMPRESSION_BOOK);
        Book book = searchBookById(idBook);
        if (book != null) {
            book.setImpressionBook(impressionBook);
            book.setUpdatedAtBook(LocalDateTime.now());
            jsonBookRepository.saveToJsonFile(books);
        }
    }

    public void changePage(int value) {  // 0 - total, 1 - current TODO: Надо подумать как сделать более читаемо и наглядно
        displayBooks();
        int idBook = inputController.inputBookId();
        int countPage = inputController.inputPage();
        Book book = searchBookById(idBook);
        if (book != null) {
            if (value == 0) {
                book.setTotalPageBook(countPage);
            } else {
                book.setCurrentPageBook(countPage);
            }
            book.updatePercentageBook(book);
            book.setUpdatedAtBook(LocalDateTime.now());
            jsonBookRepository.saveToJsonFile(books);
        }
    }

    public void deleteBook() {
        displayBooks();
        books.remove(inputController.inputBookId() - 1);
        updateIdBook();
        jsonBookRepository.saveToJsonFile(books);
    }

    public void createAndAddBook() {
        out.print("Введите " + TITLE_BOOK + ": ");
        String titleBook = scanner.nextLine();
        out.print("\nВведите " + AUTHOR_BOOK + ": ");
        String authorBook = scanner.nextLine();
        int totalPage = inputController.inputPage();
        Genre genreBook = inputController.chooseGenreOrStatus(Genre.values(), ID_GENRE);
        books = jsonBookRepository.readFromJsonFile();
        books.add(new Book(titleBook, authorBook, totalPage, genreBook));
        jsonBookRepository.saveToJsonFile(books);
    }

    public void displayBooks() {
        books = jsonBookRepository.readFromJsonFile();
        if (books == null || books.isEmpty()) {
            out.println(EMPTY_BOOKS);
        } else {
            books.forEach(book -> {
                book.updatePercentageBook(book);
                out.println(book);
            });
        }
    }
}
