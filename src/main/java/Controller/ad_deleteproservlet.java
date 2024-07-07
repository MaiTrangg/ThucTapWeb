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
 * Servlet implementation class ad_deleteproservlet
 */
@WebServlet("/ad_deleteproservlet")
public class ad_deleteproservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idpro = Integer.parseInt(request.getParameter("idpro"));
		ProductDAO.deleteProduct(idpro);
//		System.out.println("ad_deleteproservlet kkkkkkkkkkkkkkkkkkkkkkk");
//		System.out.println("idpro: "+idpro);

		response.sendRedirect("managerProServerlet");
//		request.getRequestDispatcher("managerProServerlet").forward(request,response);

//		request.getRequestDispatcher("managerProServerlet");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
