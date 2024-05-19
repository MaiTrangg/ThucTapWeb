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

	public static void insertOrderLine(int order_id, int product_id, int quantity, double price) {
		String query = "INSERT INTO orderDetails(order_id, productID, quantity, price) VALUES(?,?,?,?)";
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(query);
			pst.setInt(1, order_id);
			pst.setInt(2, product_id);
			pst.setInt(3, quantity);
			pst.setDouble(4, price);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<OrderDetail> ListOrderLines(int order_id) {
		String query = "SELECT * FROM orderDetails WHERE order_id = ?";
		List<OrderDetail> orderLines = new ArrayList<OrderDetail>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(query);
			pst.setInt(1, order_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Product p = ProductDAO.getProductByID(rs.getInt("product_id"));
				orderLines.add(new OrderDetail(p, rs.getInt("quantity"), rs.getDouble("price")));
			}
		} catch (SQLException e) {
			System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderLines;
	}

	public static double revenue() {
		String query = "SELECT SUM(price) AS total FROM orderlines";
		double total = 0;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			if (rs.next()) {
				total = rs.getDouble("total");
			}
		} catch (SQLException e) {
			System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}

	public static int productsSale() {
		String query = "SELECT SUM(quantity) AS total FROM orderlines";
		int total = 0;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection();
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}
}
