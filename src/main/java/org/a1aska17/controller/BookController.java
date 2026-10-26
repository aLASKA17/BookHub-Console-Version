package org.a1aska17.controller;

import org.a1aska17.exception.ExistsAsARangeException;
import org.a1aska17.exception.LessThanZeroException;
import org.a1aska17.model.Book;
import org.a1aska17.model.Genre;
import org.a1aska17.model.Status;
import org.a1aska17.repository.JsonBookRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

public class BookController {
    private final JsonBookRepository jsonBookRepository = new JsonBookRepository();
    private final Scanner scanner = new Scanner(in);

    private int checkValueLessThanZero(int value) {
        if (value < 0) {
            throw new LessThanZeroException("Значение не может быть меньше 0");
        }
        return value;
    }

    private Book searchBookById(int idBook, List<Book> books) {
        for (Book book : books) {
            if (book.getIdBook() == idBook) {
                return book;
            }
        }
        return null;
    }

    private int checkValueExistsAsRange(int value, int range) {
        if (value < 0 || value > range + 1) {
            throw new ExistsAsARangeException("Значение должно быть в диапозоне");
        }
        return value;
    }

    private int inputBookId() {
        List<Book> books = jsonBookRepository.readFromJsonFile();
        int chooseBookId = -1;
        while (chooseBookId == -1) {
            try {
                out.print("\nВведите ID книги: ");
                chooseBookId = checkValueExistsAsRange(scanner.nextInt(), books.size());
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                out.println("Введите число!");
                scanner.next();
            } catch (ExistsAsARangeException ex) {
                out.println("Введите число, которе находится в дапозоне!");
            }
        }
        return chooseBookId;
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

    private Status chooseStatus() {
        for (Status status : Status.values()) {
            out.println(status.getId() + ") " + status.getTitleStatus());
        }
        int idStatusBook = -1;
        while (idStatusBook == -1) {
           try {
               out.print("\nВведите id status: ");
               idStatusBook = checkValueExistsAsRange(scanner.nextInt(), Status.values().length);
               scanner.nextLine();
           } catch (InputMismatchException ex) {
               out.println("Введите число!");
               scanner.nextLine();
           } catch (ExistsAsARangeException ex) {
               out.println("Введите число, которе находится в дапозоне!");
           }
        }
        for (Status status : Status.values()) {
            if (status.getId() == idStatusBook) {
                return status;
            }
        }
        return null;
    }

    private Genre chooseGenre() {
        for (Genre genre : Genre.values()) {
            out.println(genre.getId() + ") " + genre.getTitle());
        }
        int idGenreBook = -1;
        while (idGenreBook == -1) {
            try {
                out.print("\nВведите id жанра книги: ");
                idGenreBook = checkValueExistsAsRange(scanner.nextInt(), Genre.values().length);
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                out.println("Введите число!");
                scanner.next();
            } catch (ExistsAsARangeException ex) {
                out.println("Введите число, которе находится в дапозоне!");
            }
        }
        for (Genre genre : Genre.values()) {
            if (genre.getId() == idGenreBook) {
                return genre;
            }
        }
        return null;
    }

    public void changeStatus() {
        displayBooks();
        int idBook = inputBookId();
        Status statusBook = chooseStatus();
        Book book = searchBookById(idBook, jsonBookRepository.readFromJsonFile());
        if (book != null) {
            book.setStatusBook(statusBook);
        }
        jsonBookRepository.saveToJsonFile(null);
    }

    public void createAndAddBook() {
        out.print("Введите название книги: ");
        String titleBook = scanner.nextLine();
        out.print("\nВведите автора книги: ");
        String authorBook = scanner.nextLine();
        int totalPage = inputPage();
        Genre genreBook = chooseGenre();
        jsonBookRepository.saveToJsonFile(new Book(titleBook, authorBook, totalPage, genreBook));
    }

    public void displayBooks() {
        List<Book> books = jsonBookRepository.readFromJsonFile();
        books.forEach(book -> {
            book.updatePercentageBook(book);
            out.println(book);
        });
    }

}
