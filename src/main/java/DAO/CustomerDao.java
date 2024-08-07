package DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DBConnection.JDBCUtil;
import Model.Customer;

public class CustomerDao {
	private Connection con;
	private String query;



	public static CustomerDao GetInstance() {
		return new CustomerDao();
	}
//	private String query;


//	public CustomerDao() {
//		this.con = JDBCUtil.getConnection();
//
//	}

	private Connection getConnection() throws SQLException {
		if (this.con == null || this.con.isClosed()) {
			this.con = JDBCUtil.getConnection();
		}
		return this.con;
	}

	public  int insert(Customer t) {
		// TODO Auto-generated method stub
		int re=0;

		try {
			con = JDBCUtil.getConnection();
			String query = "INSERT INTO customers(username, pass,email, numberphone) VALUES(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, t.getUsername());
			pst.setString(2, t.getPassword());
			pst.setString(3, t.getEmail());
			pst.setString(4, t.getNumberPhone());
			re=pst.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return re;
	}
	public Customer getCustomerByEmail(String email) {
		Customer c = null;
		try (Connection con = getConnection()) {
			String query = "SELECT * FROM customers WHERE email = ?";
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, email);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						c = new Customer(
								rs.getString("username"),
								rs.getString("pass"),
								rs.getString("email"),
								rs.getString("numberphone"),
								rs.getInt("isadmin")
						);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	public  int ad_insertUser(Customer t) {
		// TODO Auto-generated method stub
		int re=0;
		try {
			con = JDBCUtil.getConnection();
			String query = "INSERT INTO customers(username, pass,email, numberphone, isadmin) VALUES(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, t.getUsername());
			pst.setString(2, t.getPassword());
			pst.setString(3, t.getEmail());
			pst.setString(4, t.getNumberPhone());
			pst.setInt(5, t.getIsAdmin());
			re=pst.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}


	public int update(String username, int isadmin, String email, String numberphone, String oldUsername, String oldEmail) {
		int re=0;
		try {
			con = JDBCUtil.getConnection();
			String query = "UPDATE customers "
	                + "SET username=?, "
	                + "isadmin=?, "
	                + "email=?, "
	                + "numberphone=? where username = ? and email = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setInt(2, isadmin);
			pst.setString(3, email );
			pst.setString(4, numberphone);
			pst.setString(5, oldUsername);
			pst.setString(6, oldEmail);
			re=pst.executeUpdate();
			System.out.println("re "+re);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	public int updatePassword(String username, String email, String pass) {
		int re=0;
		try {
			con = JDBCUtil.getConnection();
			String query = "UPDATE customers "
					+ "SET pass=? "
					+" where username = ? and email = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, pass);
			pst.setString(2, username);
			pst.setString(3, email );

			re=pst.executeUpdate();
			System.out.println("re "+re);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}


	public int delete(String username, String email) {
		int re=0;
		try {
			con = JDBCUtil.getConnection();
			String query = "DELETE from customers where username=? and email=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, email);
			re=pst.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}


	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		List<Customer> list = new ArrayList<Customer>();
		Customer c =null;
		try {
			con = JDBCUtil.getConnection();
			String query = "select * from customers";
			PreparedStatement pst = con.prepareStatement(query);

			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				c = new Customer(	rs.getString("username"),
						 rs.getString("pass"),
						 rs.getString("email"),
						 rs.getString("numberphone"),
						 rs.getInt("isadmin"));

//				Customer c = new Customer(username, password);
				list.add(c);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	public Customer getbyID(String username, String email) {
		// TODO Auto-generated method stub
//		List<Customer> list = new ArrayList<Customer>();
		Customer c =null;
		try {
			con = JDBCUtil.getConnection();
			String query = "select * from customers where username =? and email=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, email);
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
//				username,pass,email,numberphone,isadmin
				 c = new Customer(	rs.getString("username"),
									 rs.getString("pass"),
									 rs.getString("email"),
									 rs.getString("numberphone"),
									 rs.getInt("isadmin"));

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public  Customer getbyID(Customer t) {
		// TODO Auto-generated method stub
//		List<Customer> list = new ArrayList<Customer>();
		Customer c =null;
		try {
			con = JDBCUtil.getConnection();
			String query = "select * from customers where username =? and pass=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, t.getUsername());
			pst.setString(2, t.getPassword());
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
//				username,pass,email,numberphone,isadmin
				c = new Customer(	rs.getInt("customer_id"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getString("email"),
						rs.getString("numberphone"),
						rs.getInt("isadmin"),
						rs.getInt("failedAttempts"),
						rs.getTimestamp("lock_time")
				);

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public Customer checkExistUsername(Customer c){
		Customer cus = null;

		try {
			con = JDBCUtil.getConnection();
			String query = "SELECT * FROM Customers where username=? ";
			PreparedStatement pst;
			pst = con.prepareStatement(query);
			pst.setString(1, c.getUsername());
//			pst.setString(2, c.getPassword());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("pass");
				cus = new Customer(username, password);
				break;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cus;
	}

	public  String checkExistEmail(String email) {
		String re=null;
		try {
			con = JDBCUtil.getConnection();
			String query = "SELECT pass from customers where email=? ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				re= rs.getString("pass");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return re;
	}

	public Customer getCustomer(String email, String code) {
		Customer cus = null;

		try {
			con = JDBCUtil.getConnection();
			String query = "SELECT * FROM Customers where email =? and code =?";
			PreparedStatement pst;
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, code);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("pass");
				cus =  new Customer(	rs.getString("username"),
						rs.getString("pass"),
						rs.getString("email"),
						rs.getString("numberphone"),
						rs.getInt("isadmin"));
				break;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cus;
	}


	public  void updateCode(String email, String code) {
		String query = "Update customers set code =? where email =? ";
		PreparedStatement pst;
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, code);
			pst.setString(2, email);

			pst.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getcode(String email) {
		String recode ="";
		try {
			con = JDBCUtil.getConnection();
			String query = "SELECT code from customers where email=? ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				recode= rs.getString("code");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}



		return recode;
	}

	public Customer getCustomerById(int customerID) {
		// TODO Auto-generated method stub
//		List<Customer> list = new ArrayList<Customer>();
		Customer c =null;
		try {
			con = JDBCUtil.getConnection();
			String query = "select * from customers where customer_id =?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, customerID);
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
//				username,pass,email,numberphone,isadmin
				c = new Customer(rs.getString("username"),
						rs.getString("pass"),
						rs.getString("email"),
						rs.getString("numberphone"),
						rs.getInt("isadmin"));

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public void resetFailedAttempts(String username)  {

		String query = "UPDATE Customers SET failedAttempts = 0, lock_time = NULL WHERE username = ?";
		PreparedStatement pst;
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateFailedAttempts(String user, int failedAttempts) {
		String query = "UPDATE Customers SET failedAttempts = ? WHERE username = ?";
		PreparedStatement pst;
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(query);
			pst.setInt(1, failedAttempts);
			pst.setString(2, user);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lockAccount(String user) {
		String query ="UPDATE Customers SET lock_time = ? WHERE username = ?";
		PreparedStatement pst;
		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(query);
			pst.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			pst.setString(2, user);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public static void main(String[] args) {
		Customer c = new Customer("trang", "123");
//		System.out.println(CustomerDao.GetInstance().checkExistEmail("trungg161203@gmail.com"));
//		System.out.println(CustomerDao.GetInstance().getbyID(c));
		CustomerDao.GetInstance().updateCode("11","trungg161203@gmail.com");

	}

	public Customer getbyUsername(String username) {
		Customer c =null;
		try {
			con = JDBCUtil.getConnection();
			String query = "select * from customers where username =? ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,username );

			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
//				username,pass,email,numberphone,isadmin
				c = new Customer(	rs.getInt("customer_id"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getString("email"),
						rs.getString("numberphone"),
						rs.getInt("isadmin"),
						rs.getInt("failedAttempts"),
						rs.getTimestamp("lock_time")
				);

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
}

