package org.hse.software.construction.HW2.controller;

import lombok.*;
import org.hse.software.construction.HW2.model.*;
import org.hse.software.construction.HW2.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorMenuHandler extends Handler {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final ConsoleView consoleView = new ConsoleView();
    private Handler next;
    private Menu menu;
    private Order order;
    private User user;

    // Метод для установки следующего обработчика
    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    // Метод для обработки запроса
    @Override
    public void handle(User user, Menu menu, MoneyStorage moneyStorage, ReviewService reviewService) {
        // Если пользователь - посетитель, то показать посетительское меню и обработать запрос
        if (user.getRole() == UserRole.VISITOR) {
            order = menu.getOrderByID(user.getUsername());
            this.menu = menu;
            this.user = user;

            int choice = 0;

            do {
                consoleView.showMenuItems(menu);

                consoleView.showVisitorMenu();

                try {
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1: // Показать заказ
                            showOrder(order);
                            break;
                        case 2: // Добавить блюдо
                            addDishToOrder(menu, order);
                            break;
                        case 3: // Удалить блюдо
                            removeDishFromOrder(menu, order);
                            break;
                        case 4: // Сделать заказ
                            makeOrder(menu, order);
                            menu.updateOrder(order);
                            break;
                        case 5: // Отменить заказ
                            cancelOrder(order);
                            break;
                        case 6: // Оплатить заказ
                            payOrder(order, moneyStorage, reviewService);
                            break;
                        case 7: // Выйти
                            break;
                        default:
                            consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 7");
                    }
                } catch (Exception e) {
                    consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 7");
                }
            } while (choice != 7);

        } else if (next != null) {
            next.handle(user, menu, moneyStorage, reviewService);
        }
    }

    // Метод для добавления блюда в заказ
    public void addDishToOrder(Menu menu, Order order) throws IOException {
        System.out.println("Введите название блюда:");
        String name = reader.readLine();

        try {
            if (menu.getDishByName(name).getAvailableQuantity() == 0) {
                consoleView.showErrorMessage("Блюдо закончилось");
                return;
            }
            order.addDish(menu.getDishByName(name));
            menu.getDishByName(name).setAvailableQuantity(menu.getDishByName(name).getAvailableQuantity() - 1);
        } catch (Exception e) {
            consoleView.showErrorMessage("Блюда с таким названием не существует");
        }
    }

    // Метод для показа заказа
    public void showOrder(Order order) {
        if (order.getDishes() == null || order.getDishes().isEmpty()) {
            consoleView.showErrorMessage("Заказ пуст");
            return;
        }
        System.out.println();
        consoleView.showOrderItems(order);
        System.out.println();
    }

    // Метод для удаления блюда из заказа
    public void removeDishFromOrder(Menu menu, Order order) throws IOException {
        System.out.println("Введите название блюда:");
        String name = reader.readLine();

        try {
            order.removeDish(menu.getDishByName(name));
            menu.getDishByName(name).setAvailableQuantity(menu.getDishByName(name).getAvailableQuantity() + 1);
        } catch (Exception e) {
            consoleView.showErrorMessage("Блюда с таким названием не существует");
        }
    }

    // Метод для сообщения о готовности заказа для дальнейшего его приготовления потоками поворов
    public void makeOrder(Menu menu, Order order) throws IOException {
        if (order.getDishes() == null || order.getDishes().isEmpty()) {
            consoleView.showErrorMessage("Заказ пуст");
            return;
        }
        menu.updateOrder(order);
        order.updateStatus(OrderStatus.ACCEPTED);
        consoleView.showOrderSuccess();
    }

    // Метод для отмены заказа
    public void cancelOrder(Order order) throws IOException {
        if (order.getStatus() == OrderStatus.DONE || order.getStatus() == OrderStatus.IN_PROGRESS) {
            consoleView.showErrorMessage("Заказ нельзя отменить, так как он уже готовится или готов");
            return;
        }
        ArrayList<Dish> dishesToRemove = new ArrayList<>();
        for (Dish dish : order.getDishes()) {
            dishesToRemove.add(dish);
            dish.setAvailableQuantity(dish.getAvailableQuantity() + 1);
        }
        order.getDishes().removeAll(dishesToRemove);

        consoleView.showOrderCancel();
    }

    // Метод для оплаты заказа
    public void payOrder(Order order, MoneyStorage moneyStorage, ReviewService reviewService) throws IOException {
        if (order.getStatus() != OrderStatus.DONE) {
            consoleView.showErrorMessage("Заказ не готов");
            return;
        } else if (order.getDishes() == null || order.getDishes().isEmpty()) {
            consoleView.showErrorMessage("Заказ пуст");
            return;
        }

        int choice = 0;

        do {
            consoleView.showPaymentMenu();

            choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1: // Наличные
                    moneyStorage.addCash(order.getTotalPrice());
                    order.updateStatus(OrderStatus.PAYED);

                    addReview(reviewService, order);
                    break;
                case 2: // Безналичные
                    moneyStorage.addNonCash(order.getTotalPrice());
                    order.updateStatus(OrderStatus.PAYED);

                    addReview(reviewService, order);
                    break;
                case 3: // Отмена
                    break;
                default:
                    consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 3");
            }
        } while (choice != 3 && order.getStatus() != OrderStatus.PAYED);

        order.setDishes(null);
    }

    // Метод для добавления отзыва
    public void addReview(ReviewService reviewService, Order order) throws IOException {
        int rating = 0;

        do {
            System.out.println("Какую оценку вы бы хотели поставить от 1 до 5?");
            rating = Integer.parseInt(reader.readLine());
            if (rating < 1 || rating > 5) {
                consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 5");
            }
        } while (rating < 1 || rating > 5);

        System.out.println("Введите отзыв:");
        String review = reader.readLine();

        Order clonedOrder = order.clone();
        reviewService.addReview(Review.builder()
                .review(review)
                .rating(rating)
                .author(clonedOrder.getId())
                .order(clonedOrder)
                .build());

        consoleView.showOrderPaid(order.getId());
    }
}
