package org.hse.software.construction.HW2.model;

import lombok.*;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Setter
    public String id;
    @Setter
    private ArrayList<Dish> dishes;
    @Setter
    private int totalPrice;
    @Setter
    private int totalCookingTime;
    @Setter
    public OrderStatus status;

    // Конструктор класса Order
    public Order(String id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    // Метод для добавления блюда в заказ
    public void addDish(Dish dish) {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        dishes.add(dish);
    }

    // Метод для удаления блюда из заказа
    public void removeDish(Dish dish) {
        if (dishes == null) {
            return;
        }
        dishes.remove(dish);
    }

    // Метод для получения общей стоимости заказа
    public int getTotalPrice() {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }

        totalPrice = 0;
        for (Dish dish : dishes) {
            totalPrice += dish.getPrice();
        }
        return totalPrice;
    }

    // Метод для обновления статуса заказа
    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    // Метод для получения общего времени приготовления заказа
    public int getTotalCookingTime() {
        for (Dish dish : dishes) {
            totalCookingTime += dish.getTimeToCook();
        }
        return totalCookingTime;
    }

    // Метод для клонирования заказа
    public Order clone() {
        Order clonedOrder = new Order();
        clonedOrder.setId(this.id);
        clonedOrder.setDishes(new ArrayList<>(this.dishes));
        clonedOrder.setStatus(this.status);
        return clonedOrder;
    }
}