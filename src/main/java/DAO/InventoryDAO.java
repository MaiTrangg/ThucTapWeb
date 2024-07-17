package DAO;

import DBConnection.JDBCUtil;
import Model.Category;
import Model.Inventory;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class InventoryDAO {
    private static Connection con ;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    public static List<Inventory> getAllInventoryPro() {
        List<Inventory> list = new ArrayList<>();
        String query = "select * from inventories";
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {


                Product p = ProductDAO.getProductByID(rs.getInt(2));
                list.add(new Inventory(
                        rs.getInt(1),
                        p,
                        rs.getInt(3),
                        rs.getTimestamp(4)
                ));
            }
            con.close();

//            con.close();
//            ps.close();
//            rs.close();

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

    public static void updateInventory(Inventory inventory){
        String query = "UPDATE inventories set quantity=?, lastUpdated=? where productID = ?";
        //update product
        ProductDAO.updateProduct(inventory.getProduct());

        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);

            ps.setDouble(1,inventory.getQuantity());
            ps.setTimestamp(2,inventory.getLastUpdated());
            ps.setInt(3,inventory.getProduct().getProductId());
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
    public static void deleteInventory(int idpro){
        String query ="delete from inventories where productID = ? ";

        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idpro);
            pst.executeUpdate();

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

    //lay so luong cua san pham co ma la idpro
    public static Inventory getInventoryPro(int idpro){
        Inventory inventory =null;
        String query ="select * from inventories where productID = ? ";

        try {
            con = new JDBCUtil().getConnection();
             ps = con.prepareStatement(query);
            ps.setInt(1, idpro);
           rs = ps.executeQuery();

           if(rs.next()) {
               inventory = new Inventory(rs.getInt("inventory_id"),
                       ProductDAO.getProductByID(rs.getInt("productID")),
                       rs.getInt("quantity"),
                       rs.getTimestamp("lastUpdated"));
           }

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
        return inventory;
    }
    public static List<Inventory> getProductsNotOrderedLast3Months() throws SQLException {
        Set<Integer> importedProducts = InventoryTransactionDAO.getIDProductsImportedLast3Months();
        Set<Integer> orderedProducts = OrderDao.getIDProductsOrderedLast3Months();
        List<Inventory> productNotOrdered = new ArrayList<>();

        importedProducts.removeAll(orderedProducts);
        for (int productId : importedProducts) {
            System.out.println(productId);
            productNotOrdered.add(getInventoryPro(productId));
        }

        return productNotOrdered;
    }
    public static List<Inventory> getProductsNeedImported(int min_stock, int max_stock) throws SQLException {
        List<Inventory> list = new ArrayList<>();
        String query = "select *, (?- quantity) as slCanNhap from inventories where quantity < ?  ";
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1,max_stock);
            ps.setInt(2,min_stock);
            rs = ps.executeQuery();
            while (rs.next()) {


                Product p = ProductDAO.getProductByID(rs.getInt(2));
                list.add(new Inventory(
                        rs.getInt(1),
                        p,
                        rs.getInt("slCanNhap"),
                        rs.getTimestamp(4)
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
//            System.out.println(getProductsImportedLast3Months().size());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        try {
            System.out.println(getProductsNeedImported(150,30));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
