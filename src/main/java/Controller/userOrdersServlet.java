package Controller;

import DAO.OrderDao;
import Model.Customer;
import Model.Order;
import Model.OrderDetail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/userOrdersServlet")
public class userOrdersServlet extends HttpServlet {
    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Customer customer = (Customer) session.getAttribute("customer");
//
//        if (customer != null) {
//            OrderDao orderDAO = new OrderDao();
//            List<Order> orders = orderDAO.getOrdersByCustomerId(customer.getUser_id());
//
//            if (orders != null && !orders.isEmpty()) {
//                System.out.println("Orders fetched successfully: " + orders.size());
//            } else {
//                System.out.println("No orders found for customer ID: " + customer.getUser_id());
//            }
//
//            request.setAttribute("showOrders", orders);
//            request.getRequestDispatcher("/WEB-INF/userOrders.jsp").forward(request, response);
//        } else {
//            System.out.println("Customer not found in session.");
//            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
//        }
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String orderIdParam = request.getParameter("orderId");

        if (orderIdParam != null) {
            try {
                int orderId = Integer.parseInt(orderIdParam);
                OrderDao orderDAO = new OrderDao();
                List<OrderDetail> orderDetails = orderDAO.getOrderDetailsByOrderId(orderId);
                request.setAttribute("orderDetails", orderDetails);
                request.getRequestDispatcher("/WEB-INF/orderDetails.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Xử lý nếu orderIdParam không phải là một số nguyên hợp lệ
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid orderId");
            } catch (Exception e) {
                // Xử lý các lỗi khác
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
                e.printStackTrace(); // In ra lỗi để debug
            }
            return;
        }

        // Xử lý lấy danh sách đơn hàng của khách hàng nếu không có orderIdParam
        if (customer != null) {
            try {
                OrderDao orderDAO = new OrderDao();
                List<Order> orders = orderDAO.getOrdersByCustomerId(customer.getUser_id());
                request.setAttribute("showOrders", orders);
                request.getRequestDispatcher("/WEB-INF/userOrders.jsp").forward(request, response);
            } catch (NullPointerException ex) {
                // Xử lý nếu customer là null
                response.sendRedirect("/WEB-INF/login.jsp");
                ex.printStackTrace();
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
                e.printStackTrace(); // In ra lỗi để debug
            }
        } else {
            response.sendRedirect("/WEB-INF/login.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}