package Controller;

import DAO.CouponDAO;
import Model.Coupon;
import Model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/CouponServlet")
public class CouponServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CouponDAO couponDAO = new CouponDAO();
        List<Coupon> couponList = couponDAO.getAllCoupons();
        request.setAttribute("couponList", couponList);
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("customer");
        request.setAttribute("customer",cus);
        System.out.println(cus);
        request.getRequestDispatcher("/WEB-INF/coupon.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        int couponId = Integer.parseInt(request.getParameter("coupon_id"));

        CouponDAO couponDAO = new CouponDAO();
        boolean hasUsed = couponDAO.hasUserUsedCoupon(customerId, couponId);

        if (hasUsed) {
            request.setAttribute("message", "Bạn đã sài mã này");
        } else {
            couponDAO.recordCouponUsage(customerId, couponId);
            request.setAttribute("message", "Lưu mã thành công");

            HttpSession session = request.getSession();
            Coupon savedCoupon = couponDAO.getCouponById(couponId);  // Get the saved coupon details
            session.setAttribute("savedCoupon", savedCoupon);  // Save the coupon in the session
        }

        List<Coupon> couponList = couponDAO.getAllCoupons();
        request.setAttribute("couponList", couponList);
        request.getRequestDispatcher("/WEB-INF/coupon.jsp").forward(request, response);
    }
}

