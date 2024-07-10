package Model;

public class OrderDetail {
	private int orderDetailId;

	private Product product;
	private int quantity;
	private double price;


	/**
	 * @param product
	 * @param quantity
	 * @param price
	 */
	public OrderDetail(Product product, int quantity, double price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderDetail(int orderDetailId, Product product, int quantity, double price) {
		this.orderDetailId = orderDetailId;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}



	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}



	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}



	public boolean existProduct(OrderDetail ol) {
		if(ol.product.getProductId()==this.product.getProductId()) return true;
		return false;
	}

//	public void updateAvailable() {
//		product.setAvailable(product.getAvailable()-this.quantity);
//	}


	@Override
	public String toString() {
		return "/nOrderDetail{" +
				"product=" + product.getName() +
				", quantity=" + quantity +
				", price=" + price +
				'}';
	}
}

