package hsb.mkss1.ordersystem;

import hsb.mkss1.ordersystem.service.OrderService;

public class OrderSystemMain {
	static void main() {
		OrderService orderService = new OrderService();
		orderService.runMenuLoop();
	}
}
