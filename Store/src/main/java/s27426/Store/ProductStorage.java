package s27426.Store;

import java.util.HashMap;
import java.util.Map;

public class ProductStorage {
    private static ProductStorage instance;
    public ProductStorage(){
    }

    public static ProductStorage getInstance() {
        if (instance == null){
            instance = new ProductStorage();
        }
        return instance;
    }
    Map<String, Product> products = new HashMap<>();
    public void addProduct(String name, double price) {
        Product product = new Product(name,price);
        products.put(name,product);
    }
    public Product getProduct(String name){
        return products.get(name);
    }
    public Map<String, Product> getProducts() {
        return products;
    }
    public boolean isProductAvailable(String productName) {
        if (products.containsKey(productName)) {
            return true;
        } else {
            throw new RuntimeException("Product not found: " + productName);
        }
    }
}
