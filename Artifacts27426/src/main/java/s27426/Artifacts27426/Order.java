package s27426.Artifacts27426;

import java.util.Map;

public class Order {
    private static int lastOrderId = 0;
    private int orderId;
    private User User;
    private Map<Product, Integer> products;
    private String deliveryAddress;
    private OrderStatus status;

    public Order(User user, Map<Product, Integer> products, String deliveryAddress) {
        this.orderId = ++lastOrderId;
        this.User = user;
        this.products = products;
        this.deliveryAddress = deliveryAddress;
        this.status = OrderStatus.NOWE;
    }
    public int getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Map<Product, Integer> getProducts() {
        return  products;
    }
}
