package org.hse.software.construction.HW2.view;

import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.MoneyStorage;
import org.hse.software.construction.HW2.model.Order;
import org.hse.software.construction.HW2.model.ReviewService;

public interface View {
    void showRegistrationForm();

    void showRegistrationSuccess(String name);

    void showErrorMessage(String message);

    void showMenu();

    void showMenuItems(Menu menu);

    void showMenuItemsAdmin(Menu menu);

    public void showAdminMenu();

    public void showVisitorMenu();

    void showOrderItems(Order order);

    void showOrderSuccess();

    void showOrderCancel();

    public void showOrderDone(String id);

    void showUpdateMenu();

    void showPaymentMenu();

    void showStatistics(ReviewService reviewService);

    void showMoneyStorageMenu(MoneyStorage moneyStorage);
}
