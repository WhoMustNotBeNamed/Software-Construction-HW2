package org.hse.software.construction.HW2.controller;

import org.hse.software.construction.HW2.model.*;
import org.hse.software.construction.HW2.repository.Repository;
import org.hse.software.construction.HW2.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hse.software.construction.HW2.model.UserRole.ADMIN;
import static org.hse.software.construction.HW2.model.UserRole.VISITOR;

public class Controller {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Handler adminHandler = new AdminHandler();
    private Handler visitorHandler = new VisitorHandler();

    public Controller() {
        // Установка цепочки обработчиков
        adminHandler.setNext(visitorHandler);

        // Инициализация представления
        ConsoleView view = new ConsoleView();
        Repository repository = new Repository();
        Snapshot snapshot = repository.restoreSnapshot();

        // Восстановление состояния системы
        Menu menu = snapshot.getMenu();
        Account account = snapshot.getAccount();
        Order order = snapshot.getOrder();

        int choice = 0;

        do {
            try {
                // Показать форму регистрации
                view.showRegistrationForm();

                // Получить выбор пользователя
                choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        // Регистрация и вход пользователя в систему
                        User user = registration();
                        account.login(user.getUsername(), user.getPassword());
                        handleUser(user);
                        break;
                    case 2:
                        // Регистрация пользователя
                        User newUser = registration();
                        account.signUp(newUser.getUsername(), newUser.getPassword(),
                                newUser.getUsername().equals("admin") ? ADMIN : VISITOR);
                        handleUser(newUser);
                        break;
                    case 3:
                        break;
                    default:
                        view.showErrorMessage("Некорректный ввод. Введите число от 1 до 3");
                        break;
                }

            } catch (NumberFormatException e) {
                view.showErrorMessage("Некорректный ввод. Введите число от 1 до 3");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (choice != 3);
    }

    // Метод регистрации пользователя
    private User registration() throws IOException {
        System.out.println("Введите логин: ");
        String login = reader.readLine();
        System.out.println("Введите пароль: ");
        String password = reader.readLine();

        return User.builder()
                .username(login)
                .password(password)
                .build();
    }

    // Метод обработки пользователя
    private void handleUser(User user) {
        adminHandler.handle(user);
    }
}