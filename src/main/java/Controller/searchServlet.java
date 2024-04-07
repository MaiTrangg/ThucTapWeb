package Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import Model.Product;
import DAO.DigitalDAO;

@WebServlet(name = "searchServlet", value = "/searchServlet")
public class searchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String name = request.getParameter("search");
        DigitalDAO dao = new DigitalDAO();

        List<Product> list = dao.getProductByName(name);

        HttpSession session = request.getSession();
        session.setAttribute("listP", list);

        // Forward to the Shop.jsp instead of redirecting
        request.getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}