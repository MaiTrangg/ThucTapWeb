package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProductDAO;
import Model.OrderDetail;
import Model.Order;
import Model.Product;

/**
 * Servlet implementation class addToCartServlet
 */
@WebServlet("/addToCartServlet")
public class addToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("da vao servlet addtocart");
		HttpSession session = request.getSession();
		int idpro = Integer.parseInt(request.getParameter("idpro"));
		System.out.println("idpro: "+idpro);
		Product p = ProductDAO.getProductByID(idpro);
		OrderDetail ol = new OrderDetail(p, 1, p.getSellingPrice());
		
		Order o = (Order) session.getAttribute("order");
		if(o==null) {
//			newlistOrderLine.add(ol);
			Order onew  = new Order();
			List<OrderDetail> newlistOrderLine = new ArrayList<OrderDetail>();
			onew.setOrderLines(newlistOrderLine);
			onew.addOrderline(ol);
			o=onew;
			session.setAttribute("order", o);
		}else {
//			listOrderLine.add(ol);
//			o.setOrderLines(listOrderLine);
			o.addOrderline(ol);
			
			session.setAttribute("order", o);
		}
		session.setAttribute("countOrderLine", o.getOrderLines().size());
		System.out.println("-----------------------------");
		for (OrderDetail orderLine : o.getOrderLines()) {
			System.out.println(orderLine);
		}
		String value = request.getParameter("value");
		System.out.println("value"+value);
//		PrintWriter out = response.getWriter();
		if(value.equals("buy")) {
			System.out.println("da vao noi xu ly");
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
//			response.sendRedirect("/WEB-INF/cart.jsp");
		}else{
			request.getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
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
