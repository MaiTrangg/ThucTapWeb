package DBConnection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection connection = null;
		try {
		    // Load the MySQL JDBC driver
//			com.mys
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    String url = "jdbc:mysql://localhost:3306/store?useUnicode=true&characterEncoding=UTF-8";
		    String user = "root";


		    String password = "010814";





		    try {
				connection =  DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		return connection;

	}
	
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Connection c = getConnection();
		System.out.println(c);

		// Đóng kết nối

	}

}


