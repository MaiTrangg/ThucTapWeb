package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.InventoryDAO;
import DAO.InventoryTransactionDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Inventory;
import Model.Product;

/**
 * Servlet implementation class ad_editproServlet
 */
@MultipartConfig 
@WebServlet("/ad_editproServlet")
public class ad_editproServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ad_editproServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		int idpro =(int) session.getAttribute("idpro");
//		System.out.println(idpro);
//		String name = request.getParameter("name");
//		System.out.println(name);
//		String img = request.getParameter("img");
//		System.out.println(img);
//		int available = Integer.parseInt(request.getParameter("available"));
//		System.out.println(available);
//		double price = Double.parseDouble(request.getParameter("price"));
//		System.out.println(price);
//		
//		ProductDao.updateProduct(idpro, name, img, price, available);
//		session.removeAttribute("pro");
////		response.sendRedirect("managerProServerlet");
//		request.getRequestDispatcher("managerProServerlet").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
        	HttpSession session = request.getSession();
        	int idpro =(int) session.getAttribute("idpro");
    		System.out.println(idpro);
            String namepro = request.getParameter("name");
            int availablepro = Integer.parseInt(request.getParameter("available"));
            double pricepro = Double.parseDouble(request.getParameter("price"));
            String descriptionP = request.getParameter("descriptionP");
            int idcate = Integer.parseInt(request.getParameter("category"));

            //tính số lượng thay đổi khi dieu chinh
            Inventory inven = InventoryDAO.getInventoryPro(idpro);
            int numberChange = availablepro - inven.getQuantity();

            
            Part part = request.getPart("img");
            System.out.println(part+" part");
            
            String realPath = request.getServletContext().getRealPath("/images");
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            File directory = new File(realPath);
            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("Đã tạo thư mục: " + realPath);
            } else {
            	System.out.println("Thư mục đã tồn tại: " + realPath);
            }

            part.write(realPath +File.separator + filename);
            System.out.println("aa");

            // Lưu đường dẫn ảnh vào cơ sở dữ liệu
//            ProductDao.insertProduct(namepro, "images/" + filename, pricepro, availablepro);
            System.out.println(filename+" filename");
            //tạo product mới từ thông tin người dùng cung cấp
            Product newPro = new Product(idpro,  "images/" +File.separator+ filename,
                    namepro, descriptionP,pricepro,pricepro ,new Category(idcate));
            Timestamp lastUpdated = new Timestamp(System.currentTimeMillis());
            Inventory inventory = new Inventory(0, newPro, availablepro,lastUpdated);
            InventoryDAO.updateInventory(inventory);

            //ghi lai giao dich kho
            InventoryTransactionDAO.insertInventoryTransaction(idpro,"Điều chỉnh",numberChange,lastUpdated,
                    "Điều chỉnh sản phẩm trong kho: có mã sp: "+newPro.getProductId()+", tên: "+newPro.getName());

            System.out.println("bb");

            response.sendRedirect("managerProServerlet?message=Upload Successful");
        } catch (Exception e) {
            response.sendRedirect("managerProServerlet?message=Upload Failed: " + e.getMessage());
        }
    }

}