package org.hse.software.construction.HW2.view;

import org.hse.software.construction.HW2.model.*;

public class ConsoleView implements View {

    // Показать форму входа
    @Override
    public void showRegistrationForm() {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню регистрации:" + ConsoleColors.ANSI_RESET);
        System.out.println("1. Войти:");
        System.out.println("2. Зарегистрироваться:");
        System.out.println("3. Выйти");
    }

    // Показать сообщение об успешной регистрации
    @Override
    public void showRegistrationSuccess(String name) {
        System.out.println(ConsoleColors.ANSI_GREEN + "Регистрация пользователя " + name + " прошла успешно" + ConsoleColors.ANSI_RESET);
    }

    // Показать сообщение об ошибке
    @Override
    public void showErrorMessage(String message) {
        System.out.println(ConsoleColors.ANSI_RED + "Ошибка: " + message + ConsoleColors.ANSI_RESET);
    }

    // Показать пункты меню
    @Override
    public void showMenuItems(Menu menu) {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню:" + ConsoleColors.ANSI_RESET);
        for (Dish dish : menu.getDishes()) {
            System.out.printf("%-10s Цена (руб.): %-10d Доступное количество порций: %3d шт.%n",
                    dish.getName(), dish.getPrice(), dish.getAvailableQuantity());
        }
    }

    // Показать пункты меню администратора
    @Override
    public void showMenuItemsAdmin(Menu menu) {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню:" + ConsoleColors.ANSI_RESET);
        for (Dish dish : menu.getDishes()) {
            System.out.printf("%-15s Цена (руб.): %-8d Время приготовления: %-8d Доступное количество порций: %3d шт.%n",
                    dish.getName(), dish.getPrice(), dish.getTimeToCook(), dish.getAvailableQuantity());
        }
    }

    // Показать меню действий администратора
    @Override
    public void showAdminMenu() {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню взаимодействия администратора:" + ConsoleColors.ANSI_RESET);
        System.out.println("1. Добавить блюдо");
        System.out.println("2. Удалить блюдо");
        System.out.println("3. Изменить блюдо");
        System.out.println("4. Показать статистику");
        System.out.println("5. Показать кассу");
        System.out.println("6. Выйти");
    }

    // Показать меню действий посетителя
    @Override
    public void showVisitorMenu() {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню взаимодействия посетителя:" + ConsoleColors.ANSI_RESET);
        System.out.println("1. Просмотр заказа");
        System.out.println("2. Добавить блюдо в заказ");
        System.out.println("3. Удалить блюдо из заказ");
        System.out.println("4. Оформить заказ");
        System.out.println("5. Отменить заказ");
        System.out.println("6. Оплатить заказ");
        System.out.println("7. Выйти");
    }

    // Показать пункты заказа
    @Override
    public void showOrderItems(Order order) {
        System.out.println(ConsoleColors.ANSI_BLUE + "Заказ:" + ConsoleColors.ANSI_RESET);
        for (Dish dish : order.getDishes()) {
            System.out.printf("%-20s Цена: %-4d руб. %n", dish.getName(), dish.getPrice());
        }
        System.out.printf("%sИтого: %23d  руб.%s %n", ConsoleColors.ANSI_ORANGE, order.getTotalPrice(), ConsoleColors.ANSI_RESET);
    }

    // Показать сообщение об успешном заказе
    @Override
    public void showOrderSuccess() {
        System.out.println(ConsoleColors.ANSI_GREEN + "Заказ оформлен" + ConsoleColors.ANSI_RESET);
    }

    // Показать сообщение об отмене заказа
    @Override
    public void showOrderCancel() {
        System.out.println(ConsoleColors.ANSI_GREEN + "Заказ отменен" + ConsoleColors.ANSI_RESET);
    }

    // Показать сообщение о выполненном заказа
    @Override
    public void showOrderDone(String id) {
        System.out.println(ConsoleColors.ANSI_GREEN + "Заказ для " + id + " выполнен" + ConsoleColors.ANSI_RESET);
    }

    // Показать сообщение о оплате заказа
    @Override
    public void showOrderPaid(String id) {
        System.out.println(ConsoleColors.ANSI_GREEN + "Заказ для " + id + " оплачен и выдан" + ConsoleColors.ANSI_RESET);
    }

    // Показать меню оплаты
    @Override
    public void showPaymentMenu() {
        System.out.println(ConsoleColors.ANSI_BLUE + "Меню оплаты:" + ConsoleColors.ANSI_RESET);
        System.out.println("1. Оплатить заказ наличными");
        System.out.println("2. Оплатить заказ картой");
        System.out.println("3. Назад");
    }

    // Показать статистику
    @Override
    public void showStatistics(ReviewService reviewService) {
        System.out.println(ConsoleColors.ANSI_BLUE + "\nСтатистика:" + ConsoleColors.ANSI_RESET);
        System.out.println("Самое популярное блюдо: " + reviewService.getMostPopularDish().getName());
        System.out.println("Средняя оценка: " + reviewService.getAverageRating() + "\n");
    }

    // Показать меню кассы
    @Override
    public void showMoneyStorageMenu(MoneyStorage moneyStorage) {
        System.out.println(ConsoleColors.ANSI_BLUE + "\nКасса:" + ConsoleColors.ANSI_RESET);
        System.out.println("Наличные: " + moneyStorage.getCash());
        System.out.println("Безналичные: " + moneyStorage.getNonCash());
        System.out.println("Всего: " + moneyStorage.getTotalMoney() + "\n");
    }
}

