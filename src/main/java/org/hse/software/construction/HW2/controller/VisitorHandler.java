package org.hse.software.construction.HW2.controller;

import lombok.*;
import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.User;
import org.hse.software.construction.HW2.model.UserRole;
import org.hse.software.construction.HW2.view.ConsoleView;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorHandler extends Handler {
    private Handler next;
    private Menu menu;

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public void handle(User user) {
        if (user.getRole() == UserRole.VISITOR) {
            ConsoleView consoleView = new ConsoleView();
            consoleView.showMenu();
        } else if (next != null) {
            next.handle(user);
        }
    }
}
