package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBConnection.JDBCUtil;
import Model.Category;
import Model.Product;

public class DigitalDAO {
    private static Connection con ;
    private static  PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static List<Product> getProductByName(String name) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT p.productID, p.img, p.nameProduct, p.descriptionP, " +
                "p.originalPrice, p.sellingPrice, c.nameCate " +
                "FROM store.products p " +
                "INNER JOIN store.categories c ON p.categoryID = c.categoryID " +
                "WHERE BINARY p.nameProduct LIKE ?";

        try {
            con = new JDBCUtil().getConnection();

            ps = con.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productID"),
                        rs.getString("img"),
                        rs.getString("nameProduct"),
                        rs.getString("descriptionP"),
                        rs.getDouble("originalPrice"),
                        rs.getDouble("sellingPrice"),
                        new Category(rs.getString("nameCate"))
                );
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return productList;
    }

    // Close resources
    private static void close() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println(getProductByName("Heo"));
    }

}
