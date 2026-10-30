package org.a1aska17.view;

import org.a1aska17.controller.BookController;
import static org.a1aska17.constant.BookHubConstant.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.*;

public class BookView {
    private final BookController bookController;
    private final Scanner scanner;

    public BookView(BookController bookController, Scanner scanner) {
        this.bookController = bookController;
        this.scanner = scanner;
    }

    private void UIMenu() {
        out.println("_________________________________________________________________");
        out.println("1) Вывести список книг");
        out.println("2) Добавить новую книгу");
        out.println("3) Изменить книгу ");
        out.println("4) Удалить книгу");
        out.println("5) Выйти");
        out.print("Выберите пункт меню: ");
    }

    public void controllerUIMenu() {
        while (true) {
            UIMenu();
            try {
                int pointMenu = scanner.nextInt();
                scanner.nextLine();
                switch (pointMenu) {
                    case 1: bookController.displayBooks(); break;
                    case 2: bookController.createAndAddBook(); break;
                    case 3: controllerUIMenuChange(); break;
                    case 4: bookController.deleteBook(); break;
                    case 5: exit(0); break;
                    default: out.println(NOT_FOUND_POINT);
                }
            } catch (InputMismatchException ex) {
                scanner.nextLine();
                out.println(CORRECT_VALUE);
            }
        }
    }

    private void UIMenuChange() {
        out.println("___________________________________________________________________________");
        out.println("1) Изменить название книги");
        out.println("2) Изменить автора книги");
        out.println("3) Изменить статус книги");
        out.println("4) Изменить жанр");
        out.println("5) Изменить текущую страницу");
        out.println("6) Изменить количество страниц в книге");
        out.println("7) Изменить рейтинг книги");
        out.println("8) Изменить отзыв о книги");
        out.println("9) Вернуться в основное меню");
        out.print("Выберите пункт меню: ");
    }

    private void controllerUIMenuChange() {
        while (true) {
           try {
               UIMenuChange();
               int pointMenu = scanner.nextInt();
               scanner.nextLine();
               switch (pointMenu) {
                   case 1: bookController.changeTitleBook(); break;
                   case 2: bookController.changeAuthorBook(); break;
                   case 3: bookController.changeStatus(); break;
                   case 4: bookController.changeGenre(); break;
                   case 5: bookController.changePage(1); break;
                   case 6: bookController.changePage(0); break;
                   case 7: bookController.changeRating(); break;
                   case 8: bookController.changeImpressionBook(); break;
                   case 9: return;
                   default: out.println(NOT_FOUND_POINT);
               }
           } catch (InputMismatchException ex) {
               scanner.nextLine();
               out.println(CORRECT_VALUE);
           }
        }
    }
}
