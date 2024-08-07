package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DBConnection.JDBCUtil;
import Model.*;

public class OrderDao {
    private static Connection con;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    // lưu order vào cơ so dữ liệu và return về id của order vừa lưu
    public static int insertOrder(double totalMoney, Timestamp dateorder, String statusOrder, String noteOrder) {
        String query = "insert into orders(totalMoney, dateorder, statusOrder, noteOrder) values(?,?,?,?)";
        int orderID = 0;
        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setDouble(1, totalMoney);
//            pst.setDate(2, dateorder);
            pst.setTimestamp(2, dateorder);
            pst.setString(3, statusOrder);
            pst.setString(4, noteOrder);
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderID = generatedKeys.getInt(1);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }
        return orderID;
    }

    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        String query = "select * from orders ";

        try {
            con = new JDBCUtil().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            rs = pst.executeQuery();
//            while(rs.next()) {
//                Customer c= CustomerDao.GetInstance().getbyID(rs.getString("username"), rs.getString("email"));
//                if(c==null) {
//                    c = new Customer();
//                    c.setEmail(rs.getString("email"));
//                    c.setUsername(rs.getString("username"));
//                }
//
//                System.out.println(c);
//                List<OrderDetail> orderLines = OrderDetailDAO.ListOrderLines(rs.getInt("order_id"));
//                java.util.Date  dateOrder = new java.util.Date(rs.getDate("dateorder").getTime());
//                orders.add(new Order(rs.getInt("order_id"), c, dateOrder, orderLines,rs.getDouble("totalMoney")));
//            }
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                double totalMoney = rs.getDouble("totalMoney");
//                Date dateOrder = rs.getDate("dateorder");
                Timestamp dateOrder = rs.getTimestamp("dateorder");
                String statusOrder = rs.getString("statusOrder");
                String noteOrder = rs.getString("noteOrder");

                // Giả sử bạn có các phương thức để lấy Transaction và ShippingAddress theo orderId
                Transaction transaction = TransactionDAO.getTransactionByOrderId(orderId);
                ShippingAddress shippingAddress = ShippingAddressDAO.getShippingAddressByOrderId(orderId);

                List<OrderDetail> orderDetails = OrderDetailDAO.ListOrderLines(orderId);

                Order order = new Order(orderId, transaction, shippingAddress, dateOrder, orderDetails, totalMoney, statusOrder, noteOrder);
                orders.add(order);
            }

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("Đã xảy ra lỗi khi thao tác với cơ sở dữ liệu:");
            e.printStackTrace();
        }

        return orders;
    }

//    public static void updateOrderStatus(int orderId, String statusOrder) {
//
//        try (Connection con = new JDBCUtil().getConnection();
//             PreparedStatement pst = con.prepareStatement(query)) {{
//
//            String sql = "UPDATE orders SET statusOrder = ? WHERE order_id = ?";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, statusOrder);
//            ps.setInt(2, orderId);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
public static void updateOrderStatus(int orderId, String statusOrder) {
    String sql = "UPDATE orders SET statusOrder = ? WHERE order_id = ?";
    try (Connection con = new JDBCUtil().getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, statusOrder);
        pst.setInt(2, orderId);
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected == 0) {
            System.err.println("Không cập nhật được đơn hàng với ID: " + orderId);
        }

    } catch (SQLException e) {
        System.err.println("Lỗi khi cập nhật trạng thái đơn hàng: " + e.getMessage());
    }
}




//    public static List<Order> getOrdersByCustomerId(int customerId) {
//        List<Order> orders = new ArrayList<>();
//        String query = "SELECT o.order_id, o.totalMoney, o.dateorder, o.statusOrder, o.noteOrder " +
//                "FROM orders o " +
//                "JOIN transactions t ON o.order_id = t.order_id " +
//                "WHERE t.customer_id = ?";
//
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            con = new JDBCUtil().getConnection();
//            ps = con.prepareStatement(query);
//            ps.setInt(1, customerId);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                int orderId = rs.getInt("order_id");
//                double totalMoney = rs.getDouble("totalMoney");
//                Timestamp dateOrder = rs.getTimestamp("dateorder");
//                String statusOrder = rs.getString("statusOrder");
//                String noteOrder = rs.getString("noteOrder");
//
//                // Retrieve transaction and shipping address details
//                Transaction transaction = TransactionDAO.getTransactionByOrderId(orderId);
//                ShippingAddress shippingAddress = ShippingAddressDAO.getShippingAddressByOrderId(orderId);
//                List<OrderDetail> orderDetails = OrderDetailDAO.ListOrderLines(orderId);
//
//                Order order = new Order(orderId, transaction, shippingAddress, dateOrder, orderDetails, totalMoney, statusOrder, noteOrder);
//                orders.add(order);
//            }
//        } catch (SQLException e) {
//            System.err.println("Database operation error:");
//            e.printStackTrace();
//        } finally {
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return orders;
//    }


    public static Set<Integer> getIDProductsOrderedLast3Months() throws SQLException {
        Set<Integer> list = new HashSet<>();
        //lấy tất cả id product có đơn hàng trừ trang thái hủy
        String query = "SELECT DISTINCT productID " +
                "FROM orderDetails " +
                "WHERE order_id IN ( " +
                "    SELECT order_id " +
                "    FROM orders " +
                "    WHERE dateorder >= DATE_SUB(NOW(), INTERVAL 3 MONTH) " +
                "    AND statusOrder != 'Hủy'" +
                ")";

        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("productID"));
            }


        } catch (SQLException e) {
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


    public static List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT o.order_id, o.totalMoney, o.dateorder, o.statusOrder, o.noteOrder " +
                "FROM orders o " +
                "JOIN transactions t ON o.order_id = t.order_id " +
                "WHERE t.customer_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                double totalMoney = rs.getDouble("totalMoney");
                Timestamp dateOrder = rs.getTimestamp("dateorder");
                String statusOrder = rs.getString("statusOrder");
                String noteOrder = rs.getString("noteOrder");

                // Retrieve transaction and shipping address details
                Transaction transaction = TransactionDAO.getTransactionByOrderId(orderId);
                ShippingAddress shippingAddress = ShippingAddressDAO.getShippingAddressByOrderId(orderId);
                List<OrderDetail> orderDetails = OrderDetailDAO.ListOrderLines(orderId);

                Order order = new Order(orderId, transaction, shippingAddress, dateOrder, orderDetails, totalMoney, statusOrder, noteOrder);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Database operation error:");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }


    public static void main(String[] args) {
        List<Order> orders = getAllOrders();
        if (orders == null || orders.isEmpty()) {
            System.out.println("Không có đơn hàng nào.");
        } else {
            System.out.println("Danh sách đơn hàng:");
            for (Order order : orders) {
                System.out.println(order);
            }
        }

    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT od.orderDetail_id, od.productID, p.nameProduct AS productName, p.img AS productImage, od.quantity, od.price " +
                "FROM orderDetails od " +
                "JOIN store.products p ON od.productID = p.productID " +
                "WHERE od.order_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new JDBCUtil().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int orderDetailId = rs.getInt("orderDetail_id");
                int productId = rs.getInt("productID");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                // Tạo đối tượng Product từ thông tin đã truy vấn
                Product product = new Product(productId, productName, productImage);

                // Tạo đối tượng OrderDetail từ thông tin đã truy vấn
                OrderDetail orderDetail = new OrderDetail(orderDetailId, product, quantity, price);
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            System.err.println("Database operation error:");
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return orderDetails;
    }

}







            