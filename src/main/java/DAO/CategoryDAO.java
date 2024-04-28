package DAO;

import DBConnection.JDBCUtil;
import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            if(rs.next()) return category = new Category(rs.getString(2));


            con.close();

        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
        }
        return category;
    }




}

