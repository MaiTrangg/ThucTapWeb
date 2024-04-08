package Controller;

import DAO.ProductDAO;
import Model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "processCategoryTab", value = "/processCategoryTab")
public class processCategoryTab extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String nameTab = request.getParameter("nameTab");
    System.out.println("nametab: "+nameTab);
    HttpSession session = request.getSession();
    List<Product> listP = ProductDAO.getProductByCategory(nameTab);
    session.setAttribute("listP", listP);
        session.setAttribute("nameTab", nameTab);

        request.getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}