package Model;

public class Product {
	private int productId;
	private String img;
	private String name;
	private String descriptionP;
	private double originalPrice;
	private double sellingPrice;
	private int available;
	private Category category;

	public Product(int productId, String img, String name, String descriptionP, double originalPrice, double sellingPrice, int available, Category category) {
		this.productId = productId;
		this.img = img;
		this.name = name;
		this.descriptionP = descriptionP;
		this.originalPrice = originalPrice;
		this.sellingPrice = sellingPrice;
		this.available = available;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public String getImg() {
		return img;
	}

	public String getName() {
		return name;
	}

	public String getDescriptionP() {
		return descriptionP;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public int getAvailable() {
		return available;
	}

	public Category getCategory() {
		return category;
	}

	
	

}

