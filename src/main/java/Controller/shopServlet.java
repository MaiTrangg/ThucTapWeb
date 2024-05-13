package Controller;

import java.io.IOException;
import java.io.PrintWriter;
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
        List<Product> listPro = pdao.getAllProduct();
        List<Category> listCate = pdao.countProByCategory();



        session.setAttribute("listP", listPro);
        session.setAttribute("listCate", listCate);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        for (Product p : listPro){
            out.println("<div id=\"show-pro\" class=\"col-md-6 col-lg-6 col-xl-4\" >\n" +
                    "                                    <div class=\"rounded position-relative fruite-item \">\n" +
                    "                                        <div class=\"fruite-img\">\n" +
                    "                                            <img src=\""+p.getImg()+"\" class=\"img-fluid w-100 rounded-top \" alt=\"\">\n" +
                    "                                        </div>\n" +
                    "                                        <div class=\"text-white bg-secondary px-3 py-1 rounded position-absolute\" style=\"top: 10px; left: 10px;\">Food</div>\n" +
                    "                                        <div class=\"p-4 border border-secondary border-top-0 rounded-bottom \">\n" +
                    "                                            <h4>"+p.getName()+"</h4>\n" +
                    "                                            <p>"+p.getDescriptionP()+"</p>\n" +
                    "                                            <div class=\"d-flex justify-content-between flex-lg-wrap\">\n" +
                    "                                                <p class=\"text-dark fs-5 fw-bold mb-0\">"+p.getSellingPrice()+"</p>\n" +
                    "\n" +
                    "                                                <a href=\"addToCartServlet?idpro="+p.getProductId()+"&value=add\" class=\"btn border border-secondary rounded-pill px-3 text-primary\"><i class=\"fa fa-shopping-bag me-2 text-primary\"></i>Add </a>\n" +
                    "                                                <a href=\"addToCartServlet?idpro="+p.getProductId()+"&value=buy\" class=\"btn border border-secondary rounded-pill px-3 text-primary\">Buy</a>\n" +
                    "                                            </div>\n" +
                    "                                        </div>\n" +
                    "                                    </div>\n" +
                    "                                </div>");
        }
        out.flush();
        out.close();


        request.getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
//        request.getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
	}

}