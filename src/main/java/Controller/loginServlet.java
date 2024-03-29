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
 * Servlet implementation class loginServerlet
 */
@WebServlet(name = "loginServlet", value="/loginServlet")
public class loginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("đã vào loginServlet");
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		String username = request.getParameter("login-username").trim();
		System.out.println("UserName: "+username);
		String password = request.getParameter("login-password").trim();
		System.out.println("Pass: "+password);
		if(username.isEmpty()|| username == null) {
			session.setAttribute("error", "Please, enter username!");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);

		} else
		if(!password.isEmpty() && password != null) {
			password = MaHoa.toSHA1(password);
		}else {
			session.setAttribute("error", "please enter password!");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
		}
//		password = MaHoa.toSHA1(password);
		Customer c = new Customer(username, password);
		System.out.print(c);
		Customer recustomer = CustomerDao.GetInstance().getbyID(c);
		System.out.print("reCustomer: "+recustomer);
		if(recustomer==null) {
			String error = "Wrong login information, please try again!";
			session.setAttribute("error", error);
//			request.getRequestDispatcher("/login").forward(request, response);
//			response.sendRedirect("login");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
		}else {
			if (session != null) {
			session.removeAttribute("error");
			session.setAttribute("customer", recustomer);
			System.out.println("Đã vào login và customer là: "+ recustomer );
				request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
			}
		}
//		HttpSession session = request.getSession();
//		session.setAttribute("error", "Đây là lỗi");
//		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
