<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 03-Mar-24
  Time: 1:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <c:import url="includes/head.jsp"></c:import>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<!-- Spinner Start -->
<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status"></div>
</div>
<!-- Spinner End -->
<c:set var="currentPage" value="coupon" />
<c:import url="includes/navbar.jsp">
    <c:param name="currentPage" value="${currentPage}" />
</c:import>

<!-- Modal Search Start -->
<div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-fullscreen">
        <div class="modal-content rounded-0">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex align-items-center">
                <div class="input-group w-75 mx-auto d-flex">
                    <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                    <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Search End -->

<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Coupon</h1>
    <ol class="breadcrumb justify-content-center mb-0">
        <li class="breadcrumb-item"><a href="#">Home</a></li>
        <li class="breadcrumb-item"><a href="#">Pages</a></li>
        <li class="breadcrumb-item active text-white">Coupon</li>
    </ol>
</div>
<!-- Single Page Header End -->

<!-- Coupon Codes Section Start -->
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-12">
            <div class="row">
                <c:forEach var="coupon" items="${couponList}">
                    <div class="col-md-4 mb-4">
                        <div class="card shadow">
                            <div class="card-body">
                                <h5 class="card-title" style="text-align: center; font-family: 'Times New Roman', sans-serif;">${coupon.code}</h5>
                                <hr>
                                <p class="card-text" style="display: none;" >Discount Type: ${coupon.discountType}</p>
                                <p class="card-text">Giảm: ${coupon.discountValue} %</p>
                                <p class="card-text">Tối đa: ${coupon.maxDiscountValue} $</p>
                                <p class="card-text">Đơn tối thiểu: ${coupon.minTotalValue} $</p>
                                <form id="couponForm${coupon.id}" method="post" action="${pageContext.request.contextPath}/CouponServlet" class="d-flex justify-content-center" onsubmit="saveCoupon(event, ${coupon.id})">
                                    <input type="hidden" name="customer_id" value="${customer.user_id}">
                                    <input type="hidden" name="coupon_id" value="${coupon.id}">
                                    <button class="btn btn-primary" type="submit">Lưu Mã</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${not empty message}">
                <div class="alert alert-info mt-4">
                        ${message}
                </div>
            </c:if>
        </div>
    </div>
</div>

<!-- Coupon Codes Section End -->

<!-- Start Footer Section -->
<c:import url="includes/footer.jsp"></c:import>
<!-- End Footer Section -->

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
<script src="js/main.js"></script>

<script>
    function saveCoupon(event, couponId) {
        event.preventDefault();
        var form = $('#couponForm' + couponId);
        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: form.serialize(),
            success: function(response) {
                alert('Coupon saved successfully!');
                // Optionally, you can update the UI or show a message to the user
            },
            error: function() {
                alert('Error saving coupon.');
            }
        });
    }
</script>

</body>
</html>