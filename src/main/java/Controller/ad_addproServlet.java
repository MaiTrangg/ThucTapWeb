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
import javax.servlet.http.Part;


import DAO.InventoryDAO;
import DAO.InventoryTransactionDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Inventory;
import Model.InventoryTransaction;
import Model.Product;

/**
 * Servlet implementation class ad_addproServlet
 */
@MultipartConfig 
@WebServlet("/ad_addproServlet")
public class ad_addproServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ad_addproServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try {
			String namepro = request.getParameter("name");
			System.out.println("name pro: "+ namepro);
			String descriptionpro = request.getParameter("description");
			int categorypro = Integer.parseInt(request.getParameter("category"));
//				System.out.println("categorypro "+categorypro);
			int availablepro = Integer.parseInt(request.getParameter("available"));
			double pricepro = Double.parseDouble(request.getParameter("price"));

			Part part = request.getPart("image");

			String realPath = request.getServletContext().getRealPath("/images");
			String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

			File directory = new File(realPath);
			if (!directory.exists()) {
				directory.mkdirs();
				System.out.println("Đã tạo thư mục: " + realPath);
			} else {
				System.out.println("Thư mục đã tồn tại: " + realPath);
			}

			part.write(realPath + File.separator + filename);
			System.out.println("aa");

			// Lưu đường dẫn ảnh vào cơ sở dữ liệu
			Product pro = new Product(0, "images/" + File.separator + filename,
					namepro, descriptionpro, pricepro, pricepro,new Category(categorypro));
			Timestamp lastUpdated = new Timestamp(System.currentTimeMillis());
			int proID = new ProductDAO().insertProduct(pro);
			pro.setProductId(proID);
			Inventory inventory = new Inventory(0, pro, availablepro,lastUpdated);
			InventoryDAO.insertInventory(inventory);
			// ghi lai giao dich nhap kho
			InventoryTransactionDAO.insertInventoryTransaction(proID,"Nhập",availablepro,lastUpdated,"Thêm sản phẩm vào kho: có mã sp: "+pro.getProductId()+", tên: "+pro.getName());



//	            ProductDAO.insertProduct(namepro, "images/" + File.separator + filename, pricepro, availablepro);
//	            System.out.println("bb");

			response.sendRedirect("managerProServerlet?message=Upload Successful");
		} catch (Exception e) {
			response.sendRedirect("managerProServerlet?message=Upload Failed: " + e.getMessage());
		}

	}

	/**
	
	

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
			doGet(request,response);

		}


	}
        
    

	

