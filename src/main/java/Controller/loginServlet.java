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
import Model.Geo_API;
import Model.ILog;
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("đã vào loginServlet");
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		String username = request.getParameter("login-username").trim();
		System.out.println("UserName: "+username);
		String password = request.getParameter("login-password").trim();
		System.out.println("Pass: "+password);

//		String clientIP = new Geo_API().getClientIp(request); //lay ra dia chi ip cua may khach
		if(username.isEmpty()|| username == null) {
			session.setAttribute("error", "Nhập vào username!");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);

		} else
		if(!password.isEmpty() && password != null) {
			password = MaHoa.toSHA1(password);
		}else {
			session.setAttribute("error", "Nhập vào password!");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
		}
//		password = MaHoa.toSHA1(password);
		Customer c = new Customer(username, password);
		System.out.print(c);
		Customer recustomer = CustomerDao.GetInstance().getbyID(c);
		System.out.print("reCustomer: "+recustomer);
		ILog log = new LogDao();
		if(recustomer==null) {
			//ghi log

			log.warn("Chưa đăng nhập","đăng nhập thất bại",username,request.getSession().getId(),request.getRemoteAddr());

			//thong bao loi
			String error = "Sai thông tin đăng nhập!";
			session.setAttribute("error", error);
//			request.getRequestDispatcher("/login").forward(request, response);
//			response.sendRedirect("login");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
		}else {
			if (session != null) {
				//ghi log
			log.info("Chưa đăng nhập","đăng nhập thành công",username,request.getSession().getId(),request.getRemoteAddr());

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
