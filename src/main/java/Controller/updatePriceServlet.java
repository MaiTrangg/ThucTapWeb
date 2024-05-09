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

import Model.Order;
import Model.OrderDetail;

/**
 * Servlet implementation class updatePriceServlet
 */
@WebServlet("/updatePriceServlet")
public class updatePriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePriceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("haha");
		System.out.println(request.getQueryString()+" dữ liệu được gửi đi");
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String i =request.getParameter("idpro");
		int idpro = Integer.parseInt(i);
		System.out.println("id: "+idpro);
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("quantity: "+quantity);
//		System.out.println("quantity là: "+quantity);
		Order o = (Order) session.getAttribute("order");
		o.updatePrice(idpro, quantity);
		session.setAttribute("order", o);
		// Xử lý yêu cầu từ client và cập nhật giá mới
		double newTotal = o.total();
		System.out.println("newtotal: "+newTotal);
		int newQuantity =0;
		double newPrice =0 ;// Lấy giá mới từ dữ liệu hoặc tính toán lại giá mới
		for (OrderDetail od : o.getOrderDetails()){
			if(od.getProduct().getProductId()==idpro){
				newPrice=od.getPrice();
				newQuantity = od.getQuantity();
				break;
			}
		}
		int quantityCart = o.getOrderLines().size();
		System.out.println("newprice: "+newPrice);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		String newHtml="";

		out.print("{\"newPrice\": " + newPrice + ", \"newTotalPrice\": " + newTotal +", \"newQuantity\": " + newQuantity +", \"quantityCart\": " + quantityCart + "}");
//		out.print("{\"newHtml\": \"" + newHtml + "\", \"newTotalPrice\": " + newTotal + "}");
//		out.print("{\"newHtml\": \"" + newHtml + "\", \"newTotalPrice\": " + newTotal + "}");

		out.flush();
		out.close();

		request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
//		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
