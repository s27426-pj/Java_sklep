package s27426.Store;

import java.util.Map;
import java.util.Optional;

public class OrderService {
    ProductStorage productStorage = ProductStorage.getInstance();
    OrderStorage orderStorage = OrderStorage.getInstance();

    public Order makeOrder(User user, Map<Product,Integer> products, String deliveryAddress){
        Order order = new Order(user,products,deliveryAddress);
        try {
            if (areProductsAvailable(order.getProducts())) {
                orderStorage.addOrder(order);
                return order;
            } else {
                throw new RuntimeException("One or more products are not available.");
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }
    private boolean areProductsAvailable(Map<Product, Integer> orderProducts) {
        return orderProducts.keySet().stream()
                .allMatch(product -> productStorage.isProductAvailable(product.getName()));
    }

    public String checkOrderStatus(int orderId){
        String status;
        status = orderStorage.getOrderStatus(orderId).toString();
        status += orderStorage.getOrderBasket(orderId).toString();
        return status;
    }
    public String cancelOrder(int orderId) {
        Order order = orderStorage.getOrderForID(orderId);
        if (order != null) {
            if (order.getStatus() == OrderStatus.NOWE) {
                order.setStatus(OrderStatus.ANULOWANE);
                return "Order canceled successfully.";
            } else {
                return "Unable to cancel. Order is not in NEW status.";
            }
        } else {
            return "Order not found. Unable to cancel.";
        }
    }
    public String getDeliveryStatus(int orderId) {
        Order order = orderStorage.getOrderForID(orderId);
        if (order != null) {
            if (order.getStatus() == OrderStatus.DOSTARCZONE) {
                return "Order has been delivered.";
            } else {
                return "Order is not delivered yet.";
            }
        } else {
            return "Order not found.";
        }
    }


}
