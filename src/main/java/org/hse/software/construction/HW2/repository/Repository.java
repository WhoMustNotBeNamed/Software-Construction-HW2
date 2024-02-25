package org.hse.software.construction.HW2.repository;

import org.hse.software.construction.HW2.model.Account;
import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.Order;
import org.hse.software.construction.HW2.model.Snapshot;

import java.util.ArrayList;

public class Repository {
    private ArrayList<String> spanshots = new ArrayList<>();

    public void saveSnapshot(Menu menu, Account account, Order order) {
        MenuRepository menuRepository = new MenuRepository();
        menuRepository.saveMenu(menu, "menu.json");

        AccountRepository accountRepository = new AccountRepository();
        accountRepository.saveAccount(account, "users.json");

        OrderRepository orderRepository = new OrderRepository();
        orderRepository.saveOrder(order, "order.json");
        //spanshots.add(snapshot);
    }

    public Snapshot restoreSnapshot() {
        MenuRepository menuRepository = new MenuRepository();
        Menu menu = menuRepository.restoreMenu("menu.json");

        AccountRepository userRepository = new AccountRepository();
        Account account = userRepository.restoreUser("users.json");

        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.restoreMenu("order.json");

        Snapshot snapshot = new Snapshot();
        snapshot.setMenu(menu);
        snapshot.setAccount(account);
        snapshot.setOrder(order);

        return snapshot;
    }
}
