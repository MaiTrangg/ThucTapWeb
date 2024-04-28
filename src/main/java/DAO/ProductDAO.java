package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBConnection.JDBCUtil;
import Model.Category;
import Model.Product;
public class ProductDAO {
    private static Connection con ;
    private static  PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>();
        String query = "select * from products";
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {


                Category category= CategoryDAO.getCategoryByID( rs.getInt(8));
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getInt(7),
                        category
                ));
            }
            con.close();

        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
        }
        return list;
    }

    public static List<Category> countProByCategory(){
        List<Category> list = new ArrayList<>();
        String query = "select   categories.nameCate, count(productID) soluong\n" +
                "from products, categories\n" +
                "where products.categoryID =categories.categoryID\n" +
                "group by products.categoryID";
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {

                list.add(new Category(

                        rs.getString("nameCate"),
                        rs.getInt("soluong")
                ));
            }
            con.close();

        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
        }
        return list;
    }

    public static List<Product> getProductByCategory(String nameCate){
        List<Product> list = new ArrayList<>();
        String query = "select  *\n" +
                "from products, categories\n" +
                "where products.categoryID =categories.categoryID and categories.nameCate = ?\n";
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1,nameCate);
            rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Product(
                        rs.getInt("productID"),
                        rs.getString("img"),
                        rs.getString("nameProduct"),
                        rs.getString("descriptionP"),
                        rs.getDouble("originalPrice"),
                        rs.getDouble("sellingPrice"),
                        rs.getInt("quantity"),
                        new Category(rs.getString("nameCate"))
                ));
            }
            con.close();

        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
        }
        return list;
    }

    public List<Product> getListByPage(List<Product> listPro, int start, int end) {
        // TODO Auto-generated method stub
        ArrayList<Product> arr = new ArrayList<>();
        for(int i=start; i<end; i++) {
            arr.add(listPro.get(i));
        }
        return arr;
    }

}