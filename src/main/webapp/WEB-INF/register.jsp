<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 24-Mar-24
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <c:import url="includes/head.jsp"></c:import>
</head>
<style>
    .input-password{
        display: flex;
        justify-content: space-between;
    }
    .none-border{
        border-radius: 0 ;
        border: none;
        margin-top: 5px;
    }
    .none-border:hover {
        background: white;
    }
    .input-border{
        border-radius: 10px 0 0 10px;
    }
    .input-group-append{
        border-radius: 0 10px 10px 0;
        background: white;
        border: 1px solid #ffb524;
    }
    /*.input-group-append:hover{*/
    /*    background:#ffb524 ;*/
    /*}*/


</style>
<body>

<!-- Spinner Start -->
<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status"></div>
</div>
<!-- Spinner End -->

<c:import url="includes/navbar.jsp">
    <c:param name="currentPage" value="${currentPage}" />
</c:import>


<div class="container-register">
    <div class="register-wrapper">
        <div class="register-card">
        <div class="card-header text-center">Đăng kí tài khoản</div>
        <div class="card-body">
            <form action="registerServerlet" method="post">
                <div class="form-group">
                    <label>Tên khách hàng</label>
                    <input type="text" name="register-username" class="form-control" placeholder="Nhập tên khách hàng" value="${username}">
                    <p class="error">${errorFromRegister} </p>
                </div>
                <div class="form-group">
                    <label>Mật khẩu</label>
                    <p>(*)Mật khẩu phải chứa ít nhất 1 kí tự viết hoa, 1 kí tự đặc biệt, 1 số và độ dài từ 8 trở lên </p>
                    <div class="input-password">
                        <input type="password" id="password" name="register-password" class="form-control input-border" placeholder="Nhập mật khẩu" value="${pass}">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary none-border" type="button" id="btnPassword">
                                <span class="fas fa-eye-slash" id="eyeIcon"></span>
                            </button>
                        </div>
                    </div>
                    <p id="passwordFeedback"></p>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="register-email" class="form-control" placeholder="Nhập email" value="${email}">
                </div>
                <div class="form-group">
                    <label>Số điện thoại</label>
                    <input type="tel" name="register-numberphone" class="form-control" placeholder="Nhập số điện thoại" value="${numberphone}">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary" >Đăng kí</button><br>
                    <label class="register-ti">Hoặc bạn có thể</label><br>
                    <a href="login" class="register-login">Đăng nhập tài khoản</a>
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
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var passwordInput = document.getElementById('password');
        var eyeIcon = document.getElementById('eyeIcon');
        var btnPassword = document.getElementById('btnPassword');
        var isPasswordVisible = false;

        btnPassword.addEventListener('click', function() {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                passwordInput.type = 'text';
                eyeIcon.className = 'fas fa-eye'; // Thêm lớp cho biểu tượng
            } else {
                passwordInput.type = 'password';
                eyeIcon.className = 'fas fa-eye-slash'; // Thêm lớp cho biểu tượng
            }
        });
    });

</script>

<script>
    window.onload = function() {
        var passwordInput = document.getElementById("password");
        var feedbackElement = document.getElementById("passwordFeedback");

        passwordInput.addEventListener("input", function() {
            var matKhau = passwordInput.value;

            // Kiểm tra độ dài mật khẩu
            var lengthOk = matKhau.length >= 8;
            var containsUppercase = /[A-Z]/.test(matKhau);
            var containsSpecialCharAndNumber = /(?=.*[!@#$%^&*()_+=\-\\|[\]{};:'",<.>\/?])(?=.*[0-9])/.test(matKhau);

            // Hiển thị phản hồi cho người dùng
            if (lengthOk && containsUppercase && containsSpecialCharAndNumber) {
                feedbackElement.innerHTML = "Mật khẩu mạnh";
                feedbackElement.style.color = "green";
            } else {
                feedbackElement.innerHTML = "Mật khẩu yếu";
                feedbackElement.style.color = "red";
            }
        });
    };
</script>



</body>

</html>
