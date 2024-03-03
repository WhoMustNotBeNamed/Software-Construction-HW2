package org.hse.software.construction.HW2.controller;

import lombok.*;
import org.hse.software.construction.HW2.model.*;
import org.hse.software.construction.HW2.repository.Repository;
import org.hse.software.construction.HW2.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminMenuHandler extends Handler {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Handler next;

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public void handle(User user, Menu menu, /* Order order,*/ MoneyStorage moneyStorage, ReviewService reviewService) {
        if (user.getRole() == UserRole.ADMIN) {
            ConsoleView consoleView = new ConsoleView();

            int choice = 0;

            do {
                consoleView.showMenuItemsAdmin(menu);

                consoleView.showAdminMenu();

                try {
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            addDish(menu);
                            break;
                        case 2:
                            removeDish(menu);
                            break;
                        case 3:
                            updateDish(menu);
                            break;
                        case 4:
                            consoleView.showStatistics(reviewService);
                            break;
                        case 5:
                            consoleView.showMoneyStorageMenu(moneyStorage);
                            break;
                        case 6:
                            break;
                        default:
                            consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 6");
                    }
                } catch (Exception e) {
                    consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 6");
                }
            } while (choice != 6);
        } else if (next != null) {
            next.handle(user, menu, /*order,*/ moneyStorage, reviewService);
        }
    }

    public void addDish(Menu menu) throws IOException {
        System.out.println("Введите название блюда:");
        String name = reader.readLine();

        System.out.println("Введите цену блюда:");
        int price = Integer.parseInt(reader.readLine());

        System.out.println("Введите время приготовления блюда:");
        int time = Integer.parseInt(reader.readLine());

        System.out.println("Введите количество порций:");
        int availableQuantity = Integer.parseInt(reader.readLine());

        menu.addDish(Dish.builder()
                .name(name)
                .price(price)
                .timeToCook(time)
                .availableQuantity(availableQuantity)
                .build());
    }

    public void removeDish(Menu menu) throws IOException {
        ConsoleView consoleView = new ConsoleView();

        if (menu.getDishes().isEmpty() || menu.getDishes() == null) {
            consoleView.showErrorMessage("Меню пустое");
            return;
        }

        System.out.println("Введите название блюда:");
        String name = reader.readLine();

        menu.removeDish(name);
    }

    public void updateDish(Menu menu) throws IOException {
        ConsoleView consoleView = new ConsoleView();

        if (menu.getDishes().isEmpty() || menu.getDishes() == null) {
            consoleView.showErrorMessage("Меню пустое");
            return;
        }

        System.out.println("Введите название блюда:");
        String name = reader.readLine();

        System.out.println("Введите цену блюда:");
        int price = Integer.parseInt(reader.readLine());

        System.out.println("Введите время приготовления блюда:");
        int time = Integer.parseInt(reader.readLine());

        System.out.println("Введите количество порций:");
        int availableQuantity = Integer.parseInt(reader.readLine());

        menu.updateDish(Dish.builder()
                .name(name)
                .price(price)
                .timeToCook(time)
                .availableQuantity(availableQuantity)
                .build());
    }
}