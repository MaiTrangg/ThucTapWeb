package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.*;
import Model.*;

/**
 * Servlet implementation class checkoutServlet
 */
@WebServlet("/checkoutServlet")
public class checkoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public checkoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		HttpSession session = request.getSession();
//		session.removeAttribute("errorFromCheckoutServlet");
//
//		String fullName = request.getParameter("fullName");
//		String tinh = request.getParameter("tinh");
//		String quan = request.getParameter("quan");
//		String phuong = request.getParameter("phuong");
//		System.out.println("tinh: "+tinh);
//		System.out.println("quan: "+quan);
//		System.out.println("phuong: "+phuong);
//
//		String noteAddress = request.getParameter("noteAddress");
//		String numberPhone = request.getParameter("numberPhone");
//		String email = request.getParameter("email");
//		String noteOrder = request.getParameter("noteOrder");
//		//kiểm tra xem khách hàng đã điền đầy đủ thông tin chưa
//		if(tinh.equals("-1")||quan.equals("-1")|| phuong.equals("-1")) {
//			String errorFromCheckoutServlet = "Please, select address !";
//			session.setAttribute("errorFromCheckoutServlet", errorFromCheckoutServlet);
////			System.out.println("chưa nhập thông tin");
//			request.getRequestDispatcher("/checkout.jsp").forward(request, response);
//		}else {
//			session.removeAttribute("errorFromCheckoutServlet");
//			java.util.Date utilDate = new java.util.Date();
//			Date dateorder = new Date(utilDate.getTime());
//
//			// lay ra order từ session
//
//			Order o = (Order) session.getAttribute("order");
//			System.out.println(o +" day la order");
//			//lấy ra id đơn hàng vừa được lưu
//			int order_id = OrderDao.insertOrder(o.getTotalMoney(),dateorder,"Chờ xác nhận",noteOrder);
//			System.out.println("order_id là: "+order_id);
//			// update infor of customer
////				CustomerDao.GetInstance().updateCheckout(firstname, lastname, country, phone, address,o.getCustomer().getUsername(),o.getCustomer().getEmail());
//			//get customer current
//			Customer cus = (Customer) session.getAttribute("customer");
//			//lưu thông tin giao dịch
//			TransactionDAO.insertTransaction(order_id, cus.getUser_id(),new Transaction(fullName,email,numberPhone,o.getTotalMoney()));
//
//			//lưu địa chỉ giao hàng
//			ShippingAddressDAO.insertShippingAddress(order_id, new ShippingAddress(0,tinh,quan,phuong,"Việt Nam",noteAddress));
//			for (OrderDetail ol : o.getOrderLines()) {
//				//lưu các orderline của khách đặt xuống csdl
//				OrderDetailDAO.insertOrderLine(order_id, ol.getProduct().getProductId(), ol.getQuantity(), ol.getPrice());
//				//update lại số lượng còn lại trong csdl sau khi đặt
//				ol.updateAvailable();
////			ProductDAO.updateProduct(ol.getProduct().getProductId(), ol.getProduct().getName(), ol.getProduct().getImg(), ol.getProduct().getPrice(), ol.getProduct().getAvailable());
//			}
//			session.removeAttribute("order");
//			request.getRequestDispatcher("/WEB-INF/thankyou.jsp").forward(request, response);
//		}
//	}
//	//kiểm tra xem người dùng đã điền thông tin hay chưa
//	public  boolean isNullOrEmpty(String str) {
//		return str == null || str.trim().isEmpty();
//	}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		session.removeAttribute("errorFromCheckoutServlet");

		String fullName = request.getParameter("fullName");
		String tinh = request.getParameter("tinh");
		String quan = request.getParameter("quan");
		String phuong = request.getParameter("phuong");
		String noteAddress = request.getParameter("noteAddress");
		String numberPhone = request.getParameter("numberPhone");
		String email = request.getParameter("email");
		String noteOrder = request.getParameter("noteOrder");

		if (tinh.equals("-1") || quan.equals("-1") || phuong.equals("-1")) {
			String errorFromCheckoutServlet = "Please, select address!";
			session.setAttribute("errorFromCheckoutServlet", errorFromCheckoutServlet);
			request.getRequestDispatcher("/checkout.jsp").forward(request, response);
		}else {
		session.removeAttribute("errorFromCheckoutServlet");
		java.util.Date utilDate = new java.util.Date();
//		Date dateorder = new Date(utilDate.getTime());
		Timestamp dateorder = new Timestamp(System.currentTimeMillis());
		// lay ra order từ session

		Order o = (Order) session.getAttribute("order");
		System.out.println(o +" day la order");
		//lấy ra id đơn hàng vừa được lưu
		int order_id = OrderDao.insertOrder(o.getTotalMoney(),dateorder,"Chờ xác nhận",noteOrder);
		o.setOrderId(order_id);
		o.setNoteOrder(noteOrder);
		System.out.println("order_id là: "+order_id);
		// update infor of customer
//				CustomerDao.GetInstance().updateCheckout(firstname, lastname, country, phone, address,o.getCustomer().getUsername(),o.getCustomer().getEmail());
		//get customer current
		Customer cus = (Customer) session.getAttribute("customer");
		//lưu thông tin giao dịch
			TransactionDAO.insertTransaction(order_id, cus.getUser_id(),new Transaction(fullName,email,numberPhone,o.getTotalMoney()));

		//lưu địa chỉ giao hàng
			ShippingAddressDAO.insertShippingAddress(order_id, new ShippingAddress(0,tinh,quan,phuong,"Việt Nam",noteAddress));
		for (OrderDetail ol : o.getOrderLines()) {
			//lưu các orderline của khách đặt xuống csdl
			OrderDetailDAO.insertOrderLine(order_id, ol.getProduct().getProductId(), ol.getQuantity(), ol.getPrice());
			//update lại số lượng còn lại trong csdl sau khi đặt
			ol.updateAvailable();
//			ProductDAO.updateProduct(ol.getProduct().getProductId(), ol.getProduct().getName(), ol.getProduct().getImg(), ol.getProduct().getPrice(), ol.getProduct().getAvailable());
		}
			//ghi log
			ILog log = new LogDao();

			log.info("Chưa hoàn tất đơn hàng","Đặt hàng thành công Đơn hàng: "+o.printOrder(),cus.getUsername(),request.getSession().getId(),request.getRemoteAddr());


			session.removeAttribute("order");
			request.getRequestDispatcher("/WEB-INF/thankyou.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}