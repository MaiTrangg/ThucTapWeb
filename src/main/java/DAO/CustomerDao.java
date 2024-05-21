package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
						rs.getInt("isadmin"));
				
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
	
	public String checkExistEmail(String email) {
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
	
//	public void updateCheckout(String fname, String lname, String country,String phone, String address,String username, String email) {
//		query = "Update customers set fristname =?, lastname = ?, country = ?, numberphone = ?, address = ? where username =? and email =?";
//		PreparedStatement pst;
//		try {
//			pst = con.prepareStatement(query);
//			pst.setString(1, fname);
//			pst.setString(2, lname);
//			pst.setString(3, country);
//			pst.setString(4, country);
//			pst.setString(5, address);
//			pst.setString(6, username);
//			pst.setString(7, email);
//
//			pst.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//	}
	
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

	public static void main(String[] args) {
		Customer c = new Customer("trang", "123");

//		System.out.println(CustomerDao.GetInstance().getbyID(c));
	}

}

