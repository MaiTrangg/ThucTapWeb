package Controller;

import DAO.InventoryDAO;
import DAO.InventoryTransactionDAO;
import DAO.ProductDAO;
import Model.Inventory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "ad_deleteproservlet", value = "/ad_deleteproservlet")
public class ad_deleteproservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // Lấy danh sách ID sản phẩm từ request parameter
        String idList = request.getParameter("idpro");
//        int idpro = Integer.parseInt(request.getParameter("idpro"));
        if (idList != null && !idList.isEmpty()) {
            // Chia chuỗi idList thành mảng các ID
            String[] ids = idList.split(",");

            // Lặp qua từng ID và xử lý xóa sản phẩm (ví dụ)
            for (String id : ids) {
                int idpro = Integer.parseInt(id);
                //lay ra san pham dang xoa trong kho
                Inventory inventory = InventoryDAO.getInventoryPro(idpro);

                Timestamp lastUpdated = new Timestamp(System.currentTimeMillis());

                //ghi lai giao dich kho
                InventoryTransactionDAO.insertInventoryTransaction(idpro,"Xóa",inventory.getQuantity(),
                        lastUpdated,"Xóa sản phẩm ra khỏi kho: có mã sp: "+idpro+", tên sp: "+
                                inventory.getProduct().getName());

                //xoa san pham ra khoi kho
                ProductDAO.deleteProduct(idpro);

            }

            response.sendRedirect("managerProServerlet");
        } else {
            // Nếu idList không có giá trị, có thể xử lý báo lỗi hoặc điều hướng tới trang lỗi
            System.out.println("idpro la null");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}