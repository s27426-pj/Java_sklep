package s27426.Artifacts27426;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class OrderStorage {
    private Map<Integer, Order> orders = new HashMap<>();
    public Map<Integer, Order> getOrders() {
        return orders;
    }
    public void put(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order get(int OrderId){
            return orders.get(OrderId);
    }
}
