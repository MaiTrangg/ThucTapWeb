package Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
	private static final int MAX_FAILED_ATTEMPTS = 5;
	private static final int LOCK_TIME_DURATION = 3; // hours
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
			Customer dbCustomer = CustomerDao.GetInstance().getbyUsername(username); // Lấy thông tin người dùng từ username
			if (dbCustomer != null) {
				int failedAttempts = dbCustomer.getFailedAttempts();
				Timestamp lockTime = dbCustomer.getLock_time();

				if (lockTime != null && lockTime.toLocalDateTime().plusHours(LOCK_TIME_DURATION).isAfter(LocalDateTime.now())) {
					session.setAttribute("error", "Tài khoản đã bị khóa. Vui lòng thử lại sau.");
					request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
					return;
				}else {
					CustomerDao.GetInstance().resetFailedAttempts(username);
				}

				failedAttempts++;
				if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
					CustomerDao.GetInstance().lockAccount(username);
					session.setAttribute("error", "Tài khoản đã bị khóa do đăng nhập <br> sai quá nhiều lần. Vui lòng thử lại sau.");
				} else {
					CustomerDao.GetInstance().updateFailedAttempts(username, failedAttempts);
					session.setAttribute("error", "Sai mật khẩu! Bạn còn " + (MAX_FAILED_ATTEMPTS - failedAttempts) + " lần thử.");
				}
			} else {
				session.setAttribute("error", "Sai thông tin đăng nhập!");
			}
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);



//			//thong bao loi
//			String error = "Sai thông tin đăng nhập!";
//			session.setAttribute("error", error);
//			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
		}else {
			if (session != null) {
				//ghi log
			log.info("Chưa đăng nhập","đăng nhập thành công",username,request.getSession().getId(),request.getRemoteAddr());
				CustomerDao.GetInstance().resetFailedAttempts(recustomer.getUsername());
				session.removeAttribute("error");
			session.setAttribute("customer", recustomer);
			System.out.println("Đã vào login và customer là: "+ recustomer );
				request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
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
