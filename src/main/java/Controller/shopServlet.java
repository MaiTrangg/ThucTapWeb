package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProductDAO;
import Model.Category;
import Model.Product;

/**
 * Servlet implementation class homeServlet
 */
@WebServlet("/shopServlet")
public class shopServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO pdao = new ProductDAO();
//        List<Product> listPro = pdao.getAllProduct();
        List<Category> listCate = pdao.countProByCategory();

        String category = request.getParameter("category");
        List<Product> listPro = (category != null && !category.isEmpty()) ?
                pdao.getProductByCategory(category) : pdao.getAllProduct();

        int numberPerPage = 15;
        int size = listPro.size();
        int num = (size % 15 == 0 ? (size / 15) : ((size / 15)) + 1);
        int page;

        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }

        int start = (page - 1) * numberPerPage;
        int end = Math.min(page * numberPerPage, size);
        List<Product> list = pdao.getListByPage(listPro, start, end);

        session.setAttribute("listP", list);
        session.setAttribute("page", page);
        session.setAttribute("num", num);
        session.setAttribute("category", category);
        session.setAttribute("listCate", listCate);

        request.getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}