package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBConnection.JDBCUtil;
import Model.Customer;
import Model.Order;
import Model.OrderDetail;

public class OrderDao {
    private static Connection con ;
    private static  PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static int insertOrder(String username, String email, Date dateorder) {
        String query ="insert into orders(username, email, dateorder) values(?,?,?)";
        int orderID=0;
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setDate(3, dateorder);
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderID = generatedKeys.getInt(1);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }
        return orderID;
    }

    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        String query ="select * from orders ";

        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()) {
                Customer c= CustomerDao.GetInstance().getbyID(rs.getString("username"), rs.getString("email"));
                if(c==null) {
                    c = new Customer();
                    c.setEmail(rs.getString("email"));
                    c.setUsername(rs.getString("username"));
                }

                System.out.println(c);
                List<OrderDetail> orderLines = OrderDetailDAO.ListOrderLines(rs.getInt("order_id"));
                java.util.Date  dateOrder = new java.util.Date(rs.getDate("dateorder").getTime());
                orders.add(new Order(rs.getInt("order_id"), c, dateOrder, orderLines,rs.getDouble("totalMoney")));
            }

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }

        return orders;
    }
    public static void main(String[] args) {
        System.out.println("---------------------------------------------id"+insertOrder("nhan","nhan@gmail.com",new Date(new java.util.Date().getTime())));
    }
}
