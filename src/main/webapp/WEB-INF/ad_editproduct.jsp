<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <c:import url="includes/head.jsp"></c:import>
</head>
<body>
<%-- <c:import url="includes/navbar.jsp"></c:import> --%>
<div class="container">
<c:if test="${pro == null }">pro null</c:if>
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Edit Product</div>
			<div class="card-body">
				<form action="ad_editproServlet" method="post" enctype="multipart/form-data"  >
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" name="name" class="form-control" value="${pro.name }" required>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input type="file" name="img" class="form-control" value="${pro.img }" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input type="number" name="price" class="form-control" value="${pro.price }" required>
                            </div>
                            <div class="form-group">
                                <label>Available</label>
                                <input type="number" name="available" class="form-control" value="${pro.available }" required>
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