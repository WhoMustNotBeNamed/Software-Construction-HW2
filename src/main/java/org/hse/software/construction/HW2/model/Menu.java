package org.hse.software.construction.HW2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
//@Getter
@Setter
public class Menu {
    @Getter
    private static ArrayList<Dish> dishes;

    public void addDish(Dish dish) {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Dish dish : dishes) {
            sb.append(dish.getName()).append(" - ").append(dish.getPrice()).append(" - ").append(dish.getAvailableQuantity()).append("\n");
        }
        return sb.toString();
    }
}
