package DAO;

import DBConnection.JDBCUtil;
import Model.Transaction;

import java.sql.*;

public class TransactionDAO {
    private static Connection con ;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    // l?u transaction v�o c? so d? li?u

    public static void insertTransaction(int order_id, int customer_id, Transaction t) {
        String query ="insert into transactions(order_id, customer_id,fullName , email_tran,numberPhone_tran,amount) values(?,?,?,?,?,?)";
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setDouble(1, order_id);
            pst.setInt(2, customer_id);
            pst.setString(3, t.getFullName());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getNumberPhone());
            pst.setDouble(6, t.getAmount());
            pst.executeUpdate();

            con.close();
            pst.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("?� x?y ra l?i khi thao t�c v?i c? s? d? li?u:");
            e.printStackTrace();
        }

    }
    // Lấy transaction theo order_id
    public static Transaction getTransactionByOrderId(int order_id) {
        String query = "SELECT * FROM transactions WHERE order_id = ?";
        Transaction transaction = null;
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, order_id);
            rs = pst.executeQuery();
            if (rs.next()) {
                int transactionId =rs.getInt("transactionId");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email_tran");
                String numberPhone = rs.getString("numberPhone_tran");
                double amount = rs.getDouble("amount");
                transaction = new Transaction(fullName, email, numberPhone, amount);
            }
            con.close();
            pst.close();
        } catch (SQLException e) {
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }
        return transaction;
    }
}
