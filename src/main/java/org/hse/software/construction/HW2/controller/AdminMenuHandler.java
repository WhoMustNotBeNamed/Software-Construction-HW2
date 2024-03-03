package org.hse.software.construction.HW2.controller;

import lombok.*;
import org.hse.software.construction.HW2.model.*;
import org.hse.software.construction.HW2.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminMenuHandler extends Handler {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final ConsoleView consoleView = new ConsoleView();
    private Handler next;

    // Метод для установки следующего обработчика
    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    // Метод для обработки запроса
    @Override
    public void handle(User user, Menu menu, MoneyStorage moneyStorage, ReviewService reviewService) {
        // Если пользователь - администратор, то показать администраторское меню и обработать запрос
        if (user.getRole() == UserRole.ADMIN) {
            int choice = 0;

            do {
                consoleView.showMenuItemsAdmin(menu);

                consoleView.showAdminMenu();

                try {
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1: // Добавить блюдо
                            addDish(menu);
                            break;
                        case 2: // Удалить блюдо
                            removeDish(menu);
                            break;
                        case 3: // Обновить блюдо
                            updateDish(menu);
                            break;
                        case 4: // Показать статистику
                            consoleView.showStatistics(reviewService);
                            break;
                        case 5: // Показать кассу
                            consoleView.showMoneyStorageMenu(moneyStorage);
                            break;
                        case 6: // Выйти
                            break;
                        default:
                            consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 6");
                    }
                } catch (Exception e) {
                    consoleView.showErrorMessage("Некорректный ввод. Введите число от 1 до 6");
                }
            } while (choice != 6);
        } else if (next != null) {
            next.handle(user, menu, moneyStorage, reviewService);
        }
    }

    // Метод для добавления блюда в меню
    public void addDish(Menu menu) throws IOException {
        Dish dish = takeDishParameters(menu);

        menu.addDish(dish);
    }

    // Метод для удаления блюда из меню
    public void removeDish(Menu menu) throws IOException {
        if (menu.getDishes().isEmpty() || menu.getDishes() == null) {
            consoleView.showErrorMessage("Меню пустое");
            return;
        }

        System.out.println("Введите название блюда:");
        String name = reader.readLine();

        menu.removeDish(name);
    }

    // Метод для обновления блюда в меню
    public void updateDish(Menu menu) throws IOException {
        if (menu.getDishes().isEmpty() || menu.getDishes() == null) {
            consoleView.showErrorMessage("Меню пустое");
            return;
        }

        Dish dish = takeDishParameters(menu);

        menu.updateDish(dish);
    }

    // Метод для получения параметров блюда
    public Dish takeDishParameters(Menu menu) throws IOException {
        System.out.println("Введите название блюда:");
        String name = reader.readLine();

        System.out.println("Введите цену блюда:");
        int price = Integer.parseInt(reader.readLine());

        System.out.println("Введите время приготовления блюда:");
        int time = Integer.parseInt(reader.readLine());

        System.out.println("Введите количество порций:");
        int availableQuantity = Integer.parseInt(reader.readLine());

        return Dish.builder()
                .name(name)
                .price(price)
                .timeToCook(time)
                .availableQuantity(availableQuantity)
                .build();
    }
}