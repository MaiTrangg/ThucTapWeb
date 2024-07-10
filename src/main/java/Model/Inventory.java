package Model;

import java.sql.Timestamp;

public class Inventory {
    private int inventoryId;
    private Product product;
    private int quantity;
    private Timestamp lastUpdated;

    public Inventory(int inventoryId, Product product, int quantity, Timestamp lastUpdated) {
        this.inventoryId = inventoryId;
        this.product = product;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;// Ngày giờ cập nhật lần cuối.
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
