<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 02-Mar-24
  Time: 10:58 PM
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
    .highlighted {
        color: var(--bs-secondary);; /* Màu chữ của liên kết được highlight */
    }
    .active {
        font-weight: bold; /* Có thể thay đổi kiểu hiển thị của mục được chọn */
        color: var(--bs-secondary);
    }
    .h-30{
        height: 30%;
    }
    h-600{
        height: 600px;
    }
    h-70{
        height: 70%;
    }


</style>
<body>
<!-- Spinner Start -->
<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status"></div>
</div>
<!-- Spinner End -->

<c:set var="currentPage" value="shop" />
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
    <h1 class="text-center text-white display-6">Shop</h1>
    <ol class="breadcrumb justify-content-center mb-0">
        <li class="breadcrumb-item"><a href="#">Home</a></li>
        <li class="breadcrumb-item"><a href="#">Pages</a></li>
        <li class="breadcrumb-item active text-white">Shop</li>
    </ol>
</div>
<!-- Single Page Header End -->


<!-- Fruits Shop Start-->
<div class="container-fluid fruite py-5">
    <div class="container py-5">
        <h1 class="mb-4">Fresh fruits shop</h1>
        <div class="row g-4">
            <div class="col-lg-12">
                <div class="row g-4">
                    <div class="col-xl-3">
                        <div class="input-group w-100 mx-auto d-flex">
                            <form action="searchServlet" method="get">
                                <input type="search" class="form-control" id="inputModalSearch"
                                       name="search" placeholder="Search ..." list="datalist1">
                                <datalist id="datalist1">
                                    <c:forEach items="${listP}" var="p">
                                        <option value="${p.name}"></option>
                                    </c:forEach>
                                </datalist>
                                <button type="submit"
                                        class="input-group-text bg-success text-light">
                                    <i class="fa fa-fw fa-search text-white"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                    <div class="col-6"></div>
                    <div class="col-xl-3">
                        <div class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
                            <label for="fruits">Default Sorting:</label>
                            <select id="fruits" name="fruitlist" class="border-0 form-select-sm bg-light me-3" form="fruitform">
                                <option value="volvo">Nothing</option>
                                <option value="saab">Popularity</option>
                                <option value="opel">Organic</option>
                                <option value="audi">Fantastic</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row g-4">
                    <div class="col-lg-3">
                        <div class="row g-4">
                            <div class="col-lg-12">
                                <div class="mb-3">
                                    <h4>Categories</h4>
<%--                                    <ul class="list-unstyled fruite-categorie"   >--%>

<%--                                        <c:forEach var="cate" items="${listCate}">--%>
<%--                                            <li>--%>
<%--                                                <div class="d-flex justify-content-between fruite-name ">--%>
<%--                                                    <a id="${cate.category}" href="shopServlet?category=${cate.category}" ><i class="fas fa-apple-alt me-2"></i>${cate.category}</a>--%>
<%--                                                    <span>(${cate.quantity})</span>--%>
<%--                                                </div>--%>
<%--                                            </li>--%>
<%--                                            </c:forEach>--%>

                                    <ul class="list-unstyled fruite-categorie">
<%--                                        <c:forEach var="cate" items="${listCate}">--%>
<%--                                            <li>--%>
<%--                                                <div class="d-flex justify-content-between fruite-name">--%>
<%--                                                    <a id="${cate.category}" href="shopServlet?category=${cate.category}" onclick="highlightCategory(this)"><i class="fas fa-apple-alt me-2"></i>${cate.category}</a>--%>

<%--                                                    <span>(${cate.quantity})</span>--%>
<%--                                                </div>--%>
<%--                                            </li>--%>
<%--                                        </c:forEach>--%>
    <c:forEach var="cate" items="${listCate}">
        <li>
            <div class="d-flex justify-content-between fruite-name">
                <a id="${cate.category}" href="#" class="category-link" data-category="${cate.category}" onclick="highlightCategory(this)"><i class="fas fa-apple-alt me-2"></i>${cate.category}</a>
                <span>(${cate.quantity})</span>
            </div>
        </li>
    </c:forEach>

                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="mb-3">
                                    <h4 class="mb-2">Price</h4>
                                    <input type="range" class="form-range w-100" id="rangeInput" name="rangeInput" min="0" max="500" value="0" oninput="amount.value=rangeInput.value">
                                    <output id="amount" name="amount" min-velue="0" max-value="500" for="rangeInput">0</output>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="mb-3">
                                    <h4>Additional</h4>
                                    <div class="mb-2">
                                        <input type="radio" class="me-2" id="Categories-1" name="Categories-1" value="Beverages">
                                        <label for="Categories-1"> Organic</label>
                                    </div>
                                    <div class="mb-2">
                                        <input type="radio" class="me-2" id="Categories-2" name="Categories-1" value="Beverages">
                                        <label for="Categories-2"> Fresh</label>
                                    </div>
                                    <div class="mb-2">
                                        <input type="radio" class="me-2" id="Categories-3" name="Categories-1" value="Beverages">
                                        <label for="Categories-3"> Sales</label>
                                    </div>
                                    <div class="mb-2">
                                        <input type="radio" class="me-2" id="Categories-4" name="Categories-1" value="Beverages">
                                        <label for="Categories-4"> Discount</label>
                                    </div>
                                    <div class="mb-2">
                                        <input type="radio" class="me-2" id="Categories-5" name="Categories-1" value="Beverages">
                                        <label for="Categories-5"> Expired</label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <h4 class="mb-3">Featured products</h4>
                                <div class="d-flex align-items-center justify-content-start">
                                    <div class="rounded me-4" style="width: 100px; height: 100px;">
                                        <img src="img/featur-1.jpg" class="img-fluid rounded" alt="">
                                    </div>
                                    <div>
                                        <h6 class="mb-2">Big Banana</h6>
                                        <div class="d-flex mb-2">
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="d-flex mb-2">
                                            <h5 class="fw-bold me-2">2.99 $</h5>
                                            <h5 class="text-danger text-decoration-line-through">4.11 $</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex align-items-center justify-content-start">
                                    <div class="rounded me-4" style="width: 100px; height: 100px;">
                                        <img src="img/featur-2.jpg" class="img-fluid rounded" alt="">
                                    </div>
                                    <div>
                                        <h6 class="mb-2">Big Banana</h6>
                                        <div class="d-flex mb-2">
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="d-flex mb-2">
                                            <h5 class="fw-bold me-2">2.99 $</h5>
                                            <h5 class="text-danger text-decoration-line-through">4.11 $</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex align-items-center justify-content-start">
                                    <div class="rounded me-4" style="width: 100px; height: 100px;">
                                        <img src="img/featur-3.jpg" class="img-fluid rounded" alt="">
                                    </div>
                                    <div>
                                        <h6 class="mb-2">Big Banana</h6>
                                        <div class="d-flex mb-2">
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star text-secondary"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                        <div class="d-flex mb-2">
                                            <h5 class="fw-bold me-2">2.99 $</h5>
                                            <h5 class="text-danger text-decoration-line-through">4.11 $</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center my-4">
                                    <a href="#" class="btn border border-secondary px-4 py-3 rounded-pill text-primary w-100">Vew More</a>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="position-relative">
                                    <img src="img/banner-fruits.jpg" class="img-fluid w-100 rounded" alt="">
                                    <div class="position-absolute" style="top: 50%; right: 10px; transform: translateY(-50%);">
                                        <h3 class="text-secondary fw-bold">Fresh <br> Fruits <br> Banner</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9">

                        <div class="row g-4 justify-content-center" id="content-pro" >
                            <c:forEach var="pro" items="${listP}">
                                <div id="show-pro" class="col-md-6 col-lg-6 col-xl-4" >
                                    <div class="rounded position-relative fruite-item ">
                                        <div class="fruite-img">
                                            <img src="${pro.img}" class="img-fluid w-100 rounded-top " alt="">
                                        </div>
                                        <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">Food</div>
                                        <div class="p-4 border border-secondary border-top-0 rounded-bottom ">
                                            <h4>${pro.name}</h4>
                                            <p>${pro.descriptionP}</p>
                                            <div class="d-flex justify-content-between flex-lg-wrap">
                                                <p class="text-dark fs-5 fw-bold mb-0">${pro.sellingPrice}</p>
                                                <input type="hidden" name="idpro" value="${pro.productId}">

                                                <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary" id="addPro"><i class="fa fa-shopping-bag me-2 text-primary"></i>Add </a>
                                                <a href="addToCartServlet?idpro=${pro.productId}&value=buy" class="btn border border-secondary rounded-pill px-3 text-primary">Buy</a>
<%--                                                <a href="#" id="add-to-cart" data-id="${pro.productId}" data-action="add" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i>Add </a>--%>
<%--                                                <a href="#" id="buy-now" data-id="${pro.productId}" data-action="buy" class="btn border border-secondary rounded-pill px-3 text-primary">Buy</a>--%>
<%--                                                <a href="addToCartServlet?idpro=${pro.productId}&value=buy" class="btn border border-secondary rounded-pill px-3 text-primary">Buy</a>--%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>


                            <!-- Pagination -->
                            <c:set var="page" value="${page}" />
<%--                            <div class="pagination">--%>
<%--                                <!-- Pagination -->--%>
<%--                                <c:forEach begin="1" end="${num}" var="inum">--%>
<%--                                    <a class="${inum==page?' active':''}" href="shopServlet?page=${inum}&category=${category}">${inum}</a>--%>
<%--                                </c:forEach>--%>
<%--                            </div>--%>
                            <div class="pagination">
                                <!-- Pagination links should have 'pagination-link' class -->
                                <c:forEach begin="1" end="${num}" var="inum">
                                    <a class="pagination-link${inum==page?' active':''}" href="#" data-page="${inum}">${inum}</a>
                                </c:forEach>
                            </div>


                            <!-- Thêm các liên kết khác tùy thuộc vào các loại sản phẩm khác -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Fruits Shop End-->

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%--xử lí load lại trang khi theem sảnphaamrm vào giỏ hàng--%>




<script>
    <%--document.addEventListener("DOMContentLoaded", function() {--%>
    <%--    var highlightLinkId = '${nameTab}';--%>
    <%--    var linkToHighlight = document.getElementById(highlightLinkId);--%>
    <%--    if (linkToHighlight) {--%>
    <%--        linkToHighlight.classList.add("highlighted");--%>
    <%--    }--%>
    <%--});--%>
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