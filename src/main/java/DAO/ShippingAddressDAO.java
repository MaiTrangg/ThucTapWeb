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
//    public static ShippingAddress getShippingAddressByOrderId(int order_id) {
//        String query = "SELECT * FROM shippingaddresses WHERE order_id = ?";
//        ShippingAddress shippingAddress = null;
//        try {
//            con = new JDBCUtil().getConnection();
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setInt(1, order_id);
//            rs = pst.executeQuery();
//            if (rs.next()) {
//                Integer shippingAddressId = rs.getInt("shippingAddress_id");
//                String province = rs.getString("province");
//                String district = rs.getString("district");
//                String commune = rs.getString("commune");
//                String country = rs.getString("country");
//                String noteAddress = rs.getString("noteAddress");
//                shippingAddress = new ShippingAddress(shippingAddressId ,province, district, commune, country, noteAddress);
//            }
//            con.close();
//            pst.close();
//        } catch (SQLException e) {
//            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
//            e.printStackTrace();
//        }
//        return shippingAddress;
//    }

    public static ShippingAddress getShippingAddressByOrderId(int order_id) {
        String query = "SELECT * FROM shippingaddresses WHERE order_id = ?";
        ShippingAddress shippingAddress = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = new JDBCUtil().getConnection();
            pst = con.prepareStatement(query);
            pst.setInt(1, order_id);
            rs = pst.executeQuery();

            if (rs.next()) {
                Integer shippingAddressId = rs.getInt("shippingAddress_id");
                String province = rs.getString("province");
                String district = rs.getString("district");
                String commune = rs.getString("commune");
                String country = rs.getString("country");
                String noteAddress = rs.getString("noteAddress");
                shippingAddress = new ShippingAddress(shippingAddressId, province, district, commune, country, noteAddress);
                System.out.println("Shipping address found: " + shippingAddress.toString());
            } else {
                System.out.println("No shipping address found for order_id: " + order_id);
            }
        } catch (SQLException e) {
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return shippingAddress;
    }



    public static void main(String[] args) {
        ShippingAddressDAO s = new ShippingAddressDAO();
        System.out.println(s.getShippingAddressByOrderId(2));
    }



}
