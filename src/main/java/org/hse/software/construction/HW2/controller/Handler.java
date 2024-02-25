package org.hse.software.construction.HW2.controller;

import org.hse.software.construction.HW2.model.User;

public abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handle(User user);
}