package Controller;

import DAO.InventoryDAO;
import DAO.InventoryTransactionDAO;
import Model.Inventory;
import Model.InventoryTransaction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "managerInvenServlet", value = "/managerInvenServlet")
public class managerInvenServlet extends HttpServlet {
    private final int MAX_STOCK = 150;
    private final int MIN_STOCK = 30;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try {
            List<Inventory> listProNotOrdered =  InventoryDAO.getProductsNotOrderedLast3Months();
            List<Inventory> listNeedImported =  InventoryDAO.getProductsNeedImported( MIN_STOCK, MAX_STOCK);
            List<InventoryTransaction> listInventoryTran = InventoryTransactionDAO.getAllTransactionLast3Months();

            session.setAttribute("listProNotOrdered", listProNotOrdered);
            session.setAttribute("listNeedImported", listNeedImported);
            session.setAttribute("listInventoryTran", listInventoryTran);

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