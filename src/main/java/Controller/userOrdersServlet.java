package Controller;

import DAO.OrderDao;
import Model.Customer;
import Model.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/userOrdersServlet")
public class userOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null) {
            OrderDao orderDAO = new OrderDao();
            List<Order> orders = orderDAO.getOrdersByCustomerId(customer.getUser_id());
            session.setAttribute("showOrders", orders);
            System.out.println("hehe" + orders);
            request.getRequestDispatcher("/WEB-INF/userOrders.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
//        request.getRequestDispatcher("/WEB-INF/userOrders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}