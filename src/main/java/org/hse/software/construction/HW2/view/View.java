package org.hse.software.construction.HW2.view;

public interface View {
    void showRegistrationForm();

    void showRegistrationSuccess();

    void showErrorMessage(String message);

    void showMenu();

    void showMenuItems();

    void showOrderItems();

    void showOrderSuccess();

    void showOrderCancel();

    void showUpdateMenu();
}
