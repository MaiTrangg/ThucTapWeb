<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<c:import url="includes/head.jsp"></c:import>
	<%@page isELIgnored="false" %>
</head>
<body>
<%-- <c:import url="includes/navbar.jsp"></c:import> --%>
<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Change Password</div>
			<div class="card-body">
				<form action="changePassServlet" method="post">
					<div class="form-group">
						<label>Mật khẩu mới</label>
						<input type="password" name="newpass" class="form-control" placeholder="Enter here" required>
					</div>
					<div class="form-group">
						<label> Nhập lại mật khẩu mới</label>
						<input type="password" name="confirmnewpass" class="form-control" placeholder="Enter here" required>
					</div>
					<p class="error">${errorFromChangePass}</p>
					 <div class="text-center">
						<button type="submit" class="btn btn-primary" >Submit</button>
						<!-- <br> <a href="login.jsp" > login</a>
						 <a href="register.jsp"  style="margin-left: 20px;"> create an acount</a> -->
					</div> 
					
				</form>
			</div>
		</div>
	</div>
	<c:import url="includes/footer.jsp"></c:import>
</body>
</html>