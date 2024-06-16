package Controller;

import java.io.IOException;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDao;
import Model.Customer;

/**
 * Servlet implementation class ad_deleteCusServlet
 */
@WebServlet("/ad_deleteCusServlet")
public class ad_deleteCusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ad_deleteCusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("đã vào ad_deleteCus");
		HttpSession session = request.getSession();
		String username = request.getParameter("username_c");
		String email = request.getParameter("email_c");
		System.out.println(email+"------------"+username);
		session.removeAttribute("listCus");
		CustomerDao.GetInstance().delete(username, email);
//		List<Customer> newListCus= CustomerDao.GetInstance().getAll();
//		session.setAttribute("listCus", newListCus);
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
