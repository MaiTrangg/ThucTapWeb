package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.CustomerDao;
import Model.Customer;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;
import util.MaHoa;
import util.UniqueStringGenerator;

@WebServlet("/LoginGoogleHandler")
public class LoginGoogleHandler extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code != null) {
			String accessToken = getToken(code);
			UserGoogleDto user = getUserInfo(accessToken);
			CustomerDao customerDao = CustomerDao.GetInstance();
			Customer customer = customerDao.getCustomerByEmail(user.getEmail());

			if (customer == null) {
				// If user does not exist, create a new one
				customer = new Customer();
				customer.setUsername(user.getName());
				customer.setEmail(user.getEmail());
				customer.setNumberPhone("");  // Assuming number phone is not available from Google info
				String pass = MaHoa.toSHA1(UniqueStringGenerator.generateRandomPassword());
				customer.setPassword(pass);// Password should be handled securely if required

				customerDao.insert(customer);
			}

			// Set user in session
			request.getSession().setAttribute("customer", customer);
			// Redirect to home page or the desired page
			//response.sendRedirect("index.jsp");
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);

		} else {
			// Redirect to login page if code is not available
			//response.sendRedirect("login.jsp");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);

		}
	}

	public static String getToken(String code) throws ClientProtocolException, IOException {
		// Call API to get token
		String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
						.add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
						.add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
				.execute().returnContent().asString();

		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
		return accessToken;
	}

	public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);
		return googlePojo;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
