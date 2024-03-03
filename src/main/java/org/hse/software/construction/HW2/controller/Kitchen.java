package org.hse.software.construction.HW2.controller;

import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.Order;
import org.hse.software.construction.HW2.model.OrderStatus;
import org.hse.software.construction.HW2.view.ConsoleView;

public class Kitchen {
    private static volatile boolean running = true;
    private static Thread chef1;
    private static Thread chef2;
    private static Thread chef3;

    private final ConsoleView consoleView = new ConsoleView();

    public void startKitchen(Menu menu) {
        startChef1(menu);
        startChef2(menu);
        startChef3(menu);
    }

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

    private void startChef1(Menu menu) {
        Runnable runnableTask = () -> {
            while (running) {
                checkAndCokking(menu);
            }
        };

        chef1 = new Thread(runnableTask);
        chef1.start();
    }

    private void startChef2(Menu menu) {
        Runnable runnableTask = () -> {
            while (running) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                checkAndCokking(menu);
            }
        };

        chef2 = new Thread(runnableTask);
        chef2.start();
    }

    private void startChef3(Menu menu) {
        Runnable runnableTask = () -> {
            while (running) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                checkAndCokking(menu);
            }
        };

        chef3 = new Thread(runnableTask);
        chef3.start();
    }

    private void checkAndCokking(Menu menu) {
        //synchronized (menu.getOrders()) {
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

                   // order.unlockOrder();
                }
            }
        //}
    }
}