package org.a1aska17.controller;

import org.a1aska17.model.Book;
import org.a1aska17.model.Genre;
import org.a1aska17.model.Status;
import org.a1aska17.repository.JsonBookRepository;

import static org.a1aska17.constant.BookHubConstant.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import static java.lang.System.*;

public class BookController {
    private final JsonBookRepository jsonBookRepository;
    private final InputController inputController;
    private final Scanner scanner;
    private List<Book> books = new ArrayList<>();

    public BookController(JsonBookRepository jsonBookRepository, InputController inputController, Scanner scanner) {
        this.jsonBookRepository = jsonBookRepository;
        this.inputController = inputController;
        this.scanner = scanner;
    }

    private void updatePercentageBook(Book book) {
        if (book.getCurrentPageBook() > 0 && book.getTotalPageBook() > 0) {
            book.setPercentageReadBook(Math.round((float) book.getCurrentPageBook() / book.getTotalPageBook() * 100));
        }
    }

    private int generateId() {
        return books.size()+1;
    }

    private void updateIdBook() {
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
        updateBook(book -> book.setStatusBook(inputController.chooseGenreOrStatus(Status.values(), ID_STATUS)));
    }

    public void changeGenre() {
        updateBook(book -> book.setGenreBook(inputController.chooseGenreOrStatus(Genre.values(), ID_GENRE)));
    }

    public void changeTitleBook() {
        updateBook(book -> book.setTitleBook(inputController.inputString(TITLE_BOOK)));
    }

    public void changeRating() {
        updateBook(book -> book.setRatingBook(inputController.inputNumber(MAX_RATING_FOR_BOOK, RATING_BOOK)));
    }

    public void changeAuthorBook() {
        updateBook(book -> book.setAuthorBook(inputController.inputString(AUTHOR_BOOK)));
    }

    private void updateBook(Consumer<Book> updaterForBook) {
        displayBooks();
        int idBook = inputController.inputBookId();
        Book book = searchBookById(idBook);
        if (book == null) return;
        updaterForBook.accept(book);
        book.setUpdatedAtBook(LocalDateTime.now());
        jsonBookRepository.saveToJsonFile(books);
    }

    public void changeImpressionBook() {
        updateBook(book -> book.setImpressionBook(inputController.inputString(IMPRESSION_BOOK)));
    }

    public void changePage(int value) {  // 0 - total, 1 - current TODO: Надо подумать как сделать более читаемо и наглядно
        updateBook(book -> {
            if ((value == 0)) {
                book.setTotalPageBook(inputController.inputPage());
            } else {
                book.setCurrentPageBook(inputController.inputPage());
            }
        });
    }

    public void deleteBook() {
        displayBooks();
        int idBook = inputController.inputBookId();
        Book book = searchBookById(idBook);
        if (book == null) return;
        books.remove(book);
        updateIdBook();
    }

    public void createAndAddBook() {
        out.print("Введите " + TITLE_BOOK + ": ");
        String titleBook = scanner.nextLine();
        out.print("\nВведите " + AUTHOR_BOOK + ": ");
        String authorBook = scanner.nextLine();
        int totalPage = inputController.inputPage();
        Genre genreBook = inputController.chooseGenreOrStatus(Genre.values(), ID_GENRE);
        books = jsonBookRepository.readFromJsonFile();
        books.add(new Book(generateId(), titleBook, authorBook, totalPage, genreBook));
        jsonBookRepository.saveToJsonFile(books);
    }

    public void displayBooks() {
        books = jsonBookRepository.readFromJsonFile();
        if (books == null || books.isEmpty()) {
            out.println(EMPTY_BOOKS);
        } else {
            books.forEach(book -> {
                updatePercentageBook(book);
                out.println(book);
            });
        }
    }
}
