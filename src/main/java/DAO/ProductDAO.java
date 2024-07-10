package DAO;
import java.sql.*;
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

    public static List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from products";
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {


                Category category = CategoryDAO.getCategoryByID(rs.getInt(7));
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
//                        rs.getInt(7),
                        category
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
//                        rs.getInt("quantity"),
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
//                        rs.getInt(7),
                        new Category(rs.getInt(1)));
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


            while (rs.next()) {
                return new Category(rs.getString(1), rs.getInt(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<Product> getListByPage(List<Product> listPro, int start, int end) {
        // TODO Auto-generated method stub
        ArrayList<Product> arr = new ArrayList<>();
        for(int i=start; i<end; i++) {
            arr.add(listPro.get(i));
        }
        return arr;
    }


    public static int insertProduct(Product pro) {
        String query ="insert into products(img, nameProduct, descriptionP, originalPrice, sellingPrice,categoryID) values(?,?,?,?,?,?)";
        int proID=0;
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, pro.getImg());
//            pst.setDate(2, dateorder);
            pst.setString(2, pro.getName());
            pst.setString(3, pro.getDescriptionP());
            pst.setDouble(4, pro.getOriginalPrice());
            pst.setDouble(5, pro.getSellingPrice());
            pst.setDouble(6, pro.getCategory().getQuantity());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                proID = generatedKeys.getInt(1);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }
        return proID;
    }

    public static void updateProduct(Product pro) {
        String query ="update products set img=?, nameProduct=?, descriptionP=?, originalPrice=?, sellingPrice=?,categoryID=? where productID=? ";
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, pro.getImg());
            pst.setString(2, pro.getName());
            pst.setString(3, pro.getDescriptionP());
            pst.setDouble(4, pro.getOriginalPrice());
            pst.setDouble(5, pro.getSellingPrice());
            pst.setDouble(6, pro.getCategory().getQuantity());
            pst.setInt(7, pro.getProductId());
            pst.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }

    }
    public static void deleteProduct(int id) {
        //xóa inventory trước vì dính ràng buộc
       InventoryDAO.deleteInventory(id);;

        String query ="delete from products where productID = ? ";
        try {
//			OrderLineDao.deleteOrderLine(id);
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }

    }
    public static Product getProductById(int id){
        String sql = "select * from Products where productID=?";
        try{
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
//                        rs.getInt(7),
                        new Category(rs.getInt(1)));
            }
        }catch (Exception e){

        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getProductById(1));
    }


}

