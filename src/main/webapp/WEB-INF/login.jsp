<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 13-Mar-24
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <c:import url="includes/head.jsp"></c:import>
</head>
<body>
<!-- Spinner Start -->
<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status"></div>
</div>
<!-- Spinner End -->

<c:import url="includes/navbar.jsp">
    <c:param name="currentPage" value="${currentPage}" />
</c:import>

<!-- Model start -->
<div class="container-login">
    <div class="login-wrapper">
        <div class="login-card">
            <div class="card-header text-center">Đăng nhập tài khoản</div>
            <div class="card-body">
                <form action="loginServlet" method="post">
                    <div class="form-group">
                        <label>Tên khách hàng</label>
                        <input type="text" name="login-username" class="form-control" placeholder="Nhập tên khách hàng">
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" name="login-password" class="form-control" placeholder="Nhập mật khẩu">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary" >Đăng nhập</button>
                        <label class="login-loginwith">Đăng nhập với</label><br>
                        <a href="https://www.facebook.com/v20.0/dialog/oauth?client_id=332128406636362&redirect_uri=http://localhost:8080/LoginFacebookControll" class="login-facebook"><i class="fab fa-facebook"></i> </a>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/LoginGoogleHandler&response_type=code&client_id=103711909118-kj61sqe0bv8srccvmk7tire0ih1oi87o.apps.googleusercontent.com" class="login-google"><i class="fab fa-google"></i></a>
                        <br> <a href="register" class="login-register">Tạo tài khoản mới</a>
                        <a href="forgotpassword"  class="login-forgotpass" style="margin-left: 20px;">Quên mật khẩu</a>
                        <p style="color: #e21818">${error}</p>

                    </div>






                </form>
            </div>
        </div>
    </div>
</div>

<!-- Model end -->

<c:import url="includes/navbar.jsp">
    <c:param name="currentPage" value="${currentPage}" />
</c:import>

<!-- Start Footer Section -->
<c:import url="includes/footer.jsp"></c:import>
<!-- End Footer Section -->

<!-- Copyright Start -->
<div class="container-fluid copyright bg-dark py-4">
    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                <span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
            </div>
            <div class="col-md-6 my-auto text-center text-md-end text-white">
                <!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
                <!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
                <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a> Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
            </div>
        </div>
    </div>
</div>
<!-- Copyright End -->



<!-- Back to Top -->
<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>

<!-- JavaScript Libraries -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/lightbox/js/lightbox.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="../js/main.js"></script>

</body>
</html>
