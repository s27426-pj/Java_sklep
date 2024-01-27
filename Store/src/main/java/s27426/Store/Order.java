package s27426.Store;

import java.util.Map;

public class Order {
    private static int lastOrderId = 0;
    private int orderId;
    private User user;
    private Map<Product,Integer> products;
    private String deliveryAddress;
    private OrderStatus status;
    public Order (User user, Map<Product,Integer> products,String deliveryAddress){
    this.orderId = ++lastOrderId;
    this.user = user;
    this.products = products;
    this.deliveryAddress = deliveryAddress;
    this.status = OrderStatus.NOWE;
    }

    public int getOrderId() {
        return orderId;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public User getUser() {
        return user;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", products=" + products +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", status=" + status +
                '}';
    }
}
