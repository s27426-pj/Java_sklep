package s27426.Store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

//@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ProductStorage productStorage = ProductStorage.getInstance();
		productStorage.addProduct("Cukierki",4.20);
		productStorage.addProduct("Marchew",3.50);
//		System.out.println(productStorage.getProduct("Marchew"));
//		System.out.println(productStorage.getProducts());

		Basket basket = Basket.getInstance();
		basket.addTOBasket(productStorage.getProduct("Cukierki"),4);
		basket.addTOBasket(productStorage.getProduct("Marchew"),4);
//		System.out.println(basket.getBasket());

		OrderStorage orderStorage = OrderStorage.getInstance();
		User user = new User(1);
		Order order = new Order(user, basket.getBasket(), "abc");
		orderStorage.addOrder(order);
//		System.out.println(orderStorage.getAllOrders());
//		System.out.println(orderStorage.getOrderForID(1));

		OrderService orderService = new OrderService();
		System.out.println(orderService.makeOrder(user,basket.getBasket(),"abc") + " MAKE ");
		System.out.println(orderService.checkOrderStatus(2));
		System.out.println(orderService.cancelOrder(2));
//		System.out.println(orderService.cancelOrder(2));

		orderService.orderStorage.getOrderForID(2).setStatus(OrderStatus.DOSTARCZONE);
		System.out.println(orderService.getDeliveryStatus(2));
		Product product = new Product("Ziemniaki",3.5);
		basket.addTOBasket(product,2);
		orderService.makeOrder(user,basket.getBasket(),"cab");
//		SpringApplication.run(StoreApplication.class, args);
	}

}
