package s27426.Artifacts27426;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ProductStorage {
    private Map<String, Product> products = new HashMap<>();

    public void addProduct(String name, double price) {
        Product product = new Product(name, price);
        products.put(name, product);
    }

    public Product getProduct(String name) {
        return products.get(name);
    }


}
