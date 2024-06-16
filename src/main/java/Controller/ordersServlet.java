package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OrderDao;
import Model.Order;

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
		HttpSession session = request.getSession();
		List<Order> orders = OrderDao.getAllOrders();
//		if(orders == null) System.out.println("orders null"); else
			System.out.println("đã vào ordersServlet "+orders);
		session.setAttribute("orders", orders);
		//response.sendRedirect("/WEB-INF/Orders.jsp");
		request.getRequestDispatcher("WEB-INF/Orders.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		String orderIdStr = request.getParameter("orderId");
		String statusOrder = request.getParameter("statusOrder");
		String ajax = request.getParameter("ajax");

		if (orderIdStr != null && statusOrder != null) {
			int orderId = Integer.parseInt(orderIdStr);
			OrderDao.updateOrderStatus(orderId, statusOrder);
		}
	
		if ("true".equals(ajax)) {
			response.getWriter().write("{\"status\": \"success\"}");
		} else {
			doGet(request, response);
		}
	}

}
