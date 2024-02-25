package org.hse.software.construction.HW2;

import org.hse.software.construction.HW2.controller.Controller;
import org.hse.software.construction.HW2.model.*;
import org.hse.software.construction.HW2.repository.MenuRepository;
import org.hse.software.construction.HW2.repository.AccountRepository;
import org.hse.software.construction.HW2.repository.OrderRepository;
import org.hse.software.construction.HW2.view.ConsoleView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw2Application {

	public static void main(String[] args) {

//		ConsoleView registrationView = new ConsoleView();
//
//		Order order = new Order();
//		OrderRepository orderRepository = new OrderRepository();
//		order = orderRepository.restoreMenu("order.json");
//
//		Menu menu = new Menu();
//		MenuRepository menuRepository = new MenuRepository();
//		menu = menuRepository.restoreMenu("menu.json");
//
//		Account account = new Account();
//		AccountRepository userRepository = new AccountRepository();
//		account = userRepository.restoreUser("users.json");

		Controller controller = new Controller();
	}
}
