package DAO;

import DBConnection.JDBCUtil;
import Model.Category;
import Model.Inventory;
import Model.InventoryTransaction;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public static Set<Integer> getIDProductsImportedLast3Months() throws SQLException {
        Set<Integer> list = new HashSet<>();
        
        String query = "SELECT DISTINCT i.productID FROM store.inventorytransactions i " +
                "WHERE i.type = 'Nhập' " +
                "AND i.transactionDate >= DATE_SUB(NOW(), INTERVAL 3 MONTH) " +
                "AND i.productID NOT IN (SELECT i1.productID FROM store.inventorytransactions i1 WHERE i1.type = 'Xóa')";

        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("productID"));
            }



        } catch (SQLException e) {
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

        return list;
    }

    public static List<InventoryTransaction> getAllTransactionLast3Months() {
        List<InventoryTransaction> list = new ArrayList<>();
        String query = "select * from inventorytransactions where transactionDate >= DATE_SUB(NOW(), INTERVAL 3 MONTH)";
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            Product p =null;
            while (rs.next()) {

                p = ProductDAO.getProductByID(rs.getInt(2));

                list.add(new InventoryTransaction(
                        rs.getInt(1),
                        p,
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getTimestamp(5),
                        rs.getString(6)
                ));
            }
            con.close();

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
        return list;
    }



    public static void main(String[] args) {
//        try {
//            for(InventoryTransaction i :getAllTransactionLast3Months()){
//                System.out.println(i);
//            }
//        }
    }

}
