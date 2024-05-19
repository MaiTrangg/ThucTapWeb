<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 02-Mar-24
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<c:set var="currentPage" value="checkout" />
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
    <h1 class="text-center text-white display-6">Checkout</h1>
    <ol class="breadcrumb justify-content-center mb-0">
        <li class="breadcrumb-item"><a href="#">Home</a></li>
        <li class="breadcrumb-item"><a href="#">Pages</a></li>
        <li class="breadcrumb-item active text-white">Checkout</li>
    </ol>
</div>
<!-- Single Page Header End -->


<!-- Checkout Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <h1 class="mb-4">Billing details</h1>
        <form action="checkoutServlet" method="post">
            <div class="row g-5">
                <div class="col-md-12 col-lg-6 col-xl-7">

                    <div class="form-item">
                        <label class="form-label my-3">Họ và tên<sup>*</sup></label>
                        <input type="text" class="form-control" name="fullName" required>
                    </div>
<%--                    <div class="row">--%>
<%--                        <div class="col-md-12 col-lg-6">--%>
<%--                            <div class="form-item w-100">--%>
<%--                                <label class="form-label my-3">First Name<sup>*</sup></label>--%>
<%--                                <input type="text" class="form-control">--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-12 col-lg-6">--%>
<%--                            <div class="form-item w-100">--%>
<%--                                <label class="form-label my-3">Last Name<sup>*</sup></label>--%>
<%--                                <input type="text" class="form-control">--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>

                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <div class="form-item d-flex justify-content-between align-items-center css-province">
                                <div class="w-30">
                                    <label class="form-label my-3">Địa chỉ<sup>*</sup></label>
                                </div>
                                <div class="w-20">
                                    <select  id="tinh" name="tinh" required>
                                        <option value="-1">Tỉnh Thành</option>
                                    </select>
                                </div>
                                <div class="w-20">
                                    <select  id="quan" name="quan" required>
                                        <option value="-1">Quận Huyện</option>
                                    </select>
                                </div>
                                <div class="w-20">
                                    <select  id="phuong" name="phuong" required>
                                        <option value="-1">Phường Xã</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-item">
                        <textarea name="noteAddress" class="form-control" spellcheck="false" cols="30" rows="5" placeholder="Tên đường, tòa nhà, số nhà" required></textarea>
                    </div>
                    <div class="form-item">
                        <label class="form-label my-3">Email <sup>*</sup></label>
                        <input type="email" class="form-control" name="email">
                    </div>
                    <div class="form-item">
                        <label class="form-label my-3">Số điện thoại<sup>*</sup></label>
                        <input type="tel" class="form-control" name="numberPhone">
                    </div>
<%--                    <div class="form-item">--%>
<%--                        <label class="form-label my-3">Postcode/Zip<sup>*</sup></label>--%>
<%--                        <input type="text" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-item">--%>
<%--                        <label class="form-label my-3">Mobile<sup>*</sup></label>--%>
<%--                        <input type="tel" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-item">--%>
<%--                        <label class="form-label my-3">Email Address<sup>*</sup></label>--%>
<%--                        <input type="email" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-check my-3">--%>
<%--                        <input type="checkbox" class="form-check-input" id="Account-1" name="Accounts" value="Accounts">--%>
<%--                        <label class="form-check-label" for="Account-1">Create an account?</label>--%>
<%--                    </div>--%>
<%--                    <hr>--%>
<%--                    <div class="form-check my-3">--%>
<%--                        <input class="form-check-input" type="checkbox" id="Address-1" name="Address" value="Address">--%>
<%--                        <label class="form-check-label" for="Address-1">Ship to a different address?</label>--%>
<%--                    </div>--%>
                    <div class="form-item my-3">
                        <textarea name="noteOrder" class="form-control" spellcheck="false" cols="30" rows="11" placeholder="Ghi chú cho đơn hàng"></textarea>
                    </div>
                </div>
                <div class="col-md-12 col-lg-6 col-xl-5">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Products</th>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${order.orderDetails}" var="o">
                                <c:if test="${empty order}">
                                    <p>No items in order.</p>
                                </c:if>

                                <tr>
                                    <th scope="row">
                                        <div class="d-flex align-items-center mt-2">
                                            <img src="${o.product.img}" class="img-fluid rounded-circle" style="width: 90px; height: 90px;" alt="">
                                        </div>
                                    </th>
                                    <td class="py-5">${o.product.name}</td>
                                    <td class="py-5">${o.product.sellingPrice}</td>
                                    <td class="py-5">${o.quantity}</td>
                                    <td class="py-5">${o.price}</td>
                                </tr>
                            </c:forEach>

                            <tr>
                                <th scope="row">
                                </th>
                                <td class="py-5"></td>
                                <td class="py-5"></td>
                                <td class="py-5">
                                    <p class="mb-0 text-dark py-3">Subtotal</p>
                                </td>
                                <td class="py-5">
                                    <div class="py-3 border-bottom border-top">
                                        <p class="mb-0 text-dark">${order.total()}</p>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">
                                </th>
                                <td class="py-5">
                                    <p class="mb-0 text-dark py-4">Shipping</p>
                                </td>
                                <td colspan="3" class="py-5">
                                    <div class="form-check text-start">
                                        <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-1" name="Shipping-1" value="Shipping">
                                        <label class="form-check-label" for="Shipping-1">Free Shipping</label>
                                    </div>
                                    <div class="form-check text-start">
                                        <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-2" name="Shipping-1" value="Shipping">
                                        <label class="form-check-label" for="Shipping-2">Flat rate: $15.00</label>
                                    </div>
                                    <div class="form-check text-start">
                                        <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-3" name="Shipping-1" value="Shipping">
                                        <label class="form-check-label" for="Shipping-3">Local Pickup: $8.00</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">
                                </th>
                                <td class="py-5">
                                    <p class="mb-0 text-dark text-uppercase py-3">TOTAL</p>
                                </td>
                                <td class="py-5"></td>
                                <td class="py-5"></td>
                                <td class="py-5">
                                    <div class="py-3 border-bottom border-top">
                                        <p class="mb-0 text-dark">$135.00</p>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="row g-4 text-center align-items-center justify-content-center pt-4">
                        <button type="submit" class="btn border-secondary py-3 px-4 text-uppercase w-100 text-primary">Place Order</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- Checkout Page End -->

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
<script src="js/main.js"></script>
<script>
    $(document).ready(function () {
        // Lấy tỉnh thành
        $.getJSON('https://esgoo.net/api-tinhthanh/1/0.htm', function (data_tinh) {
            if (data_tinh.error == 0) {
                $.each(data_tinh.data, function (key_tinh, val_tinh) {
                    $("#tinh").append('<option value="' + val_tinh.name + '" data-id="'+val_tinh.id+'">' + val_tinh.name + '</option>');
                    // console.log(val_tinh.name)
                });
            }
        });

        // Khi chọn tỉnh
        $("#tinh").change(function (e) {
            const idtinh = $('#tinh').find("option:selected").attr("data-id");
            // Lấy quận huyện
            $.getJSON('https://esgoo.net/api-tinhthanh/2/' + idtinh + '.htm', function (data_quan) {
                if (data_quan.error == 0) {
                    $("#quan").html('<option value="0">Quận Huyện</option>');
                    $("#phuong").html('<option value="0">Phường Xã</option>');
                    $.each(data_quan.data, function (key_quan, val_quan) {
                        $("#quan").append('<option value="' + val_quan.name + '" data-id="'+val_quan.id+'">'  + val_quan.name + '</option>');
                        // console.log(val_quan.name)
                    });
                }
            });
        });

        // Khi chọn quận/huyện
        $("#quan").change(function (e) {
            const idquan = $('#quan').find("option:selected").attr("data-id");
            // Lấy phường xã
            $.getJSON('https://esgoo.net/api-tinhthanh/3/' + idquan + '.htm', function (data_phuong) {
                if (data_phuong.error == 0) {
                    $("#phuong").html('<option value="0">Phường Xã</option>');
                    $.each(data_phuong.data, function (key_phuong, val_phuong) {
                        $("#phuong").append('<option value="' + val_phuong.name + '" data-id="'+val_phuong.id+'">' + val_phuong.name + '</option>');
                        // console.log(val_phuong.name)
                    });
                }
            });
        });
    });
</script>

</body>
</html>
