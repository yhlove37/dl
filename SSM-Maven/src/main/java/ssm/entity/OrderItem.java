package ssm.entity;

import java.io.Serializable;

public class OrderItem implements Serializable {

    
    private String itemId;
    private int count;
    private double subtotal;
    private Product product;
    private Order order;

    public OrderItem() {
    }

    public OrderItem(String itemId, int count, double subtotal, Product product, Order order) {
        this.itemId = itemId;
        this.count = count;
        this.subtotal = subtotal;
        this.product = product;
        this.order = order;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
