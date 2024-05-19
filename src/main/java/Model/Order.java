package Model;




import java.util.Date;
import java.util.List;

public class Order {
	private int orderId;
	private Transaction transaction;
	private ShippingAddress shippingAddress;
	private Date dateOrder;
	private List<OrderDetail> orderDetails ;
	private double totalMoney ;
	private String statusOrder;
	private String noteOrder;
	/**
	 * @return the dateOrder
	 */
	public Date getDateOrder() {
		return dateOrder;
	}
	/**
	 * @param dateOrder the dateOrder to set
	 */
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Order(int orderId, Transaction transaction, ShippingAddress shippingAddress, Date dateOrder, List<OrderDetail> orderDetails, double totalMoney, String statusOrder, String noteOrder) {
		this.orderId = orderId;
		this.transaction = transaction;
		this.shippingAddress = shippingAddress;
		this.dateOrder = dateOrder;
		this.orderDetails = orderDetails;
		this.totalMoney = totalMoney;
		this.statusOrder = statusOrder;
		this.noteOrder = noteOrder;
	}
	//	public Order(int orderId, Customer customer, List<OrderDetail> orderDetails) {
//		this.orderId = orderId;
//		this.customer = customer;
//		this.orderDetails = orderDetails;
//
//	}


//	/**
//	 * @param orderId
//	 * @param customer
//	 * @param dateOrder
//	 * @param orderDetails
//	 */
//	public Order(int orderId, Customer customer, Date dateOrder, List<OrderDetail> orderDetails, double totalMoney) {
//		this.orderId = orderId;
//		this.customer = customer;
//		this.dateOrder = dateOrder;
//		this.orderDetails = orderDetails;
//		this.totalMoney = totalMoney;
//	}
//	@Override
//	public String toString() {
//		return "Order [orderId=" + orderId  + ", customer="  + customer.toString() + "]";
////		 + ", customer=" + customer.toString() + ", orderLines=" + orderLines +
//	}
	public Order() {

	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the customer
	 */
//	public Customer getCustomer() {
//		return customer;
//	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * @param customer the customer to set
	 */
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
	/**
	 * @return the orderLines
	 */
	public List<OrderDetail> getOrderLines() {
		return orderDetails;
	}
	/**
	 * @param orderLines the orderLines to set
	 */

	public void setOrderLines(List<OrderDetail> orderLines) {
		this.orderDetails = orderLines;
	}
	/**
	 * @param orderDetails
	 */
	public Order(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public void addOrderline(OrderDetail ol) {
		boolean existed =false ;
		for (OrderDetail orderLine : orderDetails) {
			if(existed=orderLine.existProduct(ol)) {
				orderLine.setQuantity(orderLine.getQuantity()+1);
				orderLine.setPrice(orderLine.getQuantity()*orderLine.getProduct().getSellingPrice());
				break;
			}
		}
		if(!existed) orderDetails.add(ol);
	}

	public void removeOrderline(OrderDetail ol) {
		for (OrderDetail orderLine : orderDetails) {
			if(orderLine.existProduct(ol)) orderDetails.remove(orderLine);
			break;
		}
		orderDetails.remove(ol);
	}

	public OrderDetail getOrderLinebyIDPro(int idpro) {
		for (OrderDetail orderLine : orderDetails) {
			if(orderLine.getProduct().getProductId()==idpro) {
				return orderLine;
			}
		}
		return null;
	}

	public void updatePrice(int idpro, int quantity) {
		OrderDetail ol= getOrderLinebyIDPro(idpro);
		if(quantity==0) {
			System.out.println("huhu");
			removeOrderline(ol);
			return;
		}

		ol.setQuantity(quantity);
//		total();
		ol.setPrice(ol.getProduct().getSellingPrice()*ol.getQuantity());

	}

	public double total() {
		double total =0;
		for (OrderDetail orderLine : orderDetails) {
			total+=orderLine.getPrice();
		}
		setTotalMoney(total);
		System.out.println("tinh tong thanh cong: "+total);
		return total;
	}




}
