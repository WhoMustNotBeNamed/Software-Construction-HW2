package org.hse.software.construction.HW2.view;

import org.hse.software.construction.HW2.model.Dish;
import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.Order;


public class ConsoleView implements View {

    @Override
    public void showRegistrationForm() {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню регистрации:" + ConsoleColors.ANSI_RESET);
        System.out.println("1. Войти:");
        System.out.println("2. Зарегистрироваться:");
    }

    @Override
    public void showRegistrationSuccess() {
        System.out.println(ConsoleColors.ANSI_GREEN + "Регистрация прошла успешно" + ConsoleColors.ANSI_RESET);
    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println(ConsoleColors.ANSI_RED + "Ошибка: " + message + ConsoleColors.ANSI_RESET);
    }

    @Override
    public void showMenu() {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню:" + ConsoleColors.ANSI_RESET);
        System.out.println("1. Просмотр меню");
        System.out.println("2. Просмотр заказа");
        System.out.println("3. Добавить блюдо в заказ");
        System.out.println("4. Удалить блюдо из заказ");
        System.out.println("5. Оформить заказ");
        System.out.println("6. Отменить заказ");
        System.out.println("7. Выйти");
    }

    @Override
    public void showMenuItems() {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню:" + ConsoleColors.ANSI_RESET);
        for (Dish dish : Menu.getDishes()) {
            System.out.printf("%-20s Цена: %-5d руб.  -  Доступное количество порций: %d шт.%n",
                    dish.getName(), dish.getPrice(), dish.getAvailableQuantity());
        }
    }

    @Override
    public void showOrderItems() {
        System.out.println(ConsoleColors.ANSI_BLUE + "Заказ:" + ConsoleColors.ANSI_RESET);
        for (Dish dish : Order.getDishes()) {
            System.out.printf("%-20s Цена: %-4d руб. %n", dish.getName(), dish.getPrice());
        }
        System.out.printf("%sИтого: %23d  руб.%s %n", ConsoleColors.ANSI_ORANGE, Order.getTotalPrice(), ConsoleColors.ANSI_RESET);
    }

    @Override
    public void showOrderSuccess() {
        System.out.println(ConsoleColors.ANSI_GREEN + "Заказ оформлен" + ConsoleColors.ANSI_RESET);
    }

    @Override
    public void showOrderCancel() {
        System.out.println(ConsoleColors.ANSI_GREEN + "Заказ отменен" + ConsoleColors.ANSI_RESET);
    }

    @Override
    public void showUpdateMenu() {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню:" + ConsoleColors.ANSI_RESET);
        System.out.println("1. Добавить блюдо");
        System.out.println("2. Удалить блюдо");
        System.out.println("3. Изменить блюдо");
        System.out.println("4. Выйти");
    }
}

