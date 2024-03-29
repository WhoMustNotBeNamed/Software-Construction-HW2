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
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Handler adminMenuHandler = new AdminMenuHandler();
    private final Handler visitorMenuHandler = new VisitorMenuHandler();

    public Controller() {
        // Установка цепочки обработчиков
        adminMenuHandler.setNext(visitorMenuHandler);

        // Инициализация представления
        ConsoleView view = new ConsoleView();
        Repository repository = new Repository();
        Snapshot snapshot = repository.restoreSnapshot();
        User user;

        // Восстановление состояния системы
        Menu menu = snapshot.getMenu();
        Account account = snapshot.getAccount();
        MoneyStorage moneyStorage = snapshot.getMoneyStorage();
        ReviewService reviewService = snapshot.getReviewService();

        // Инициализация кухни
        Kitchen kitchen = new Kitchen();
        kitchen.startKitchen(menu);

        int choice = 0;

        do {
            try {
                // Показать форму регистрации
                view.showRegistrationForm();

                // Получить выбор пользователя
                choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        // Вход пользователя в систему
                        user = registration();
                        if(!account.login(user.getUsername(), user.getPassword())) {
                            break;
                        }
                        handleUser(account.getUser(user.getUsername()), menu, moneyStorage, reviewService);

                        // Сохранение состояния
                        repository.saveSnapshot(new Snapshot(menu, account, moneyStorage, reviewService));
                        break;
                    case 2:
                        // Регистрация пользователя
                        user = registration();

                        if (!account.signUp(user.getUsername(), user.getPassword(), user.getUsername().equals("admin") ? ADMIN : VISITOR)) {
                            break;
                        }
                        handleUser(account.getUser(user.getUsername()), menu, moneyStorage, reviewService);

                        // Сохранение состояния
                        repository.saveSnapshot(new Snapshot(menu, account, moneyStorage, reviewService));
                        break;
                    case 3: // Выход
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

        // Остановка кухни
        kitchen.stopKitchen();
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
    private void handleUser(User user, Menu menu, MoneyStorage moneyStorage, ReviewService reviewService) {
        adminMenuHandler.handle(user, menu, moneyStorage, reviewService);
    }
}