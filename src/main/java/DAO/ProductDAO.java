package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

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
    public static Product getProductByID(int id) {
        String query ="select * from products where productID = ? ";
        Product product = null;
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()) {
                product= new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getInt(7),new Category(rs.getInt(1)));
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }
        return product;
    }
    public static Category getCategory( int cid){
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "select * from categories where categoryID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cid);


            ResultSet rs = preparedStatement.executeQuery();
=======
    public static Product getProductByID(int id) {
        String query ="select * from products where productID = ? ";
        Product product = null;
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()) {
                Category cate = CategoryDAO.getCategoryByID(rs.getInt("categoryID"));
                product= new Product(rs.getInt("productID"),rs.getString("img")
                        , rs.getString("nameProduct"),rs.getString("descriptionP")
                        ,rs.getDouble("originalPrice"),rs.getDouble("sellingPrice")
                        ,rs.getInt("quantity"),cate);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }
        return product;
    }



            while (rs.next()) {
                return new Category(rs.getString(1), rs.getInt(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
//        ProductDAO pd = new ProductDAO;
       Product product = ProductDAO.getProductByID(1);
        System.out.println(product.toString());
/*
        Category c = getCategory(1);
        if (c != null) {
            System.out.println(c.toString());
        } else {
            System.out.println("Không tìm thấy sản phẩm có categoryID là 1");
        }
*/

    }
}

