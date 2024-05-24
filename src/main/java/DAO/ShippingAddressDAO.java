package DAO;

import DBConnection.JDBCUtil;
import Model.ShippingAddress;
import Model.Transaction;

import java.sql.*;

public class ShippingAddressDAO {

        private static Connection con ;
        private static PreparedStatement ps = null;
        private static ResultSet rs = null;
        // l?u transaction v�o c? so d? li?u
        public static void insertShippingAddress(int order_id,ShippingAddress s) {
            String query ="insert into shippingaddresses(order_id, province,district " +
                    ", commune,country,noteAddress) values(?,?,?,?,?,?)";
            try {
                con = new JDBCUtil().getConnection();
                PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, order_id);
                pst.setString(2, s.getProvince());
                pst.setString(3, s.getDistrict());
                pst.setString(4, s.getCommune());
                pst.setString(5, s.getCountry());
                pst.setString(6, s.getNoteAddress());
                pst.executeUpdate();

                con.close();
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.err.println("?� x?y ra l?i khi thao t�c v?i c? s? d? li?u:");
                e.printStackTrace();
            }

        }
    // Lấy shipping address theo order_id
    public static ShippingAddress getShippingAddressByOrderId(int order_id) {
        String query = "SELECT * FROM shippingaddresses WHERE order_id = ?";
        ShippingAddress shippingAddress = null;
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, order_id);
            rs = pst.executeQuery();
            if (rs.next()) {
                Integer shippingAddressId = rs.getInt("shippingAddressId");
                String province = rs.getString("province");
                String district = rs.getString("district");
                String commune = rs.getString("commune");
                String country = rs.getString("country");
                String noteAddress = rs.getString("noteAddress");
                shippingAddress = new ShippingAddress(shippingAddressId ,province, district, commune, country, noteAddress);
            }
            con.close();
            pst.close();
        } catch (SQLException e) {
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }
        return shippingAddress;
    }
}
