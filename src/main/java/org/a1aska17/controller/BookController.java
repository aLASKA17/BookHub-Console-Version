package org.a1aska17.controller;

import org.a1aska17.exception.ExistsAsAGenreException;
import org.a1aska17.exception.LessThanZeroException;
import org.a1aska17.model.Book;
import org.a1aska17.model.Genre;
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

    private int checkValueExistsAsAGenre(int value) {
        if (value < 0 || value > Genre.values().length + 1) {
            throw new ExistsAsAGenreException("Значение должно быть в диапозоне значений жанра");
        }
        return value;
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

    private Genre chooseGenre() {
        for (Genre genre : Genre.values()) {
            out.println(genre.getId() + ") " + genre.getTitle());
        }
        int idGenreBook = 0;
        while (idGenreBook == 0) {
            try {
                out.print("\nВведите id жанра книги: ");
                idGenreBook = checkValueExistsAsAGenre(scanner.nextInt());
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                out.println("Введите число!");
                scanner.next();
            } catch (ExistsAsAGenreException ex) {
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
