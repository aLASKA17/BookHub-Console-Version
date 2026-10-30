package org.a1aska17.controller;

import org.a1aska17.exception.ExistsAsARangeException;
import org.a1aska17.exception.LessThanZeroException;
import org.a1aska17.model.Book;
import org.a1aska17.model.GenreOrStatus;
import org.a1aska17.repository.JsonBookRepository;

import static org.a1aska17.constant.BookHubConstant.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class InputController {
    private final Scanner scanner;
    private final JsonBookRepository jsonBookRepository;

    public InputController(Scanner scanner, JsonBookRepository jsonBookRepository) {
        this.scanner = scanner;
        this.jsonBookRepository = jsonBookRepository;
    }

    String inputString(String stringForInput) {
        out.print("\nВведите " + stringForInput + ": ");
        return scanner.nextLine();
    }

    int inputNumber(int values, String stringForInput) {
        int chooseId = 0;
        while (chooseId == 0) {
            try {
                out.print("\nВведите " + stringForInput + ": ");
                chooseId = InputValidator.checkValueExistsAsRange(scanner.nextInt(), values);
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                out.println(ENTER_NUMBER);
                scanner.nextLine();
            } catch (ExistsAsARangeException ex) {
                out.println(EXISTS_AS_A_RANGE);
            }
        }
        return chooseId;
    }

    int inputBookId() {
        List<Book> books = jsonBookRepository.readFromJsonFile();
        return inputNumber(books.size(), ID_BOOK);
    }

    int inputPage() {
        int countPageBook = 0;
        while (countPageBook == 0) {
            try {
                out.print("\nВведите " + COUNT_PAGE + ": ");
                countPageBook = InputValidator.checkValueLessThanZero(scanner.nextInt());
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                out.println(ENTER_NUMBER);
                scanner.next();
            } catch (LessThanZeroException ex) {
                out.println(LESS_THAN_ZERO);
            }
        }
        return countPageBook;
    }

    <T extends Enum<T> & GenreOrStatus> T chooseGenreOrStatus(T[] values, String genreOrStatsString) {
        for (T genreOrStats : values) {
            out.println(genreOrStats.getId() + ") " + genreOrStats.getTitle());
        }
        int idBook = inputNumber(values.length, genreOrStatsString);
        for (T genreOrStats : values) {
            if (genreOrStats.getId() == idBook) {
                return genreOrStats;
            }
        }
        return null;
    }
}