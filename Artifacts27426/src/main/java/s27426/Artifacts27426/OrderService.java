package s27426.Artifacts27426;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class OrderService {
    private final OrderStorage orderStorage;
    private final ProductStorage productStorage;
    public OrderService(OrderStorage orderStorage, ProductStorage productStorage) {
        this.orderStorage = orderStorage;
        this.productStorage = productStorage;
    }

    public Integer placeOrder(int clientId, Map<String, Integer> productQuantities, String deliveryAddress) {
        Map<Product, Integer> products = new HashMap<>();

        for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
            String productName = entry.getKey();
            Integer quantity = entry.getValue();

            Product product = productStorage.getProduct(productName);
            if (product == null) {
                throw new IllegalArgumentException("Product not found: " + productName);
            }

            products.put(product, quantity);
        }

        Order newOrder = new Order(new User(clientId), products, deliveryAddress);
        orderStorage.put(newOrder);
        return newOrder.getOrderId();
    }

    public OrderStatus checkOrderStatus(int orderId) {
        Order checkedOrder = orderStorage.get(orderId);
        if (checkedOrder == null){
            return OrderStatus.valueOf("Order don`t exist");
        }
        else {
            return checkedOrder.getStatus();
        }
    }

    public String cancelOrder(int orderId) {
        Order order = orderStorage.get(orderId);
        if (order != null && order.getStatus() == OrderStatus.NOWE) {
            order.setStatus(OrderStatus.ANULOWANE);
            return "Order canceled successfully.";
        } else if (order != null && order.getStatus() != OrderStatus.NOWE) {
            return "Cannot cancel order in progress.";
        } else {
            return "Order with given ID not found.";
        }
    }

    public String confirmDelivery(int orderId) {
        Order order = orderStorage.get(orderId);
        if (order != null && order.getStatus() == OrderStatus.W_REALIZACJI) {
            order.setStatus(OrderStatus.DOSTARCZONE);
            return "Delivery confirmed successfully.";
        } else if (order != null && order.getStatus() != OrderStatus.W_REALIZACJI) {
            return "Cannot confirm delivery for order with status other than 'W_REALIZACJI'.";
        } else {
            return "Order with given ID not found.";
        }
    }

    public double calculateOrderTotal(int orderId) {
        Order order = orderStorage.get(orderId);
        if (order != null) {
            return calculateTotal(order.getProducts());
        } else {
            throw new IllegalArgumentException("Order with given ID not found.");
        }
    }

    private double calculateTotal(Map<Product, Integer> products) {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }
}
