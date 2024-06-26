package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDao;
import DAO.LogDao;
import Model.Customer;
import Model.ILog;
import util.MaHoa;

/**
 * Servlet implementation class registerServerlet
 */
@WebServlet("/registerServerlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
		String username = request.getParameter("register-username").trim();
		session.setAttribute("username", username);
		String errorFromRegister="please enter username!";
		String pass = request.getParameter("register-password").trim();
		
		session.setAttribute("pass", pass);
		String email = request.getParameter("register-email");
		session.setAttribute("email", email);
		String numberphone = request.getParameter("register-numberphone");
		session.setAttribute("numberphone", numberphone);
		
		if(username.isEmpty()|| username==null) {
			session.setAttribute("errorFromRegister", errorFromRegister);
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		}else {
		if(!pass.isEmpty() && pass != null) {
			pass = MaHoa.toSHA1(pass);
		}else {
			session.setAttribute("errorFromRegister", "please enter pass!");
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		}
			ILog log = new LogDao();
		Customer customer = new Customer(username, pass, email, numberphone, 0);
//		session.removeAttribute("errorFromRegister");
		if(CustomerDao.GetInstance().checkExistUsername(customer) != null ) {
			 errorFromRegister = "User name exited!";
			 session.setAttribute("errorFromRegister", errorFromRegister);
			 //log
			log.info("Chưa đăng kí","đăng kí thất bại: "+errorFromRegister,username,request.getSession().getId(),request.getRemoteAddr());

			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		}
		if(CustomerDao.GetInstance().checkExistEmail(customer.getEmail()) != null ) {
			 errorFromRegister = "Email exited!";
			 session.setAttribute("errorFromRegister", errorFromRegister);
			//log
			log.info("Chưa đăng kí","đăng kí thất bại: "+errorFromRegister,username,request.getSession().getId(),request.getRemoteAddr());
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		}
		
		else {
			CustomerDao.GetInstance().ad_insertUser(customer);
			session.removeAttribute("errorFromRegister");
			session.removeAttribute("username");
			session.removeAttribute("pass");
			session.removeAttribute("email");
			session.removeAttribute("numberphone");
			//log
			log.info("Chưa đăng kí","đăng kí thành công: "+customer.toString(),username,request.getSession().getId(),request.getRemoteAddr());

			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
