package Model;

public class Category {
    private String category;
    private int quantity;

    public Category(String category, int quantity) {
        this.category = category;
        this.quantity = quantity;
    }


    public Category(String category) {
        this.category = category;
    }

    public Category(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

}

