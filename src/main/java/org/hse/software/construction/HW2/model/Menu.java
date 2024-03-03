package org.hse.software.construction.HW2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Getter @Setter @JsonIgnore
    public volatile ArrayList<Order> orders = new ArrayList<>();

    // Метод для получения блюд
    public ArrayList<Dish> getDishes() {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        return dishes;
    }

    // Метод для добавления блюда
    public void addDish(Dish dish) {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        dishes.add(dish);
    }

    // Метод для удаления блюда
    public void removeDish(String dishName) {
        Dish dish = getDishByName(dishName);
        dishes.remove(dish);
    }

    // Метод для получения блюда по имени
    public Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        return null;
    }

    // Метод для обновления блюда
    public void updateDish(Dish dish) {
        for (int i = 0; i < dishes.size(); i++) {
            if (dishes.get(i).getName().equals(dish.getName())) {
                dishes.set(i, dish);
                return;
            }
        }
    }

    // Метод для добавления заказа
    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    // Метод для удаления заказа
    public void removeOrder(Order order) {
        if (orders == null) {
            return;
        }
        orders.remove(order);
    }

    // Метод для получения заказа по ID
    public Order getOrderByID(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        addOrder(new Order(id, OrderStatus.NEW));
        return getOrderByID(id);
    }

    // Метод для обновления заказа
    public void updateOrder(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(order.getId())) {
                orders.set(i, order);
                return;
            }
        }
    }
}
