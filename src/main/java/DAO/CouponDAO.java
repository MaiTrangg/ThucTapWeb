package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DBConnection.JDBCUtil;
import Model.Coupon;

public class CouponDAO {
    public void insertCoupon(Coupon coupon) {
        String query = "INSERT INTO coupon (code, discount_type, discount_value, max_discount_value, min_total_value) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, coupon.getCode());
            ps.setString(2, coupon.getDiscountType());
            ps.setBigDecimal(3, coupon.getDiscountValue());
            ps.setBigDecimal(4, coupon.getMaxDiscountValue());
            ps.setBigDecimal(5, coupon.getMinTotalValue());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        String query = "SELECT * FROM coupon";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Coupon coupon = new Coupon();
//                coupon.setId(rs.getInt("id"));
                coupon.setCode(rs.getString("code"));
                coupon.setDiscountType(rs.getString("discount_type"));
                coupon.setDiscountValue(rs.getBigDecimal("discount_value"));
                coupon.setMaxDiscountValue(rs.getBigDecimal("max_discount_value"));
                coupon.setMinTotalValue(rs.getBigDecimal("min_total_value"));
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coupons;
    }
    public boolean hasUserUsedCoupon(int customerId, int couponId) {
        String query = "SELECT COUNT(*) FROM customer_coupons WHERE customer_id = ? AND coupon_id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ps.setInt(2, couponId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void recordCouponUsage(int customerId, int couponId) {
        String query = "INSERT INTO customer_coupons (customer_id, coupon_id) VALUES (?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ps.setInt(2, couponId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Coupon getCouponById(int couponId) {
        Coupon coupon = null;
        String sql = "SELECT * FROM coupon WHERE coupon_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, couponId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    coupon = new Coupon();
                    coupon.setId(resultSet.getInt("coupon_id"));
                    coupon.setCode(resultSet.getString("code"));
                    coupon.setDiscountType(resultSet.getString("discount_type"));
                    coupon.setDiscountValue(resultSet.getBigDecimal("discount_value"));
                    coupon.setMaxDiscountValue(resultSet.getBigDecimal("max_discount_value"));
                    coupon.setMinTotalValue(resultSet.getBigDecimal("min_total_value"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coupon;
    }
    public void insertSampleCoupons() {
          insertCoupon(new Coupon("DISCOUNT40", "percentage", new BigDecimal("40"), new BigDecimal("40000"), new BigDecimal("100000")));
    }


}
