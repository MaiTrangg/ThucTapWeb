<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 02-Mar-24
  Time: 11:34 PM
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

<c:set var="currentPage" value="cart" />
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
    <h1 class="text-center text-white display-6">Cart</h1>
    <ol class="breadcrumb justify-content-center mb-0">
        <li class="breadcrumb-item"><a href="#">Home</a></li>
        <li class="breadcrumb-item"><a href="#">Pages</a></li>
        <li class="breadcrumb-item active text-white">Cart</li>
    </ol>
</div>
<!-- Single Page Header End -->


<!-- Cart Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Products</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Total</th>
                    <th scope="col">Handle</th>
                </tr>
                </thead>
                <tbody id="orderDetail">
<%--                <c:if test="${order} == null">--%>
<%--                    order null--%>
<%--                </c:if>--%>
                <c:forEach items="${order.orderDetails}" var="od">
                    <tr >
                        <th scope="row">
                            <div class="d-flex align-items-center">
                                <img src="${od.product.img}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                            </div>
                        </th>
                        <td>
                            <p class="mb-0 mt-4">${od.product.name}</p>
                        </td>
                        <td>
                            <p class="mb-0 mt-4">${od.product.sellingPrice}</p>
                        </td>
                        <td>
<%--                            <form action="updatePriceServlet" method="post">--%>
                                <input type="hidden" name="idpro" value="${od.product.productId}">
                                <div class="input-group quantity mt-4" style="width: 100px;">
                                    <div class="input-group-btn">
                                        <button id="decrease" class="btn btn-sm btn-minus rounded-circle bg-light border" >
                                            <i class="fa fa-minus"></i>
                                        </button>
                                    </div>
                                    <input type="text" class="form-control form-control-sm text-center border-0" value="${od.quantity}" name="quantity">
                                    <div class="input-group-btn">
                                        <button id="increase" class="btn btn-sm btn-plus rounded-circle bg-light border">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
<%--                            </form>--%>
                        </td>
                        <td>
                            <p class="mb-0 mt-4"  id="pro-${od.product.productId}" >${od.price }</p>
                        </td>
                        <td>
                           <a href="#">
                               <button class="btn btn-md rounded-circle bg-light border mt-4" id="delete" name="btn-delete" value="delete">
                                   <i class="fa fa-times text-danger"></i>
                               </button>
                           </a>
                        </td>

                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
        <div class="mt-5">
            <input type="text" class="border-0 border-bottom rounded me-5 py-3 mb-4" placeholder="Coupon Code">
            <button class="btn border-secondary rounded-pill px-4 py-3 text-primary" type="button">Apply Coupon</button>
        </div>
        <div class="row g-4 justify-content-end">
            <div class="col-8"></div>
            <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
                <div class="bg-light rounded">
                    <div class="p-4">
                        <h1 class="display-6 mb-4">Cart <span class="fw-normal">Total</span></h1>
                        <div class="d-flex justify-content-between mb-4">
                            <h5 class="mb-0 me-4">Subtotal:</h5>
                            <p class="mb-0" id="totalPrice-first">${order.total()}</p>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h5 class="mb-0 me-4">Shipping</h5>
                            <div class="">
                                <p class="mb-0">Flat rate: $3.00</p>
                            </div>
                        </div>
                        <p class="mb-0 text-end">Shipping to Ukraine.</p>
                    </div>
                    <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                        <h5 class="mb-0 ps-4 me-4">Total</h5>
                        <p class="mb-0 pe-4" id="totalPrice-after">${order.total()}</p>
                    </div>
                    <a href="checkLoginServlet"><button class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4" type="button">Proceed Checkout</button></a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Cart Page End -->

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
    $(document).ready(function() {
        // $("#increase, #decrease").click(function(e) {  việc gán sự kiện chỉ được thực hiện cho phần tử tồn
        // tại tại thời điểm đó, và không ảnh hưởng đến các phần tử sau này được thêm vào DOM
            $(document).on("click", "#increase, #decrease", function(e) {//việc gán sự kiện được thực hiện một lần và áp dụng cho tất cả các phần tử con khớp
                // với selector, dù chúng được thêm vào sau đó. Điều này có thể làm giảm hiệu suất một chút so với việc gán sự kiện trực tiếp cho phần tử
                // cụ thể, nhất là khi có nhiều sự kiện được gán trên document
            e.preventDefault();
            /**
             * khi click vào nút tăng hoặc giảm số lượng sản phẩm
             * @type {string|jQuery|*}
             */
            var idproValue = $(this).closest("tr").find('input[name="idpro"]').val();
            var quantityValue = $(this).closest("tr").find('input[name="quantity"]').val();

            console.log(idproValue);
            // var quantityValue = $('input[name="quantity"]').val();
            console.log(quantityValue);
            var currentPro = $(this);


            // Gửi dữ liệu lên server bằng AJAX
            $.ajax({
                type: "POST",
                url: "/updatePriceServlet",
                data:{
                   idpro : idproValue,
                   quantity : quantityValue
                } ,
                success: function(response) {
                    // Xử lý phản hồi từ server nếu cần
                    // console.log("Đã cập nhật giá thành công");
                    var newPrice = response.newPrice; // Trích xuất giá mới từ phản hồi
                    var newTotal = response.newTotalPrice;//lấy ra tổng giá mới sau khi xử lí trong servlet
                    var newQuantity = response.newQuantity;//lấy ra số lượng sp sau khi xử lí trong servlet

                    console.log("newtotal: "+newTotal);
                    $("#pro-"+idproValue+"").text(newPrice); // cập nhật lại giá trên giao diện người dùng
                    $("#totalPrice-first, #totalPrice-after").text(newTotal);///cập nhật tổng giá trên giao diện người dùng
                    console.log("newQuantity: "+newQuantity.type);
                    //nếu quantity từ servlet trả về là 0 thì sẽ tiến hành xóa thẻ tr gần nơi sự kiện được kích hoạt
                    if(newQuantity===0) {
                        currentPro.closest("tr").remove();
                        updateQuantityInCart(response);
                    }


                },
                error: function(xhr,error) {
                    // Xử lý lỗi nếu có
                    console.error("Lỗi khi cập nhật giá:", error);
                }
            });
        });

        $(document).on("click", "#delete", function(e) {
            e.preventDefault();
            /**
             * khi click vào nút tăng hoặc giảm số lượng sản phẩm
             * @type {string|jQuery|*}
             */
            var idproValue = $(this).closest("tr").find('input[name="idpro"]').val();// lấy ra giá trị của thẻ input có name là idpro

            var currentPro = $(this);// lấy ra phần mục đươc kích hoạt sự kiện


            // Gửi dữ liệu lên server bằng AJAX
            $.ajax({
                type: "POST",
                url: "/removeToCartServlet",
                data:{
                    idpro : idproValue// gửi id khi chuyển đến trang removeToCartServlet
                } ,
                success: function(response) {
                    var newPrice = response.newPrice; // Trích xuất giá mới từ phản hồi
                    var newTotal = response.newTotalPrice;//lấy ra tổng giá mới sau khi xử lí trong servlet

                    console.log("newtotal: "+newTotal);
                    $("#pro-"+idproValue+"").text(newPrice); // cập nhật lại giá trên giao diện người dùng
                    $("#totalPrice-first, #totalPrice-after").text(newTotal);//cập nhật tổng giá trên giao diện người dùng
                    currentPro.closest("tr").remove();//tìm đến thẻ tr gần nút được click nhất và tiến hành xóa nó đi
                    updateQuantityInCart(response);
                },
                error: function(xhr,error) {
                    // Xử lý lỗi nếu có
                    console.error("Lỗi khi cập nhật giá:", error);
                }
            });

        });
        function updateQuantityInCart(respone){
            //hàm này dùng để cập nhật lại so lượng giỏ hàng trong navbar
            updatePage(respone);
        };
    });
</script>

</body>
</html>
