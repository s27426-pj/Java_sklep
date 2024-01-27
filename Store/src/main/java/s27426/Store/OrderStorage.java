package s27426.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderStorage {
    private static OrderStorage instance;
    public OrderStorage(){
    }

    public static OrderStorage getInstance() {
        if (instance == null){
            instance = new OrderStorage();
        }
        return instance;
    }

    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return orders;
    }
    public Order getOrderForID(int id) {
        return orders.stream()
                .filter(order -> order.getOrderId() == id)
                .findFirst()
                .orElse(null); // May return null if not found; handle accordingly
    }
    public OrderStatus getOrderStatus(int id) {
        return orders.stream()
                .filter(order -> order.getOrderId() == id)
                .map(Order::getStatus)
                .findFirst()
                .orElse(null); // May return null if not found; handle accordingly
    }

    public Map<Product, Integer> getOrderBasket(int id) {
        return orders.stream()
                .filter(order -> order.getOrderId() == id)
                .map(Order::getProducts)
                .findFirst()
                .orElse(null); // May return null if not found; handle accordingly
    }

}
