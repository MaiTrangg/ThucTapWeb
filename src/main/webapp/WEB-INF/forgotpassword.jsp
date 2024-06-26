<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 28-Mar-24
  Time: 8:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


<div class="forgotpass-container">
    <div class="forgot-wrapper">
        <div class="forgot-card">
        <div class="card-header text-center">Nhập địa chỉ email của bạn</div>
        <div class="card-body">
            <form action="forgotpasswordServerlet" method="post">
                <div class="form-group">
                    <label>Địa chỉ Email</label>
                    <input type="email" name="forgotpassword-email" class="form-control" placeholder="Nhập email">
                </div>
<%--                <p class="error"">${errorFromForgotpassword}</p>--%>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary" >Gửi yêu cầu</button><br>
                    <a href="login" class="forgot-login"> Đăng nhập tài khoản</a>
                    <a href="register" class="forgot-register"> Tạo tài khoản</a>
                </div>
            </form>
        </div>
        </div>
    </div>
</div>


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
