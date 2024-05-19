//package Controller;
//
//import java.io.IOException;
//import java.sql.Date;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import DAO.CustomerDao;
//import DAO.OrderDao;
//import DAO.OrderDetailDAO;
//import DAO.ProductDAO;
//import Model.Order;
//import Model.OrderDetail;
//import Model.OrderLine;
//
///**
// * Servlet implementation class checkoutServlet
// */
//@WebServlet("/checkoutServlet")
//public class checkoutServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public checkoutServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		session.removeAttribute("errorFromCheckoutServlet");
//		String country = request.getParameter("selectedCountry");
//		System.out.println(country+" country");
//		String firstname = request.getParameter("c_fname");
//		String lastname = request.getParameter("c_lname");
//		String phone = request.getParameter("c_phone");
//		String address = request.getParameter("c_address");
//		//kiểm tra xem khách hàng đã điền đầy đủ thông tin chưa
//		if(country.equals("1")||this.isNullOrEmpty(firstname)|| this.isNullOrEmpty(lastname)|| this.isNullOrEmpty(phone)|| this.isNullOrEmpty(address)) {
//			String errorFromCheckoutServlet = "Please, enter to all (*) !";
//			session.setAttribute("errorFromCheckoutServlet", errorFromCheckoutServlet);
//			System.out.println("chưa nhập thông tin");
//			request.getRequestDispatcher("/checkout.jsp").forward(request, response);
//		}else {
//		session.removeAttribute("errorFromCheckoutServlet");
//		java.util.Date utilDate = new java.util.Date();
//		Date dateorder = new Date(utilDate.getTime());
//
//		// lay ra order từ session
//
//		Order o = (Order) session.getAttribute("order");
//		System.out.println(o +" day la order");
//		int order_id = OrderDao.insertOrder(o.getCustomer().getUsername(), o.getCustomer().getEmail(), dateorder);
////		int order_id = 0;
//		System.out.println("order_id là: "+order_id);
//		// update infor of customer
//				CustomerDao.GetInstance().updateCheckout(firstname, lastname, country, phone, address,o.getCustomer().getUsername(),o.getCustomer().getEmail());
//
//
//		for (OrderDetail ol : o.getOrderLines()) {
//			//lưu các orderline của khách đặt xuống csdl
//			OrderDetailDAO.insertOrderLine(order_id, ol.getProduct().getProductId(), ol.getQuantity(), ol.getPrice());
//			//update lại số lượng còn lại trong csdl sau khi đặt
//			ol.updateAvailable();
////			ProductDAO.updateProduct(ol.getProduct().getProductId(), ol.getProduct().getName(), ol.getProduct().getImg(), ol.getProduct().getPrice(), ol.getProduct().getAvailable());
//		}
//		session.removeAttribute("order");
//		request.getRequestDispatcher("/thankyou.jsp").forward(request, response);
//		}
//	}
//	//kiểm tra xem người dùng đã điền thông tin hay chưa
//	public  boolean isNullOrEmpty(String str) {
//	    return str == null || str.trim().isEmpty();
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
