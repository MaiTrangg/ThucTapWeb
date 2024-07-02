<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<c:import url="includes/head.jsp"></c:import>
	<%@page isELIgnored="false" %>

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
<div style="margin-top: 200px" class="container">
		<div class="card w-50 mx-auto my-5">
			 <!-- <div class="card-header text-center"><p style="font-size: 30px; color: green;"> Is susessful!</p></div>  -->
			<div class="card-body">
				<!-- <form action="enterCodeServerlet" method="post"> -->
					 <div class="form-group text-center" >
					
						<img alt="image" src="img/success.gif" style="width: 450px; height: 400px;" >
					</div>
					<%-- <p class="errorFromEnterCode">${errorFromForgotpassword}</p> --%>
					<div class="text-center">
						<!-- <button type="submit" class="btn btn-primary" >Submit</button> -->
						<br> <a href="index" >Tiếp Tục Đăng Nhập</a>
						 <a href="changePass"  style="margin-left: 20px;"> Đổi Mật Khẩu</a>
					</div>
					
				<!-- </form> -->
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