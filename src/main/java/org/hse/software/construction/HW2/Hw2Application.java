package org.hse.software.construction.HW2;

import org.hse.software.construction.HW2.controller.Controller;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw2Application {
	public static void main(String[] args) {
		// Создание контроллера и запуск приложения
		Controller controller = new Controller();
	}
}
