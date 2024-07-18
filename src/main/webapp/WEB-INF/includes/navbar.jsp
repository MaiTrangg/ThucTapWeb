<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 01-Mar-24
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<div class="container-fluid fixed-top">
    <div class="container topbar bg-primary d-none d-lg-block">
        <div class="d-flex justify-content-between">
            <div class="top-info ps-2">
                <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">Phường Linh Trung, TP Thủ Đức</a></small>
                <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white">Email@Example.com</a></small>
            </div>
            <div class="top-link pe-2">
                <a href="#" class="text-white"><small class="text-white mx-2">Chính sách bảo mật</small>/</a>
                <a href="#" class="text-white"><small class="text-white mx-2">Điều khoản sử dụng</small>/</a>
                <a href="#" class="text-white"><small class="text-white ms-2">Bán hàng và Hoàn trả</small></a>
            </div>
        </div>
    </div>
    <div class="container px-0">
        <nav class="navbar navbar-light bg-white navbar-expand-xl">
            <a href="index.jsp" class="navbar-brand"><h1 class="text-primary display-6">Box Bites</h1></a>
            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars text-primary"></span>
            </button>
            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                <c:set var="currentPage" value="${param.currentPage}"></c:set>
<%--                <c:out value="${currentPage}"/>--%>

                <div class="navbar-nav mx-auto">
                    <a href="index" class="nav-item nav-link ${currentPage eq 'index' ? 'active' : ''}" >Trang chủ</a>
                    <a href="#" class="nav-item nav-link ${currentPage eq 'shop' ? 'active' : ''}" id="item-shop">Cửa hàng</a>
<%--                    <a href="shopDetail" class="nav-item nav-link ${currentPage eq 'shopDetail' ? 'active' : ''}">Chi tiết sản phẩm</a>--%>
                    <div class="nav-item dropdown">



                       <c:if test="${currentPage eq 'cart' || currentPage eq 'checkout'|| currentPage eq 'testimonial'}">
                           <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">Thanh toán sản phẩm</a>
                       </c:if>
                        <c:if test="${currentPage ne 'cart' && currentPage ne 'checkout'&& currentPage ne 'testimonial'}">
                            <a href="#" class="nav-link dropdown-toggle " data-bs-toggle="dropdown">Thanh toán sản phẩm</a>
                        </c:if>

                        <div class="dropdown-menu m-0 bg-secondary rounded-0">
                            <a href="cart" class="dropdown-item ${currentPage eq 'cart' ? 'active' : ''}">Giỏ hàng</a>
                            <a href="checkout" class="dropdown-item ${currentPage eq 'checkout' ? 'active' : ''}">Thanh toán</a>
                            <a href="testimonial" class="dropdown-item ${currentPage eq 'testimonial' ? 'active' : ''}">Đánh giá</a>
                            <%-- <a href="404.html" class="dropdown-item">404 Page</a> --%>
                        </div>
                    </div>
                    <a href="contact" class="nav-item nav-link ${currentPage eq 'contact' ? 'active' : ''}">Liên hệ</a>
                    <c:if test="${sessionScope.customer.isAdmin == 1}">
                        <a href="./index_adServlet">
                            <b>Quản lý</b>
                        </a>
                    </c:if>

                </div>
                <div class="d-flex m-3 me-0">
                    <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fas fa-search text-primary"></i></button>
                    <a href="cart" class="position-relative me-4 my-auto">
                        <i class="fa fa-shopping-bag fa-2x"></i>
                        <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;" id="quantityCart">
                            <c:if test="${order.orderLines.size() == null }">0</c:if>
		                    ${ order.orderDetails.size()}
                        </span>
                    </a>




                    <c:if test="${customer != null}">
                        <div class="my-auto dropdown">
                            <a class="dropdown-toggle" id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                chao ${customer.username}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                                <li><a class="dropdown-item" href="profile">Tài khoản của tôi</a></li>
                                <li><a class="dropdown-item" href="userOrdersServlet">Đơn hàng của tôi</a></li>
                                <li><a class="dropdown-item" href="changePass">Đổi mật khẩu</a></li>
                                <li><a class="dropdown-item" href="logoutServlet">Đăng xuất</a></li>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${customer == null}">
                        <a href="login" class="my-auto">
                            <i class="fas fa-user fa-2x"></i>
                        </a>
                    </c:if>


                </div>
            </div>
        </nav>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


<script>
    document.addEventListener("DOMContentLoaded", function() {
        function updatePageShop(response){
            console.log("da vao updatePageShop");
            $('#content-pro').html(response);
            window.location.href = "/shop";// sau khi xử lí ajax xong sẽ được chuyển hướng sang trang shop.jsp
        }

        $(document).ready(function (){
            $(document).on("click", "#item-shop", function(e) {
                e.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "/shopServlet",
                    // data:{
                    //
                    // } ,
                    success: function(response) {
                        console.log("da vao success");
                        updateProInShop(response);
                    },
                    error: function(xhr,error) {
                        // Xử lý lỗi nếu có
                        console.error("Lỗi khi cập nhật giá:", error);
                    }
                });
            });

            function updateProInShop(response){
                console.log("da vao updateProInShop");
                updatePageShop(response);
            }

        });
    });
    function updatePage(respone){
        var qc = respone.quantityCart;
        $('#quantityCart').text(qc);

    }

</script>

