package Model;

import DAO.ProductDAO;

public class Product {
	private int productId;
	private String img;
	private String name;
	private String descriptionP;
	private double originalPrice;
	private double sellingPrice;
//	private int available;
	private Category category;

	public Product(int productId, String img, String name, String descriptionP, double originalPrice, double sellingPrice, Category category) {
		this.productId = productId;
		this.img = img;
		this.name = name;
		this.descriptionP = descriptionP;
		this.originalPrice = originalPrice;
		this.sellingPrice = sellingPrice;
//		this.available = available;
		this.category = category;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public void setImg(String img) {
		this.img = img;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescriptionP(String descriptionP) {
		this.descriptionP = descriptionP;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	//	public int getAvailable() {
//		return available;
//	}

//	public void setAvailable(int available) {
//		this.available = available;
//	}
//
//	@Override
//	public String toString() {
//		return "Product{" +
//				"productId=" + productId +
//				", img='" + img + '\'' +
//				", name='" + name + '\'' +
//				", descriptionP='" + descriptionP + '\'' +
//				", originalPrice=" + originalPrice +
//				", sellingPrice=" + sellingPrice +
//				", available=" + available +
//				", category=" + category +
//				'}';
//	}

	public Category getCategory() {
		return category;
	}


	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", img='" + img + '\'' +
				", name='" + name + '\'' +
				", descriptionP='" + descriptionP + '\'' +
				", originalPrice=" + originalPrice +
				", sellingPrice=" + sellingPrice +
//				", available=" + available +
				", category=" + category +
				'}';
	}

//	public void setAvailable(int available) {
//		this.available = available;
//	}
public static void main(String[] args) {

}
}