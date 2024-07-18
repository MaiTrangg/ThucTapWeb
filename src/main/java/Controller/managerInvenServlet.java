package Controller;

import DAO.InventoryDAO;
import Model.Inventory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "managerInvenServlet", value = "/managerInvenServlet")
public class managerInvenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try {
            List<Inventory> listProNotOrdered =  InventoryDAO.getProductsNotOrderedLast3Months();
            session.setAttribute("listProNotOrdered", listProNotOrdered);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/WEB-INF/managerInventory.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}