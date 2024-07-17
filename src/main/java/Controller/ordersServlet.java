package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OrderDao;
import DAO.OrderDetailDAO;
import DAO.ShippingAddressDAO;
import Model.Order;
import Model.OrderDetail;
import Model.ShippingAddress;

/**
 * Servlet implementation class ordersServlet
 */
@WebServlet("/ordersServlet")
public class ordersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ordersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		if (action == null || action.equals("listOrders")) {
			// Lấy danh sách đơn hàng
//			HttpSession session = request.getSession();
			List<Order> orders = OrderDao.getAllOrders();
//			session.setAttribute("orders", orders);
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("WEB-INF/Orders.jsp").forward(request, response);
		} else if (action.equals("viewOrderDetails")) {
			// Lấy chi tiết đơn hàng
			String orderIdStr = request.getParameter("orderId");
			int orderId = Integer.parseInt(orderIdStr);
			List<OrderDetail> orderDetails = OrderDetailDAO.getOrderDetailsByOrderId(orderId);
			request.setAttribute("orderDetails", orderDetails);
//			HttpSession session = request.getSession();
			List<Order> orders = OrderDao.getAllOrders();
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("WEB-INF/Orders.jsp").forward(request, response);
		} else if (action.equals("getShippingAddress")) {
			String orderIdStr = request.getParameter("orderId");
			int orderId = Integer.parseInt(orderIdStr);
			ShippingAddress address = ShippingAddressDAO.getShippingAddressByOrderId(orderId);
			boolean isUrban = isUrbanProvince(address.getProvince());

			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("{\"status\": \"success\", \"isUrban\": " + isUrban + "}");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		String orderIdStr = request.getParameter("orderId");
		String statusOrder = request.getParameter("statusOrder");
		String ajax = request.getParameter("ajax");

		if (orderIdStr != null && statusOrder != null) {
			int orderId = Integer.parseInt(orderIdStr);
			OrderDao.updateOrderStatus(orderId, statusOrder);

			if (statusOrder.equals("Đang xử lý")) {
				// Chuyển từ "Đang xử lý" sang "Hoàn tất xác nhận" sau 2 phút
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						OrderDao.updateOrderStatus(orderId, "Hoàn tất xác nhận");
					}
				}, 2 * 60 * 1000); // 2 phút

			} else if (statusOrder.equals("Đang giao hàng")) {
				// Lấy địa chỉ giao hàng và kiểm tra khu vực
				ShippingAddress address = ShippingAddressDAO.getShippingAddressByOrderId(orderId);
				boolean isUrban = isUrbanProvince(address.getProvince());
				int delay = isUrban ? 1 * 60 * 1000 : 2 * 60 * 1000; // 1 phút cho nội thành, 2 phút cho ngoại thành

				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						OrderDao.updateOrderStatus(orderId, "Giao hàng thành công");
					}
				}, delay);
			}
		}

		if ("true".equals(ajax)) {
			response.getWriter().write("{\"status\": \"success\"}");
		} else {
			doGet(request, response);
		}
	}


	private boolean isUrbanProvince(String province) {
		// Danh sách các tỉnh nội tỉnh
		String[] urbanProvinces = {"Hà Nội", "Hồ Chí Minh", "Đà Nẵng"};
		for (String urban : urbanProvinces) {
			if (urban.equals(province)) {
				return true;
			}
		}
		return false;
	}


}
