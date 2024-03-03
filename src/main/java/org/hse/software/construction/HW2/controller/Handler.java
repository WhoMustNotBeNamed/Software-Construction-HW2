package org.hse.software.construction.HW2.controller;

import org.hse.software.construction.HW2.model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handle(User user, Menu menu, /* Order order,*/ MoneyStorage moneyStorage, ReviewService reviewService);
}