<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 02-Mar-24
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
<%@page isELIgnored="false" %>
--%>
<html>
<head>
  <%@ page isELIgnored="false" %>

  <c:import url="includes/head.jsp"></c:import>
</head>
<body>
<!-- Spinner Start -->
<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
  <div class="spinner-grow text-primary" role="status"></div>
</div>
<!-- Spinner End -->
<c:set var="currentPage" value="shopDetail" />
<c:import url="includes/navbar.jsp">

 <c:param name="currentPage" value="${currentPage}" />

</c:import>

<%--<!-- Modal Search Start -->--%>
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
  <h1 class="text-center text-white display-6">Shop Detail</h1>
  <ol class="breadcrumb justify-content-center mb-0">
    <li class="breadcrumb-item"><a href="#">Home</a></li>
    <li class="breadcrumb-item"><a href="#">Pages</a></li>
    <li class="breadcrumb-item active text-white">Shop Detail</li>
  </ol>
</div>
<!-- Single Page Header End -->

<!-- Single Product Start -->
<div id="show-pro" class="container-fluid py-5 mt-5">
  <div class="container py-5">
    <div class="row g-4 mb-5">
      <div class="col-lg-8 col-xl-9">
        <div class="row g-4">
          <div class="col-lg-6">
            <div class="border rounded">
              <a href="#">
                <img src="${pro.img}" class="img-fluid rounded" alt="Image">
              </a>
            </div>

          </div>
          <div class="col-lg-6">
              <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">Food</div>
              <div style="margin-top: 20px;">
                  <h4 class="fw-bold mb-3">${pro.name}</h4>
                  <p class="mb-3">Category: ${category.category}</p>
                  <p class="mb-4">${pro.descriptionP}</p>
                  <p class="fw-bold mb-3">${pro.sellingPrice} $</p>
                  <input type="hidden" name="idpro" value="${pro.productId}">
                  <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary" style="margin-right: 10px; margin-top: 10px;" id="addPro"><i class="fa fa-shopping-bag me-2 text-primary"></i>Add </a>
                  <a href="addToCartServlet?idpro=${pro.productId}&value=buy" class="btn border border-secondary rounded-pill px-3 text-primary" style="margin-right: 10px; margin-top: 10px;">Buy</a>
              </div>
          </div>

        </div>
      </div>
      <div class="col-lg-4 col-xl-3">
        <div class="row g-4 fruite">
          <div class="col-lg-12">
            <div class="mb-4">
              <h4>Categories</h4>
              <ul class="list-unstyled fruite-categorie">
                <li>
                  <div class="d-flex justify-content-between fruite-name">
                    <a ><i class="fas fa-apple-alt me-2"></i>Đồ Chay</a>
                  </div>
                </li>
                <li>
                  <div class="d-flex justify-content-between fruite-name">
                    <a ><i class="fas fa-apple-alt me-2"></i>Thịt Cá</a>
                  </div>
                </li>
                <li>
                  <div class="d-flex justify-content-between fruite-name">
                    <a ><i class="fas fa-apple-alt me-2"></i>Trái Cây</a>
                  </div>
                </li>
                <li>
                  <div class="d-flex justify-content-between fruite-name">
                    <a ><i class="fas fa-apple-alt me-2"></i>Ăn Vặt</a>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <h1 class="fw-bold mb-0">Related products</h1>
    <div class="vesitable">
      <div class="owl-carousel vegetable-carousel justify-content-center">
        <c:forEach var="pro" items="${list}">
            <div id="show-pro" class="border border-primary rounded position-relative vesitable-item" >
                    <div class="vesitable-img">
                        <a href="./shopDetail?pid=${pro.productId}"><img src="${pro.img}" class="img-fluid w-100 rounded-top " alt=""></a>
                    </div>
                    <div class="p-4 pb-0 rounded-bottom">
                        <h4>${pro.name}</h4>
                        <p>${pro.descriptionP}</p>
                        <div class="d-flex justify-content-between flex-lg-wrap">
                            <p class="text-dark fs-5 fw-bold">${pro.sellingPrice}</p>
                            <input type="hidden" name="idpro" value="${pro.productId}">
                            <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"  id="addPro"><i class="fa fa-shopping-bag me-2 text-primary"></i>Add </a>
                            <a href="addToCartServlet?idpro=${pro.productId}&value=buy" class="btn border border-secondary rounded-pill px-3 text-primary">Buy</a>
                                </div>
                    </div>
                </div>
        </c:forEach>
      </div>
    </div>
  </div>
</div>
<!-- Single Product End -->

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%--xử lí load lại trang khi thêm sản phẩm vào giỏ hàng--%>




<script>
    function highlightCategory(element) {
        // Xoá lớp active ở tất cả các mục
        var allItems = document.querySelectorAll('.fruite-categorie a');
        allItems.forEach(item => item.classList.remove('active'));

        // Thêm lớp active vào mục được chọn
        element.classList.add('active');
    }


    document.addEventListener("DOMContentLoaded", function() {
        // Lấy giá trị category từ URL
        var urlParams = new URLSearchParams(window.location.search);
        var categoryParam = urlParams.get('category');

        // Nếu có categoryParam, thực hiện highlight cho mục tương ứng
        if (categoryParam) {
            var categoryLink = document.querySelector('.fruite-categorie a[id="' + categoryParam + '"]');
            if (categoryLink) {
                categoryLink.classList.add('active');
            }
        }
    });

    $(document).ready(function() {
        // Xử lý sự kiện phân trang
        $(document).on('click', '.pagination-link', function(e) {
            e.preventDefault(); // Ngăn chặn hành vi mặc định của liên kết
            var page = $(this).data('page');
            var category = $('.category-link.active').data('category') || null;
            loadProducts(page, category);
        });

        // Xử lý sự kiện chọn mục sản phẩm
        $(document).on('click', '.category-link', function(e) {
            e.preventDefault(); // Ngăn chặn hành vi mặc định của liên kết
            var category = $(this).data('category');
            loadProducts(1, category); // Trang đầu tiên khi chọn một danh mục sản phẩm
        });

        // Hàm tải sản phẩm
        function loadProducts(page, category) {
            $.ajax({
                url: 'shopServlet',
                type: 'GET',
                data: {
                    page: page,
                    category: category
                },
                success: function(response) {
                    var newContent = $(response).find('#content-pro').html();
                    var newPagination = $(response).find('.pagination').html();
                    $('#content-pro').html(newContent);
                    $('.pagination').html(newPagination);

                    // Cuộn lên đầu trang sau khi cập nhật nội dung
                    $('html, body').animate({ scrollTop: $('#content-pro').offset().top }, 'fast');
                },
                error: function(xhr, status, error) {
                    console.error('Lỗi khi tải sản phẩm:', error);
                }
            });
        }
    });
</script>

<script>
    $(document).ready(function (){
        $(document).on("click", "#addPro", function(e) {
            e.preventDefault();
            var id = $(this).closest("#show-pro").find('input[name = "idpro"]').val();
            console.log("valueIdpro: "+id);
            $.ajax({
                type: "POST",
                url: "/addToCartServlet",
                data:{
                    idpro : id,
                    value: "add"
                } ,
                success: function(response) {
                    // console.log("da vao success");
                    updateQuantityInShop(response);



                },

                error: function(xhr,error) {
                    // Xử lý lỗi nếu có
                    console.error("Lỗi khi cập nhật giá:", error);
                }
            });
        });



    });
    function updateQuantityInShop(respone){
        //hàm này dùng để cập nhật lại so lượng giỏ hàng trong navbar
        updatePage(respone);
    };

</script>

<script src="https://cdn.botpress.cloud/webchat/v2/inject.js"></script>
<script src="https://mediafiles.botpress.cloud/a77464d7-c8b2-4ed1-836d-d9a69c481824/webchat/v2/config.js"></script>

</body>
</html>
