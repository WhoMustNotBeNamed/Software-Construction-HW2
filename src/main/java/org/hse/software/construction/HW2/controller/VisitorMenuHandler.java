package org.hse.software.construction.HW2.controller;

import lombok.*;
import org.hse.software.construction.HW2.model.*;
import org.hse.software.construction.HW2.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorMenuHandler extends Handler {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Handler next;
    private Menu menu;

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public void handle(User user, Menu menu, Order order, MoneyStorage moneyStorage, ReviewService reviewService) {
        if (user.getRole() == UserRole.VISITOR) {
            ConsoleView consoleView = new ConsoleView();

            int choice = 0;

            do {
                consoleView.showMenuItems(menu);

                consoleView.showVisitorMenu();

                try {
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            showOrder(order);
                            break;
                        case 2:
                            addDishToOrder(menu, order);
                            break;
                        case 3:
                            removeDishFromOrder(menu, order);
                            break;
                        case 4:
                            makeOrder(order);
                            break;
                        case 5:
                            cancelOrder(order);
                            break;
                        case 6:
                            payOrder(order, moneyStorage, reviewService);
                            break;
                        case 7:
                            break;
                        default:
                            consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 7");
                    }
                } catch (Exception e) {
                    consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 7");
                }
            } while (choice != 7);

        } else if (next != null) {
            next.handle(user, menu, order, moneyStorage, reviewService);
        }
    }

    public void addDishToOrder(Menu menu, Order order) throws IOException, IOException {
        System.out.println("Введите название блюда:");
        String name = reader.readLine();

        try {
            order.addDish(menu.getDishByName(name));
        } catch (Exception e) {
            System.out.println("Блюда с таким названием не существует");
        }
    }

    public void showOrder(Order order) {
        ConsoleView consoleView = new ConsoleView();

        if (order.getDishes() == null || order.getDishes().isEmpty()) {
            consoleView.showErrorMessage("Заказ пуст");
            return;
        }
        System.out.println();
        consoleView.showOrderItems(order);
        System.out.println();
    }

    public void removeDishFromOrder(Menu menu, Order order) throws IOException {
        System.out.println("Введите название блюда:");
        String name = reader.readLine();

        try {
            order.removeDish(menu.getDishByName(name));
        } catch (Exception e) {
            System.out.println("Блюда с таким названием не существует");
        }
    }

    public void makeOrder(Order order) {
        ConsoleView consoleView = new ConsoleView();
        if (order.getDishes() == null || order.getDishes().isEmpty()) {
            consoleView.showErrorMessage("Заказ пуст");
            return;
        }
        //order.updateStatus(OrderStatus.ACCEPTED);
        order.updateStatus(OrderStatus.DONE);
    }

    public void cancelOrder(Order order) {
        if (order.getStatus() == OrderStatus.DONE || order.getStatus() == OrderStatus.IN_PROGRESS){
            ConsoleView consoleView = new ConsoleView();
            consoleView.showErrorMessage("Заказ нельзя отменить, так как он уже готовится или готов");
            return;
        }
        order.setDishes(null);
    }

    public void payOrder(Order order, MoneyStorage moneyStorage, ReviewService reviewService) {
        ConsoleView consoleView = new ConsoleView();

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

            try {
                choice = Integer.parseInt(reader.readLine());
                switch (choice) {
                    case 1:
                        moneyStorage.addCash(order.getTotalPrice());
                        order.updateStatus(OrderStatus.PAYED);

                        addReview(reviewService, order);
                        break;
                    case 2:
                        moneyStorage.addNonCash(order.getTotalPrice());
                        order.updateStatus(OrderStatus.PAYED);

                        addReview(reviewService, order);
                        break;
                    case 3:
                        break;
                    default:
                        consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 3");
                }
            } catch (Exception e) {
                consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 3" + e);
            }
        } while (choice != 3 && order.getStatus() != OrderStatus.PAYED);

        order.setDishes(null);
    }

    public void addReview(ReviewService reviewService, Order order) throws IOException {
        ConsoleView consoleView = new ConsoleView();
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


    }
}
