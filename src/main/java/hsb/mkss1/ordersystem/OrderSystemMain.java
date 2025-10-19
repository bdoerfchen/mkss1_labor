package hsb.mkss1.ordersystem;

import hsb.mkss1.ordersystem.service.OrderService;

public class OrderSystemMain {
	public static void main(String[] args) {
		OrderService orderService = new OrderService();
		orderService.menuloop();
	}
}
