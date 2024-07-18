package DAO;

import DBConnection.JDBCUtil;
import Model.Payment;
import Model.Product;

import java.sql.*;

public class PaymentDAO {

    private static Connection con ;
    private static  PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void insertPayment(Payment p) {
        String query ="insert into payments(paymentId, order_id, description, amount, date, accountNumber) values(?,?,?,?,?,?)";
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, p.getPaymentId());
            pst.setInt(2, p.getOrder().getOrderId());
            pst.setString(3, p.getDescription());
            pst.setDouble(4, p.getAmount());
            pst.setTimestamp(5, p.getDate());
            pst.setString(6, p.getAccountNumber());
            pst.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }
    }
}
