package Controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "shopDetail", value = "/shopDetail")
public class shopDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        System.out.println(pid);
        Product product = ProductDAO.getProductByID(Integer.parseInt(pid));
        Category category = CategoryDAO.getCategoryByID(product.getCategory().getQuantity());
        System.out.println("phân loại"+category);
        List<Product> list = ProductDAO.getAllProduct();
        request.setAttribute("category",category);
        request.setAttribute("pro",product);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/WEB-INF/shopDetail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}