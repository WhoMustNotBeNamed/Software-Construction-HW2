package org.hse.software.construction.HW2.model;

import lombok.*;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    //TODO: добавить поле для хранения имени пользователя, чтобы возвращать его заказ по его имени вместо id
    @Getter @Setter
    public String id;
    @Getter @Setter
    private ArrayList<Dish> dishes;
    @Getter @Setter
    private int totalPrice;
    @Getter @Setter
    private int totalCookingTime;
    @Getter @Setter
    public OrderStatus status;

    public Order(String id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public void addDish(Dish dish) {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        if (dishes == null) {
            return;
        }
        dishes.remove(dish);
    }

    public int getTotalPrice() {
        totalPrice = 0;
        for (Dish dish : dishes) {
            totalPrice += dish.getPrice();
        }
        return totalPrice;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public int getTotalCookingTime() {
        for (Dish dish : dishes) {
            totalCookingTime += dish.getTimeToCook();
        }
        return totalCookingTime;
    }

    public Order clone() {
        Order clonedOrder = new Order();
        clonedOrder.setId(this.id);
        clonedOrder.setDishes(new ArrayList<>(this.dishes));
        clonedOrder.setStatus(this.status);
        // Клонируйте все остальные поля по аналогии
        return clonedOrder;
    }
}