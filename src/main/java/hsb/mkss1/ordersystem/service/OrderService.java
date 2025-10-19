package hsb.mkss1.ordersystem.service;

import hsb.mkss1.ordersystem.model.Product;
import hsb.mkss1.ordersystem.model.Service;
import hsb.mkss1.ordersystem.util.Input;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

public class OrderService {

    private static final int MAX_PRODUCT_COUNT = 5;
    private static final int MAX_SERVICES_COUNT = 5;

	private Product[] products = new Product[MAX_PRODUCT_COUNT];
	private Service[] services = new Service[MAX_SERVICES_COUNT];

	int productIndex = 0;
	int serviceIndex = 0;

	public void runMenuLoop() {
        while (true) {
            int input;
            do {
                printMenu();
                input = Input.readInt();
                switch (input) {
                    case 0:
                        break;
                    case 1:
                        orderProduct(productIndex++);
                        break;
                    case 2:
                        orderService(serviceIndex++);
                        break;
                    default:
                        IO.println("invalid");
                        break;
                }
            } while (input != 0 && (productIndex < MAX_PRODUCT_COUNT) && serviceIndex < MAX_SERVICES_COUNT);
            sortProducts();
            sortServices();
            finishOrder();
            clearProducts();
            clearServices();
        }
	}

    private void clearProducts() {
        products = new Product[MAX_PRODUCT_COUNT];
        productIndex = 0;
    }

    private void clearServices() {
        services = new Service[MAX_SERVICES_COUNT];
        serviceIndex = 0;
    }

    private void printMenu() {
		IO.println("Your choice?");
		IO.println("(0) Finish order");
		IO.println("(1) Order product");
		IO.println("(2) Order service");
	}
	
	private void sortProducts() {
		for (int i = 0; i< products.length-1; i++) {
			for (int j = 0; j< products.length-1-i; j++) {
				if ( products[j+1] != null && products[j+1].getPrice()< products[j].getPrice()) {
					Product temp = products[j + 1] ;
					products[j+1] = products[ j ] ;
					products[j] = temp ;
				}
			}
		}
	}

	private void sortServices() {
		for (int i = 0; i< services.length-1; i++) {
			for (int j = 0; j< services.length-1-i; j++) {
				if ( services[j+1] != null && services[j+1].getPrice()< services[j].getPrice()) {
					Service temp = services[j + 1] ;
					services[j+1] = services[ j ] ;
					services[j] = temp ;
				}
			}
		}
	}
	private void orderProduct(int index) {
		IO.println("Name: ");
		String l = Input.readString();
		IO.println("Unit price (in cents): ");
		int p = Input.readInt();
		IO.println("Quantity: ");
		int s = Input.readInt();
		products[index] = new Product(l, p, s) ;
	}
	
	private void orderService(int index) {
		IO.println("hsb.mkss1.ordersystem.model.Service type: ");
		String l = Input.readString();
		IO.println("Number of persons: ");
		int p = Input.readInt();
		IO.println("Hours: ");
		int s = Input.readInt();
		services[index] = new Service(l, p, s) ;
	}
	
	private void finishOrder() {
		int sum = 0;
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				IO.println(products[i] + " = " + StringFormatterUtil.formatPrice(products[i].getPrice()));
				sum += products[i].getPrice();
			}
		}
		for (int i = 0; i < services.length; i++) {
			if (services[i] != null) {
				IO.println(services[i]);
                IO.println(" = " + StringFormatterUtil.formatPrice(services[i].getPrice()));
				sum += services[i].getPrice();
			}
		}
		IO.println("Sum: "+ StringFormatterUtil.formatPrice(sum));
	}
}
