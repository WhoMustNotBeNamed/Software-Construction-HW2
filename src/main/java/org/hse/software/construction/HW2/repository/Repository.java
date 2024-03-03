package org.hse.software.construction.HW2.repository;

import org.hse.software.construction.HW2.model.*;

public class Repository {
    // Сохранение состояния
    public void saveSnapshot(Snapshot snapshot) {
        MenuRepository menuRepository = new MenuRepository();
        menuRepository.saveMenu(snapshot.getMenu(), "menu.json");

        AccountRepository accountRepository = new AccountRepository();
        accountRepository.saveAccount(snapshot.getAccount(), "users.json");

        MoneyStorageRepository moneyStorageRepository = new MoneyStorageRepository();
        moneyStorageRepository.saveCashRegister(snapshot.getMoneyStorage(), "moneyStorage.json");

        ReviewRepository reviewServiceRepository = new ReviewRepository();
        reviewServiceRepository.saveReview(snapshot.getReviewService(), "review.json");
    }

    // Восстановление состояния
    public Snapshot restoreSnapshot() {
        MenuRepository menuRepository = new MenuRepository();
        Menu menu = menuRepository.restoreMenu("menu.json");

        AccountRepository userRepository = new AccountRepository();
        Account account = userRepository.restoreUser("users.json");

        MoneyStorageRepository moneyStorageRepository = new MoneyStorageRepository();
        MoneyStorage moneyStorage = moneyStorageRepository.restoreCashRegister("moneyStorage.json");

        ReviewRepository reviewServiceRepository = new ReviewRepository();
        ReviewService reviewService = reviewServiceRepository.restoreReview("review.json");

        Snapshot snapshot = new Snapshot();
        snapshot.setMenu(menu);
        snapshot.setAccount(account);
        snapshot.setMoneyStorage(moneyStorage);
        snapshot.setReviewService(reviewService);

        return snapshot;
    }
}
