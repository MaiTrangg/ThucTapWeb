<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%@page isELIgnored="false" %>
 <c:import url="includes/head.jsp"></c:import> 
</head>
<body>
<%-- <c:import url="includes/navbar.jsp"></c:import> --%>
<div class="container">
<c:if test="${cus == null }">cus null</c:if>
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Edit Product</div>
			<div class="card-body">
				<form action="ad_editCusServlet" method="post" >
                            <div class="form-group">
                                <label>UserName</label>
                                <input type="text" name="username" class="form-control" value="${cus.username}" required>
                            </div>
                            <div class="form-group">
                                <label>isAmin(1 is admin)</label>
                                <input type="number" name="isadmin" class="form-control" value="${cus.isAdmin }" min="0" max="1" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" name="email" class="form-control" value="${cus.email }" required>
                            </div>
                            <div class="form-group">
                                <label>Numberphone</label>
                                <input type="tel" name="numberphone" class="form-control" value="${cus.numberPhone }" required>
                            </div>					
                        <div class="text-center">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
			</div>
		</div>
	</div>
	<%-- <c:import url="includes/footer.jsp"></c:import> --%>
</body>
</html>