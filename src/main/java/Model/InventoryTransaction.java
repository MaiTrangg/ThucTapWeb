package Model;

import java.sql.Timestamp;

public class InventoryTransaction {
    private  int inventoryTransaction_id;
    private Product product;
    private String type;
    private int quantity;
    private Timestamp transactionDate;

    public InventoryTransaction(int inventoryTransaction_id, Product product, String type, int quantity, Timestamp transactionDate) {
        this.inventoryTransaction_id = inventoryTransaction_id;
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }

    public int getInventoryTransaction_id() {
        return inventoryTransaction_id;
    }

    public void setInventoryTransaction_id(int inventoryTransaction_id) {
        this.inventoryTransaction_id = inventoryTransaction_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
