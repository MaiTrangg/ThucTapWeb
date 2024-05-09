package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Order;
import Model.OrderDetail;

/**
 * Servlet implementation class removeToCartServlet
 */
@WebServlet("/removeToCartServlet")
public class removeToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	HttpSession session = request.getSession();
	Order o = (Order) session.getAttribute("order");
	int  idpro = Integer.parseInt(request.getParameter("idpro"));
	System.out.println(idpro);
	o.removeOrderline(o.getOrderLinebyIDPro(idpro));
	session.setAttribute("order", o);
		double newTotal = o.total();
		System.out.println("newtotal: "+newTotal);
		double newPrice =0 ;// Lấy giá mới từ dữ liệu hoặc tính toán lại giá mới
		for (OrderDetail od : o.getOrderDetails()){
			if(od.getProduct().getProductId()==idpro){
				newPrice=od.getPrice();
				break;
			}
		}
		int quantityCart = o.getOrderLines().size();
		System.out.println("newprice: "+newPrice);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		String newHtml="";

		out.print("{\"newPrice\": " + newPrice + ", \"newTotalPrice\": " + newTotal +  ", \"quantityCart\": " + quantityCart + "}");
		out.flush();
		out.close();

		request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
