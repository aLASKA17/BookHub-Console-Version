package org.a1aska17.view;

import org.a1aska17.controller.BookController;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.*;


public class BookView {
    private final BookController bookController = new BookController();
    private final Scanner scanner = new Scanner(in);
    private void UIMenu() {
        out.println("1) Вывести список книг");
        out.println("2) Добавить новую книгу");
        out.println("3) Изменить книгу ");
        out.println("4) Удалить книгу");
        out.println("5) Выйти");
        out.print("Выберите пункт меню: ");
    }

    public void controllerUIMenu() {
        while (true) {
            try {
                UIMenu();
                int pointMenu = scanner.nextInt();
                switch (pointMenu) {
                    case 1: bookController.displayBooks(); break;
                    case 2: bookController.createAndAddBook(); break;
                    case 3: controllerUIMenuChange(); break;
                    case 4: bookController.deleteBook(); break;
                    case 5: exit(0); break;
                    default: out.println("Такого пункта в меню!");
                }
            } catch (InputMismatchException ex) {
                scanner.next();
                out.println("Введите корректное значение!");
            }
        }
    }

    private void UIMenuChange() {
        out.println("1) Изменить статус книги");
        out.println("2) Вернуться в основное меню");
        out.print("Выберите пункт меню: ");
    }

    private void controllerUIMenuChange() {
        while (true) {
           try {
               UIMenuChange();
               int pointMenu = scanner.nextInt();
               switch (pointMenu) {
                   case 1: bookController.changeStatus(); break;
                   case 2: controllerUIMenu(); break;
                   default: out.println("Такого пункта в меню!");
               }
           } catch (InputMismatchException ex) {
               scanner.next();
               out.println("Введите корректное значение!");
           }
        }
    }
}
