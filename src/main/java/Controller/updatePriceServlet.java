package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Order;

/**
 * Servlet implementation class updatePriceServlet
 */
@WebServlet("/updatePriceServlet")
public class updatePriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePriceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("haha");
		System.out.println(request.getQueryString()+" dữ liệu được gửi đi");
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String i =request.getParameter("idpro");
		int idpro = Integer.parseInt(i);
		int quantity = Integer.parseInt(request.getParameter("quantity"));
//		System.out.println("quantity là: "+quantity);
		Order o = (Order) session.getAttribute("order");
		o.updatePrice(idpro, quantity);
		session.setAttribute("order", o);
		request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
//		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
