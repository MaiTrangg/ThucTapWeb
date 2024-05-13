package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnection.JDBCUtil;
import Model.OrderDetail;
import Model.Product;

public class OrderDetailDAO {

	private static Connection con ;
	private static  PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	public static void insertOrderLine(int order_id, int product_id, int quantity, double price) {
		String query ="insert into orderlines(order_id, product_id, quantity, price) values(?,?,?,?)";
		try {
			con = new JDBCUtil().getConnection();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, order_id);
			pst.setInt(2, product_id);
			pst.setInt(3, quantity);
			pst.setDouble(4, price );
			pst.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
			e.printStackTrace();
		}
	}
	
//	public static void deleteOrderLine(int id) {
//		String query ="delete from orderlines where product_id = ? ";
//		try {
//			con = new JDBCUtil().getConnection();
//			PreparedStatement pst = con.prepareStatement(query);
//			pst.setInt(1, id);
//			pst.executeUpdate();
//			con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			 System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
//			e.printStackTrace();
//		}
//	}
	public static List<OrderDetail> ListOrderLines(int order_id) {
		String query ="select * from orderlines where order_id = ? ";
		List<OrderDetail> orderLines = new ArrayList<OrderDetail>();
		try {
			con = new JDBCUtil().getConnection();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, order_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product p = ProductDAO.getProductByID(rs.getInt("product_id"));
				orderLines.add(new OrderDetail(p, rs.getInt("quantity"), rs.getDouble("price")));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
			e.printStackTrace();
		}
		return orderLines;
	}
	
	public static double revenue() {
		String query ="select sum(price) as total from orderlines ";
		double total=0;
		try {
			con = new JDBCUtil().getConnection();
			PreparedStatement pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			if(rs.next()) total=rs.getDouble("total");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
			e.printStackTrace();
		}
		return total;
	}
	public static int productsSale() {
		String query ="select sum(quantity) as total from orderlines ";
		int total=0;
		try {
			con = new JDBCUtil().getConnection();
			PreparedStatement pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			if(rs.next()) total=rs.getInt("total");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
			e.printStackTrace();
		}
		return total;
	}
}
