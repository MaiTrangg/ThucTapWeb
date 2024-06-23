package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDao;

/**
 * Servlet implementation class ad_editCusServlet
 */
@WebServlet("/ad_editCusServlet")
public class ad_editCusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ad_editCusServlet() {
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
		String email = request.getParameter("email");
		int isadmin = Integer.parseInt(request.getParameter("isadmin"));
		String numberphone = request.getParameter("numberphone");
		String oldUsername = (String) session.getAttribute("u");
		String oldEmail = (String) session.getAttribute("e");
		System.out.println("re "+CustomerDao.GetInstance().update(username, isadmin, email, numberphone,oldUsername,oldEmail));
		session.removeAttribute("cus");
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
