package org.hse.software.construction.HW2.controller;

import org.hse.software.construction.HW2.model.*;

// Абстрактный класс для обработчиков для реализации паттерна "Цепочка обязанностей"
public abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handle(User user, Menu menu, /* Order order,*/ MoneyStorage moneyStorage, ReviewService reviewService);
}