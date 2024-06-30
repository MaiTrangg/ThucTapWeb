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
        ArrayList<Product> list2 = new ArrayList<Product>();
        String query = "SELECT p.productID, p.img, p.nameProduct, p.descriptionP, " +
                "p.originalPrice, p.sellingPrice, p.quantity, c.nameCate " +
                "FROM products p " +
                "INNER JOIN categories c ON p.categoryID = c.categoryID " +
                "WHERE BINARY p.nameProduct LIKE ?";
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            System.out.println(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                list2.add(new Product(
                        rs.getInt("productID"),
                        rs.getString("img"),
                        rs.getString("nameProduct"),
                        rs.getString("descriptionP"),
                        rs.getDouble("originalPrice"),
                        rs.getDouble("sellingPrice"),
//                        rs.getInt("quantity"),
                        new Category(rs.getString("nameCate"))
                ));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list2;
    }

    public static void main(String[] args) {

        System.out.println(getProductByName("Heo"));
    }

}
