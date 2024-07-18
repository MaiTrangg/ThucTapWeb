package Controller;

import DAO.CouponDAO;
import Model.Coupon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "cart", value = "/cart")
public class cart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Coupon> listCoupon = CouponDAO.getCoupons();
        System.out.println(listCoupon);
        request.setAttribute("listCoupon",listCoupon);

        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}