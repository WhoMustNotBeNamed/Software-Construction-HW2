package org.hse.software.construction.HW2.repository;

import org.hse.software.construction.HW2.model.*;

import java.util.ArrayList;

public class Repository {
    //TODO убрать, если не придумаю как использовать
    private ArrayList<String> spanshots = new ArrayList<>();

    public void saveSnapshot(Snapshot snapshot) {
        MenuRepository menuRepository = new MenuRepository();
        menuRepository.saveMenu(snapshot.getMenu(), "menu.json");

        AccountRepository accountRepository = new AccountRepository();
        accountRepository.saveAccount(snapshot.getAccount(), "users.json");

        //OrderRepository orderRepository = new OrderRepository();
        //orderRepository.saveOrder(snapshot.getOrder(), "order.json");

        MoneyStorageRepository moneyStorageRepository = new MoneyStorageRepository();
        moneyStorageRepository.saveCashRegister(snapshot.getMoneyStorage(), "moneyStorage.json");

        ReviewRepository reviewServiceRepository = new ReviewRepository();
        reviewServiceRepository.saveReview(snapshot.getReviewService(), "review.json");
        //spanshots.add(snapshot);
    }

    public Snapshot restoreSnapshot() {
        MenuRepository menuRepository = new MenuRepository();
        Menu menu = menuRepository.restoreMenu("menu.json");

        AccountRepository userRepository = new AccountRepository();
        Account account = userRepository.restoreUser("users.json");

        //OrderRepository orderRepository = new OrderRepository();
        //Order order = orderRepository.restoreMenu("order.json");

        MoneyStorageRepository moneyStorageRepository = new MoneyStorageRepository();
        MoneyStorage moneyStorage = moneyStorageRepository.restoreCashRegister("moneyStorage.json");

        ReviewRepository reviewServiceRepository = new ReviewRepository();
        ReviewService reviewService = reviewServiceRepository.restoreReview("review.json");

        Snapshot snapshot = new Snapshot();
        snapshot.setMenu(menu);
        snapshot.setAccount(account);
        //snapshot.setOrder(order);
        snapshot.setMoneyStorage(moneyStorage);
        snapshot.setReviewService(reviewService);

        return snapshot;
    }
}
