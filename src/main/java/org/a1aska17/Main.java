package org.a1aska17;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.a1aska17.controller.BookController;
import org.a1aska17.controller.InputController;
import org.a1aska17.repository.JsonBookRepository;
import org.a1aska17.view.BookView;

import java.io.File;
import java.util.Scanner;

import static java.lang.System.in;
import static org.a1aska17.constant.BookHubConstant.FILE_BOOKS;

public class Main {
    static void main() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_BOOKS);
        JsonBookRepository jsonBookRepository = new JsonBookRepository(mapper, file);
        Scanner scanner = new Scanner(in);
        InputController inputController = new InputController(scanner, jsonBookRepository);
        BookController bookController = new BookController(jsonBookRepository, inputController, scanner);
        BookView bookView = new BookView(bookController, scanner);
        bookView.controllerUIMenu();
    }
}
