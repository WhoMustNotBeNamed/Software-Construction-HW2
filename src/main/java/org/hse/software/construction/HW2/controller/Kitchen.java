package org.hse.software.construction.HW2.controller;

import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.Order;
import org.hse.software.construction.HW2.model.OrderStatus;
import org.hse.software.construction.HW2.view.ConsoleView;

// Класс для запуска кухни, который запускает потоки для 3х поваров
public class Kitchen {
    private static volatile boolean running = true;
    private static Thread chef1;
    private static Thread chef2;
    private static Thread chef3;

    private final ConsoleView consoleView = new ConsoleView();

    // Запуск кухни
    public void startKitchen(Menu menu) {
        startChef1(menu);
        startChef2(menu);
        startChef3(menu);
    }

    // Остановка кухни
    public void stopKitchen() {
        running = false;
        try {
            chef1.join();
            chef2.join();
            chef3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Запуск потока для повара 1
    private void startChef1(Menu menu) {
        Runnable runnableTask = () -> {
            while (running) {
                checkAndCokking(menu);
            }
        };

        chef1 = new Thread(runnableTask);
        chef1.start();
    }

    // Запуск потока для повара 2
    private void startChef2(Menu menu) {
        Runnable runnableTask = () -> {
            while (running) {
                try {
                    Thread.sleep(1000); // Задержка, чтобы повар два вступил в работу после повара 1 и не пересекались
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                checkAndCokking(menu);
            }
        };

        chef2 = new Thread(runnableTask);
        chef2.start();
    }

    // Запуск потока для повара 3
    private void startChef3(Menu menu) {
        Runnable runnableTask = () -> {
            while (running) {
                try {
                    Thread.sleep(2000); // Задержка, чтобы повар три вступил в работу после повара 2 и не пересекались
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                checkAndCokking(menu);
            }
        };

        chef3 = new Thread(runnableTask);
        chef3.start();
    }

    // Проверка заказов и приготовление блюд
    private void checkAndCokking(Menu menu) {
            for (Order order : menu.getOrders()) {
                if (order.getStatus() == OrderStatus.ACCEPTED) {
                    order.status = OrderStatus.IN_PROGRESS;
                    try {
                        Thread.sleep(1000 * order.getTotalCookingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    order.status = OrderStatus.DONE;
                    consoleView.showOrderDone(order.id);
                }
            }
    }
}