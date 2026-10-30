package org.a1aska17.controller;

import org.a1aska17.exception.ExistsAsARangeException;
import org.a1aska17.exception.LessThanZeroException;
import org.a1aska17.model.Book;
import org.a1aska17.model.Genre;
import org.a1aska17.model.GenreOrStatus;
import org.a1aska17.model.Status;
import org.a1aska17.repository.JsonBookRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

public class BookController {
    private final JsonBookRepository jsonBookRepository = new JsonBookRepository();
    private final Scanner scanner = new Scanner(in);
    private List<Book> books = new ArrayList<>();

    private int checkValueLessThanZero(int value) {
        if (value < 0) {
            throw new LessThanZeroException("Значение не может быть меньше 0");
        }
        return value;
    }

    private int checkValueExistsAsRange(int value, int range) {
        if (value < 0 || value > range + 1) {
            throw new ExistsAsARangeException("Значение должно быть в диапозоне");
        }
        return value;
    }

    private void updateIdBook() {
        books = jsonBookRepository.readFromJsonFile();
        for (int i = 1; i < books.size()+1; i++) {
            books.get(i-1).setIdBook(i);
        }
        jsonBookRepository.saveToJsonFile(books);
    }

    private int inputId(int values, String stringForInput) {
        int chooseId = -1;
        while (chooseId == -1) {
            try {
                out.print("\nВведите " + stringForInput + ":");
                chooseId = checkValueExistsAsRange(scanner.nextInt(), values);
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                out.println("Введите число!");
                scanner.next();
            } catch (ExistsAsARangeException ex) {
                out.println("Введите число, которе находится в дапозоне!");
            }
        }
        return chooseId;
    }

    private int inputBookId() {
        List<Book> books = jsonBookRepository.readFromJsonFile();
        return inputId(books.size(), "ID книги");
    }

    private int inputPage() {
        int totalPageBook = 0;
        while (totalPageBook == 0) {
            try {
                out.print("\nВведите количество страниц в книге: ");
                totalPageBook = checkValueLessThanZero(scanner.nextInt());
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                out.println("Введите число!");
                scanner.next();
            } catch (LessThanZeroException ex) {
                out.println("Введите положительное число!");
            }
        }
        return totalPageBook;
    }

    private <T extends Enum<T> & GenreOrStatus> T chooseGenreOrStatus(T[] values, String genreOrStatsString) {
        for (T genreOrStats : values) {
            out.println(genreOrStats.getId() + ") " + genreOrStats.getTitle());
        }
        int idBook = inputId(values.length, genreOrStatsString);
        for (T genreOrStats : values) {
            if (genreOrStats.getId() == idBook) {
                return genreOrStats;
            }
        }
        return null;
    }

    public void changeStatus() {
        displayBooks();
        int idBook = inputBookId();
        Status statusBook = chooseGenreOrStatus(Status.values(), "ID статуса");
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
        int idBook = inputBookId();
        Genre genreBook = chooseGenreOrStatus(Genre.values(), "ID жанра");
        Book book = searchBookById(idBook);
        if (book != null) {
            book.setGenreBook(genreBook);
            book.setUpdatedAtBook(LocalDateTime.now());
            jsonBookRepository.saveToJsonFile(books);
        }
    }

    public void inputCurrentPage() {
        displayBooks();
        int idBook = inputBookId();
        int countPage = inputPage();
        Book book = searchBookById(idBook);
        if (book != null) {

        }
    }

    private Book searchBookById(int idBook) {
        Book book = books.stream()
                .filter(bookFilter -> bookFilter.getIdBook() == idBook)
                .findFirst()
                .orElse(null);
        if (book == null) {
            out.println("Книга с таким ID не найдена!");
            return null;
        }
        return book;
    }

    public void deleteBook() {
        displayBooks();
        books.remove(inputBookId() - 1);
        updateIdBook();
        jsonBookRepository.saveToJsonFile(books);
    }

    public void createAndAddBook() {
        out.print("Введите название книги: ");
        String titleBook = scanner.nextLine();
        out.print("\nВведите автора книги: ");
        String authorBook = scanner.nextLine();
        int totalPage = inputPage();
        Genre genreBook = chooseGenreOrStatus(Genre.values(), "ID жанра");
        books = jsonBookRepository.readFromJsonFile();
        books.add(new Book(titleBook, authorBook, totalPage, genreBook));
        jsonBookRepository.saveToJsonFile(books);
    }

    public void displayBooks() {
        books = jsonBookRepository.readFromJsonFile();
        if (books == null || books.isEmpty()) {
            out.println("Список книг пуст!");
        } else {
            books.forEach(book -> {
                book.updatePercentageBook(book);
                out.println(book);
            });
        }
    }
}
