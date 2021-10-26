package ssm.vo;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> cartItems = new HashMap<>();
    
    private double total;

    public Map<Integer, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Integer, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
