package org.hse.software.construction.HW2.controller;

import lombok.*;
import org.hse.software.construction.HW2.model.Dish;
import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.User;
import org.hse.software.construction.HW2.model.UserRole;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminHandler extends Handler {
    private Handler next;
    private Menu menu;

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public void handle(User user) {
        if (user.getRole() == UserRole.ADMIN) {
            menu.addDish(Dish.builder()
                    .name("Burger")
                    .price(100)
                    .timeToCook(10)
                    .preparationDifficulty(1)
                    .availableQuantity(10)
                    .build());
        } else if (next != null) {
            next.handle(user);
        }
    }
}