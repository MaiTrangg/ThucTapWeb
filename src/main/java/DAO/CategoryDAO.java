package DAO;

import DBConnection.JDBCUtil;
import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static Connection con ;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static Category getCategoryByID(int id){

        /**
         * lấy ra loại sản phẩm dựa trên id loại sản phẩm trong product
         * @param int id
         * @return Category
         */
        String query = "select * from categories where categoryID =?";
        Category category = null;
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);

            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()) return category = new Category(rs.getString(2),id);


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
        return category;
    }
    public List<Category> getListCategory(){
        String query = "select * from categories";
        List<Category> list = new ArrayList<>();
        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);


            rs = ps.executeQuery();
            while(rs.next()) list.add(new Category(rs.getString("nameCate"), rs.getInt("categoryID")));


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
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new CategoryDAO().getListCategory());
    }




}

