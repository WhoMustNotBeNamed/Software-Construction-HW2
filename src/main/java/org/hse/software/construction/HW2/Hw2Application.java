package org.hse.software.construction.HW2;

import org.hse.software.construction.HW2.controller.Controller;
// TODO: убрать, когда буду уверен, что репозитории работают
import org.hse.software.construction.HW2.model.*;
import org.hse.software.construction.HW2.repository.MenuRepository;
import org.hse.software.construction.HW2.repository.AccountRepository;
import org.hse.software.construction.HW2.repository.MoneyStorageRepository;
import org.hse.software.construction.HW2.repository.OrderRepository;
import org.hse.software.construction.HW2.view.ConsoleView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw2Application {
	public static void main(String[] args) {

		Controller controller = new Controller();
	}
}
