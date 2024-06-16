package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDao;
import Model.Customer;

/**
 * Servlet implementation class ad_addCusServlet
 */
@WebServlet("/ad_addCusServlet")
public class ad_addCusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ad_addCusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		int isAdmin = Integer.parseInt(request.getParameter("isadmin"));
		String email = request.getParameter("email");
		String numberphone = request.getParameter("numberphone");
		Customer c = new Customer(username, pass, email, numberphone, isAdmin);
		CustomerDao.GetInstance().ad_insertUser(c);
		session.removeAttribute("listCus");
		response.sendRedirect("managerCusServlet");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
