package Controller;

import DAO.CouponDAO;
import Model.Coupon;
import Model.Customer;
import Model.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/applyCouponServlet")
public class ApplyCouponServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"User not logged in\"}");
            return;
        }

        String couponCode = request.getParameter("couponCode");
        CouponDAO couponDAO = new CouponDAO();
        Coupon coupon = couponDAO.getCouponByCode(couponCode);

        if (coupon == null) {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Invalid coupon code\"}");
            return;
        }

        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"No order found\"}");
            return;
        }

        double discountAmount = calculateDiscount(order.getTotalMoney(), coupon);
        double newTotalPrice = order.getTotalMoney() - discountAmount;

        response.setContentType("application/json");
        response.getWriter().write("{\"success\": true, \"discountAmount\": " + discountAmount + ", \"newTotalPrice\": " + newTotalPrice + "}");
    }

    private double calculateDiscount(double orderTotal, Coupon coupon) {
        double discountAmount = 0.0;
        if (coupon.getDiscountType().equals("percentage")) {
            discountAmount = orderTotal * (coupon.getDiscountValue() / 100);
        } else if (coupon.getDiscountType().equals("flat")) {
            discountAmount = coupon.getDiscountValue();
        }

        // Ensure discount does not exceed maximum discount value
        if (coupon.getMaxDiscountValue() != null && discountAmount > coupon.getMaxDiscountValue()) {
            discountAmount = coupon.getMaxDiscountValue();
        }

        return discountAmount;
    }
}
