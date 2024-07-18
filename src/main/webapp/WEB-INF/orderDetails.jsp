<%@ page import="Model.OrderDetail" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<div class="order-details">
    <h2>Chi tiết đơn hàng</h2>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Ảnh</th>
            <th scope="col">Mã sản phẩm</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Giá</th>
            <th scope="col">Thành tiền</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderDetails}" var="orderDetail">
            <tr>
                <td>
                    <div class="product-image">
                        <c:if test="${not empty orderDetail.product}">
                            <img src="${orderDetail.product.name}" class="img-fluid rounded-circle" style="width: 90px; height: 90px;" alt="Product Image">
                        </c:if>
                    </div>
                </td>
                <td>
                    <c:if test="${not empty orderDetail.product}">
                        ${orderDetail.product.productId}
                    </c:if>
                </td>
                <td>
                    <c:if test="${not empty orderDetail.product}">
                        ${orderDetail.product.img}
                    </c:if>
                </td>
                <td>${orderDetail.quantity}</td>
                <td><fmt:formatNumber value="${orderDetail.price}" pattern="#,##0'đ'" /></td>
                <td><fmt:formatNumber value="${orderDetail.price * orderDetail.quantity}" pattern="#,##0'đ'" /></td>


            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>


