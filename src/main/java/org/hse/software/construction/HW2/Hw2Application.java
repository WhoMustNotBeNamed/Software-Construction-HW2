package org.hse.software.construction.HW2;

import org.hse.software.construction.HW2.model.Dish;
import org.hse.software.construction.HW2.model.Order;
import org.hse.software.construction.HW2.repository.OrderMemento;
import org.hse.software.construction.HW2.view.ConsoleView;
import org.hse.software.construction.HW2.model.OrderStatus;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw2Application {

	public static void main(String[] args) {

		ConsoleView registrationView = new ConsoleView();

		Order order = new Order(1, OrderStatus.NEW);
		order.addDish(Dish.builder()
				.name("Burger")
				.price(100)
				.timeToCook(10)
				.preparationDifficulty(1)
				.availableQuantity(10)
				.build());
		order.addDish(Dish.builder()
				.name("Pizza")
				.price(200)
				.timeToCook(20)
				.preparationDifficulty(2)
				.availableQuantity(5)
				.build());
		order.addDish(Dish.builder()
				.name("Pizza")
				.price(200)
				.timeToCook(20)
				.preparationDifficulty(2)
				.availableQuantity(5)
				.build());

		registrationView.showOrderItems();

//		OrderMemento memento = new OrderMemento(order,);
//		memento.saveToJson();
//
//		Order restoredOrder = memento.restoreFromJson();
//
//		if (restoredOrder != null) {
//			registrationView.showOrderItems();
//		}
	}
}
