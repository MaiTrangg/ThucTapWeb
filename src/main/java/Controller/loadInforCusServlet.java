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
 * Servlet implementation class loadInforCusServlet
 */
@WebServlet("/loadInforCusServlet")
public class loadInforCusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadInforCusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username = request.getParameter("username_c");
		String email = request.getParameter("email_c");
		session.setAttribute("u", username);
		session.setAttribute("e", email);
		Customer cus = CustomerDao.GetInstance().getbyID(username, email);
		session.setAttribute("cus", cus);
		//response.sendRedirect("WEB-INF/ad_editCustomer.jsp");
		request.getRequestDispatcher("WEB-INF/ad_editCustomer.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
