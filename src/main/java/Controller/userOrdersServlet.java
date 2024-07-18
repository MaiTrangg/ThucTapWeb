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

            if (orders != null && !orders.isEmpty()) {
                System.out.println("Orders fetched successfully: " + orders.size());
            } else {
                System.out.println("No orders found for customer ID: " + customer.getUser_id());
            }

            request.setAttribute("showOrders", orders);
            request.getRequestDispatcher("/WEB-INF/userOrders.jsp").forward(request, response);
        } else {
            System.out.println("Customer not found in session.");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}