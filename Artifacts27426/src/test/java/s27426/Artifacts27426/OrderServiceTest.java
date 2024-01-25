package s27426.Artifacts27426;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {
    @MockBean
    private OrderStorage orderStorage;
    @MockBean
    private ProductStorage productStorage;
    @Autowired
    private OrderService orderService;

    @Test
    public void ShouldCreateNewOrder(){
        when(productStorage.getProduct("ExampleProduct")).thenReturn(new Product("ExampleProduct", 10.0));

        // Dane testowe
        int clientId = 1;
        Map<String, Integer> productQuantities = new HashMap<>();
        productQuantities.put("ExampleProduct", 2);
        String deliveryAddress = "Example Address";

        // Wywołanie metody placeOrder
        orderService.placeOrder(clientId, productQuantities, deliveryAddress);
        verify(orderStorage).save(argThat(order -> order.getProducts().size() == 1));
        // Sprawdzenie, czy zamówienie zostało dodane do OrderStorage
        assertThat(orderStorage.get(clientId)).isNotNull();

        // Sprawdzenie, czy status zamówienia jest NOWE
//        assertThat(orderService.checkOrderStatus(clientId)).isEqualTo(OrderStatus.NOWE);
    }
}