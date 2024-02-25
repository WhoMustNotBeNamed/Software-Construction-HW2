package org.hse.software.construction.HW2.view;

import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.Order;

public interface View {
    void showRegistrationForm();

    void showRegistrationSuccess();

    void showErrorMessage(String message);

    void showMenu();

    void showMenuItems(Menu menu);

    void showOrderItems(Order order);

    void showOrderSuccess();

    void showOrderCancel();

    void showUpdateMenu();
}
