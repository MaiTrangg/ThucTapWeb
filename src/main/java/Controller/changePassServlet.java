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
import util.MaHoa;

/**
 * Servlet implementation class changePassServlet
 */
@WebServlet("/changePassServlet")
public class changePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changePassServlet() {
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
		session.removeAttribute("errorFromChangePass");
		String newPass = request.getParameter("newpass").trim();
		if(newPass.isEmpty() || newPass == null) {
			session.setAttribute("errorFromChangePass", "Enter new password!");
			response.sendRedirect("changePass");
		}else {
		
		String confirmnewpass = request.getParameter("confirmnewpass").trim();
		if(!newPass.equals(confirmnewpass)) {
			session.setAttribute("errorFromChangePass", "Nhập lại mật khẩu mới sai!");
			response.sendRedirect("changePass");
		}else {
		Customer c = (Customer) session.getAttribute("customer");
		String pass = MaHoa.toSHA1(newPass);
		
		CustomerDao.GetInstance().updatePassword(c.getUsername(), c.getEmail(), pass);
		session.removeAttribute("customer");
//		c= CustomerDao.GetInstance().getbyID(c.getUsername(), c.getEmail());
//		session.setAttribute("customer", c);
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
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
