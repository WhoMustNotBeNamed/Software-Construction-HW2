package org.hse.software.construction.HW2.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.ArrayList;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @JsonSerialize
    public ArrayList<Dish> dishes = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public ArrayList<Dish> getDishes() {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        return dishes;
    }

    public void addDish(Dish dish) {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        dishes.add(dish);
    }

    public void removeDish(String dishName) {
        Dish dish = getDishByName(dishName);
        dishes.remove(dish);
    }

    public Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        return null;
    }

    public void updateDish(Dish dish) {
        for (int i = 0; i < dishes.size(); i++) {
            if (dishes.get(i).getName().equals(dish.getName())) {
                dishes.set(i, dish);
                return;
            }
        }
    }

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    public void removeOrder(Order order) {
        if (orders == null) {
            return;
        }
        orders.remove(order);
    }

    public Order getOrderByID(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return new Order(id, OrderStatus.NEW);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Dish dish : dishes) {
            sb.append(dish.getName()).append(" - ").append(dish.getPrice()).append(" - ").append(dish.getAvailableQuantity()).append("\n");
        }
        return sb.toString();
    }
}
