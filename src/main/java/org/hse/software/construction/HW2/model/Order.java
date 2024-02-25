package org.hse.software.construction.HW2.model;

import lombok.*;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Getter @Setter
    public int id;
    @Getter @Setter
    private ArrayList<Dish> dishes;
    @Getter @Setter
    private int totalPrice;
    @Getter @Setter
    private int totalCookingTime;
    @Getter @Setter
    public OrderStatus status;

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

    public int getTotalPrice() {
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
}