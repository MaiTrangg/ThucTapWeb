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
 * Servlet implementation class enterCodeServerlet
 */
@WebServlet("/enterCodeServerlet")
public class enterCodeServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public enterCodeServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String code = request.getParameter("code").trim();

		String email = (String) session.getAttribute("email");
		String getcode = CustomerDao.GetInstance().getcode(email);
//		session.removeAttribute("email");
		if(!code.equals(getcode)) {
			session.setAttribute("errorFromEnterCode", "your code is wrong!");
			response.sendRedirect("WEB-INF/enterCode.jsp");
		}else {
		session.removeAttribute("email");
		session.removeAttribute("errorFromEnterCode");
		Customer customer=  CustomerDao.GetInstance().getCustomer(email, getcode);
		session.setAttribute("customer", customer);
		request.getRequestDispatcher("WEB-INF/success.jsp").forward(request,response);
//		response.sendRedirect("WEB-INF/success.jsp");
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
