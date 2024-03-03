package org.hse.software.construction.HW2.view;

import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.MoneyStorage;
import org.hse.software.construction.HW2.model.Order;
import org.hse.software.construction.HW2.model.ReviewService;

public interface View {
    // Показать форму входа
    void showRegistrationForm();

    // Показать сообщение об успешной регистрации
    void showRegistrationSuccess(String name);

    // Показать сообщение об ошибке
    void showErrorMessage(String message);

    // Показать пункты меню
    void showMenuItems(Menu menu);

    // Показать пункты меню администратора
    void showMenuItemsAdmin(Menu menu);

    // Показать меню действий администратора
    void showAdminMenu();

    // Показать меню действий посетителя
    void showVisitorMenu();

    // Показать пункты заказа
    void showOrderItems(Order order);

    // Показать сообщение об успешном заказе
    void showOrderSuccess();

    // Показать сообщение об отмене заказа
    void showOrderCancel();

    // Показать сообщение о выполненном заказа
    void showOrderDone(String id);

    // Показать сообщение о оплате заказа
    void showOrderPaid(String id);

    // Показать меню оплаты
    void showPaymentMenu();

    // Показать статистику
    void showStatistics(ReviewService reviewService);

    // Показать меню кассы
    void showMoneyStorageMenu(MoneyStorage moneyStorage);
}
