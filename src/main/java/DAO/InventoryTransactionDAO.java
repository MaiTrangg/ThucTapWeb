package DAO;

import DBConnection.JDBCUtil;
import Model.Inventory;
import Model.Product;

import java.sql.*;

public class InventoryTransactionDAO {

    private static Connection con ;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;


    public static void insertInventoryTransaction(int idpro, String type, int quantity, Timestamp transactionDate,String description){
        String query = "insert into inventoryTransactions  (productID,type ,quantity, transactionDate,description_inven ) values(?,?,?,?,?)";
        System.out.println("description: "+description);

        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1,idpro);
            ps.setString(2,type);
            ps.setInt(3,quantity);
            ps.setTimestamp(4,transactionDate);
            ps.setString(5,description);
            ps.executeUpdate();

            con.close();
            ps.close();

        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Đã xảy ra lỗi khi đóng kết nối: " + e.getMessage());
            }
        }

    }

}
