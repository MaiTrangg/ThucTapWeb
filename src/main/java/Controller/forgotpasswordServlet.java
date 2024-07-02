package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDao;

import util.Email;
import util.UniqueStringGenerator;

/**
 * Servlet implementation class forgotpasswordServerlet
 */
@WebServlet("/forgotpasswordServerlet")
public class forgotpasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.removeAttribute("errorFromForgotpassword");
		String email = request.getParameter("forgotpassword-email").trim();
		if(email.isEmpty() || email == null) {
			session.setAttribute("errorFromForgotpassword", "Enter your email !");
			request.getRequestDispatcher("/WEB-INF/forgotpassword.jsp").forward(request, response);
			
		}
		String pass=CustomerDao.GetInstance().checkExistEmail(email);
		if(pass==null) {
			String errorFromForgotpassword = "Email is wrong! ";
			session.setAttribute("errorFromForgotpassword", errorFromForgotpassword);

			request.getRequestDispatcher("/WEB-INF/forgotpassword.jsp").forward(request, response);
		}else {
			session.removeAttribute("errorFromForgotpassword");
//			System.out.println(email);
//			System.out.println(pass);
			String code = UniqueStringGenerator.generateUniqueString();
			System.out.println("code "+code);
			CustomerDao.GetInstance().updateCode(email, code);
			session.setAttribute("email", email);
			Email.sendEmail(email, code);//gửi email
			request.getRequestDispatcher("WEB-INF/enterCode.jsp").forward(request, response);
//			response.sendRedirect("/WEB-INF/enterCode.jsp");
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
