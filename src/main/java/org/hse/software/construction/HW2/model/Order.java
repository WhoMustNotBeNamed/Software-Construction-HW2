package org.hse.software.construction.HW2.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hse.software.construction.HW2.repository.OrderMemento;

import java.util.ArrayList;

@Data
@Builder
public class Order {
    @Getter @Setter
    private int id;
    @Getter
    private static ArrayList<Dish> dishes;
    @Setter
    private static int totalPrice;
    @Setter
    private static int totalCookingTime;
    @Getter @Setter
    private OrderStatus status;

    public Order(int id, OrderStatus status) {
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

    public static int getTotalPrice() {
        for (Dish dish : dishes) {
            totalPrice += dish.getPrice();
        }
        return totalPrice;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public static int getTotalCookingTime() {
        for (Dish dish : dishes) {
            totalCookingTime += dish.getTimeToCook();
        }
        return totalCookingTime;
    }


}