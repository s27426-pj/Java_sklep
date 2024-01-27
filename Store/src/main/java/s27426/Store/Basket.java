package s27426.Store;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    private static Basket instance;
    public Basket(){
    }

    public static Basket getInstance() {
        if (instance == null){
            instance = new Basket();
        }
        return instance;
    }
    Map<Product, Integer> Basket = new HashMap<>();

    public void addTOBasket(Product product, Integer quantity) {
        Basket.put(product,quantity);
    }

    public Map<Product, Integer> getBasket() {
        return Basket;
    }

}
