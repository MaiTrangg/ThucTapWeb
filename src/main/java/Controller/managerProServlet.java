package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProductDAO;
import Model.Product;

/**
 * Servlet implementation class managerProServerlet
 */
@WebServlet("/managerProServerlet")
public class managerProServlet extends HttpServlet {
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("đã vào managerProServerlet");
		HttpSession session = request.getSession();
		List<Product> listNew = ProductDAO.getAllProduct();
		session.removeAttribute("listP");
		session.setAttribute("listP", listNew);
		request.getRequestDispatcher("WEB-INF/ManagerProduct.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
