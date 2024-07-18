package Controller;

import DAO.CustomerDao;
import Model.Customer;
import util.MaHoa;

import javax.management.relation.Role;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginFacebookControll", value = "/LoginFacebookControll")
public class LoginFacebookControll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        System.out.println("Code: " + code);

        // Tạo session cho người dùng
        HttpSession session = request.getSession();

        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = RestFb.getToken(code);
            System.out.println(accessToken);
            AccountForFb acc = RestFb.getUserInfo(accessToken);
            System.out.println(acc);

            CustomerDao customerDao = new CustomerDao();
            Customer customer = customerDao.getCustomerByEmail(acc.getEmail());
            System.out.println("Find acc: " + customer);

            if (customer == null) {
                // Tạo tài khoản mới
                customer = new Customer();
                customer.setUsername(acc.getName());
                customer.setEmail(acc.getEmail());
                String password = MaHoa.toSHA1("login-facebook/" + acc.getId());
                customer.setPassword(password);
                System.out.println(customer);
                customerDao.insert(customer);
                customer = customerDao.getCustomerByEmail(customer.getEmail());
                System.out.println(customer);

                session.setAttribute("customer", customer);
            } else {
                session.setAttribute("customer", customer);
            }
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}