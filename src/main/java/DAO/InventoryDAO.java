package DAO;

import DBConnection.JDBCUtil;
import Model.Category;
import Model.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private static Connection con ;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void insertInventory(Inventory inventory){
        String query = "insert into inventories  (productID,quantity, lastUpdated ) values(?,?,?)";


        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1,inventory.getProduct().getProductId());
            ps.setDouble(2,inventory.getQuantity());
            ps.setTimestamp(3,inventory.getLastUpdated());
            ps.executeUpdate();

            con.close();
            ps.close();
            rs.close();

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
