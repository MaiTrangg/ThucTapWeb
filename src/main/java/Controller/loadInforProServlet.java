package Controller;

import java.io.IOException;
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
 * Servlet implementation class loadInforProServlet
 */
@WebServlet("/loadInforProServlet")
public class loadInforProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadInforProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		int idpro = Integer.parseInt(request.getParameter("idpro"));
		Inventory inventory = InventoryDAO.getInventoryPro(idpro);
		session.setAttribute("inventory",inventory);
		session.setAttribute("idpro", idpro);
		session.setAttribute("categoryList", new CategoryDAO().getListCategory());
//		response.sendRedirect("ad_editproduct.jsp");
		request.getRequestDispatcher("/WEB-INF/ad_editproduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
