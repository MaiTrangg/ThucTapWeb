package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CategoryDAO;
import DAO.InventoryDAO;
import DAO.ProductDAO;
import Model.Inventory;
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("đã vào managerProServerlet");
		HttpSession session = request.getSession();
		List<Inventory> listNew = InventoryDAO.getAllInventoryPro();

		session.removeAttribute("listP");
		session.setAttribute("listP", listNew);
		session.setAttribute("categoryList", new CategoryDAO().getListCategory());
		request.getRequestDispatcher("/WEB-INF/ManagerProduct.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
