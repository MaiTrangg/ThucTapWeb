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
import Model.ShippingAddress;
import Model.Transaction;

public class OrderDao {
    private static Connection con ;
    private static  PreparedStatement ps = null;
    private static ResultSet rs = null;
// lưu order vào cơ so dữ liệu và return về id của order vừa lưu
    public static int insertOrder(double totalMoney, Date dateorder, String statusOrder, String noteOrder) {
        String query ="insert into orders(totalMoney, dateorder, statusOrder, noteOrder) values(?,?,?,?)";
        int orderID=0;
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setDouble(1, totalMoney);
            pst.setDate(2, dateorder);
            pst.setString(3, statusOrder);
            pst.setString(4, noteOrder);
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
//            while(rs.next()) {
//                Customer c= CustomerDao.GetInstance().getbyID(rs.getString("username"), rs.getString("email"));
//                if(c==null) {
//                    c = new Customer();
//                    c.setEmail(rs.getString("email"));
//                    c.setUsername(rs.getString("username"));
//                }
//
//                System.out.println(c);
//                List<OrderDetail> orderLines = OrderDetailDAO.ListOrderLines(rs.getInt("order_id"));
//                java.util.Date  dateOrder = new java.util.Date(rs.getDate("dateorder").getTime());
//                orders.add(new Order(rs.getInt("order_id"), c, dateOrder, orderLines,rs.getDouble("totalMoney")));
//            }
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                double totalMoney = rs.getDouble("totalMoney");
                Date dateOrder = rs.getDate("dateorder");
                String statusOrder = rs.getString("statusOrder");
                String noteOrder = rs.getString("noteOrder");

                // Giả sử bạn có các phương thức để lấy Transaction và ShippingAddress theo orderId
                Transaction transaction = TransactionDAO.getTransactionByOrderId(orderId);
                ShippingAddress shippingAddress = ShippingAddressDAO.getShippingAddressByOrderId(orderId);

                List<OrderDetail> orderDetails = OrderDetailDAO.ListOrderLines(orderId);

                Order order = new Order(orderId, transaction, shippingAddress, dateOrder, orderDetails, totalMoney, statusOrder, noteOrder);
                orders.add(order);
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
    }
}

