package Model;




import java.util.Date;
import java.util.List;

public class Order {
	private int orderId;
	private Customer customer;
	private Date dateOrder;
	private List<OrderLine> orderLines ;
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

	/**
	 * @param orderId
	 * @param customer
	 * @param orderLines
	 */
	public Order(int orderId, Customer customer, List<OrderLine> orderLines) {
		this.orderId = orderId;
		this.customer = customer;
		this.orderLines = orderLines;

	}


	/**
	 * @param orderId
	 * @param customer
	 * @param dateOrder
	 * @param orderLines
	 */
	public Order(int orderId, Customer customer, Date dateOrder, List<OrderLine> orderLines) {
		this.orderId = orderId;
		this.customer = customer;
		this.dateOrder = dateOrder;
		this.orderLines = orderLines;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId  + ", customer="  + customer.toString() + "]";
//		 + ", customer=" + customer.toString() + ", orderLines=" + orderLines +
	}
	public Order() {

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
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the orderLines
	 */
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	/**
	 * @param orderLines the orderLines to set
	 */
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	/**
	 * @param orderLines
	 */
	public Order(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	public void addOrderline(OrderLine ol) {
		boolean existed =false ;
		for (OrderLine orderLine : orderLines) {
			if(existed=orderLine.existProduct(ol)) {
				orderLine.setQuantity(orderLine.getQuantity()+1);
				orderLine.setPrice(orderLine.getQuantity()*orderLine.getProduct().getSellingPrice());
				break;
			}
		}
		if(!existed) orderLines.add(ol);
	}

	public void removeOrderline(OrderLine ol) {
		/*for (OrderLine orderLine : orderLines) {
			if(orderLine.existProduct(ol)) orderLines.remove(orderLine);
			break;
		}*/
		orderLines.remove(ol);
	}

	public OrderLine getOrderLinebyIDPro(int idpro) {
		for (OrderLine orderLine : orderLines) {
			if(orderLine.getProduct().getProductId()==idpro) {
				return orderLine;
			}
		}
		return null;
	}

	public void updatePrice(int idpro, int quantity) {
		OrderLine ol= getOrderLinebyIDPro(idpro);
		if(quantity==0) {
			System.out.println("huhu");
			removeOrderline(ol);
			return;
		}

		ol.setQuantity(quantity);
		ol.setPrice(ol.getProduct().getSellingPrice()*ol.getQuantity());

	}

	public double total() {
		double total =0;
		for (OrderLine orderLine : orderLines) {
			total+=orderLine.getPrice();
		}
		return total;
	}




}
