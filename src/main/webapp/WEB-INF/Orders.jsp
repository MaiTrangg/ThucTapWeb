<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@page isELIgnored="false" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap CRUD Data Table for Database with Modal Form</title> -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- link modal -->
    <!-- Bootstrap CSS -->
    <!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">  -->

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>

    <!--  Popper.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

    <!-- Bootstrap JS-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <c:import url="includes_ad/header.jsp"></c:import>
    <link href="css/manager.css" rel="stylesheet" />
</head>

<style>
    #sp  img{
        width: 200px;
        height: 120px;
    }
    /* Style cho bảng */
    .table-striped tbody tr:nth-of-type(odd) {
        background-color: #f9f9f9;
    }

    .table-hover tbody tr:hover {
        background-color: #f1f1f1;
    }

    /* Style cho các cột */
    th, td {
        text-align: left;
        padding: 8px;
    }

    option[value="Chờ xác nhận"] { background-color: #e3d981; }
    option[value="Đang xử lý"] { background-color: #B4EEB4; }
    option[value="Hoàn tất xác nhận"] { background-color: #00EE76; }
    option[value="Đóng gói"] { background-color: #EECFA1; }
    option[value="Đang giao hàng"] { background-color: #EEC900; }
    option[value="Giao hàng thành công"] { background-color: #63B8FF; }
    option[value="Đã hủy"] { background-color: #EE3B3B;
    }

    .btn_detail{
        background-color: #568ecb;
        color: white;
        border: none;
        padding: 8px 12px;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .btn_detail:hover {
        background-color: #0056b3;
    }

    .btn_detail i {
        margin-right: 4px;
    }



</style>
<body>
<div class="container-scroller">
    <div class="row p-0 m-0 proBanner" id="proBanner">
        <div class="col-md-12 p-0 m-0">
            <div class="card-body card-body-padding d-flex align-items-center justify-content-between">
                <div class="ps-lg-1">
                    <div class="d-flex align-items-center justify-content-between">
                        <p class="mb-0 font-weight-medium me-3 buy-now-text">Free 24/7 customer support, updates, and more with this template!</p>
                        <a href="https://www.bootstrapdash.com/product/connect-plus-bootstrap-admin-template/?utm_source=organic&utm_medium=banner&utm_campaign=buynow_demo" target="_blank" class="btn me-2 buy-now-btn border-0">Get Pro</a>
                    </div>
                </div>
                <div class="d-flex align-items-center justify-content-between">
                    <a href="https://www.bootstrapdash.com/product/connect-plus-bootstrap-admin-template/"><i class="mdi mdi-home me-3 text-white"></i></a>
                    <button id="bannerClose" class="btn border-0 p-0">
                        <i class="mdi mdi-close text-white me-0"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!-- partial:partials/_navbar.html -->
    <c:import url="includes_ad/navbar.jsp"></c:import>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
        <!-- partial:partials/_sidebar.html -->
        <c:set var="currentPage" value="orders" />
        <c:import url="includes_ad/sidebar.jsp">
            <c:param name="currentPage" value="${currentPage}" />
        </c:import>
        <%--  <c:import url="includes_ad/sidebar.jsp"></c:import> --%>
        <!-- partial -->

        <div class="main-panel">
            <div class="container" id="sp">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Manage <b>Orders</b></h2>
                            </div>
                        </div>
                    </div>
<%--                    <table id="2" class="table table-striped table-hover">--%>
                    <table id="orderTable" class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <!-- <th>
                                <span class="custom-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th> -->
                            <th>Id_Order</th>
                            <th>Account</th>
                            <th>TotalPrice</th>
                            <th>ExportDate</th>
                            <th>Action</th>
                            <th>Status</th>
                            <!--  <th>Actions</th> -->
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${orders }" var="o">
                            <tr>
                                <!-- <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td> -->
                                <td>${o.orderId}</td>
                                    <%--                                <td> ${o.customer.username} </td>--%>
                                <td> ${o.transaction.customer.username} </td>
                                <td> ${ o.totalMoney } </td>
                                <td>
                                    <p> <fmt:formatDate value="${o.dateOrder}" pattern="dd/MM/yyyy HH:mm:ss" /></p>
                                </td>
                                <td>
                                        <button class="btn_detail" onclick="viewOrderDetails(${o.orderId})">
                                            <i class="fa fa-eye"></i>
                                        </button>
                                </td>
                                <td>

                                    <input type="hidden" name="orderId" value="${o.orderId}">
                                    <select name="statusOrder" data-order-id="${o.orderId}" onchange="updateOrderStatus(${o.orderId}, this.value)">
                                        <option value="Chờ xác nhận" ${o.statusOrder == 'Chờ xác nhận' ? 'selected' : ''} ${['Đang xử lý', 'Hoàn tất xác nhận', 'Đóng gói', 'Đang giao hàng', 'Giao hàng thành công', 'Đã hủy'].indexOf(o.statusOrder) >= 0 ? 'disabled' : ''}>Chờ xác nhận</option>
                                        <option value="Đang xử lý" ${o.statusOrder == 'Đang xử lý' ? 'selected' : ''} ${['Hoàn tất xác nhận', 'Đóng gói', 'Đang giao hàng', 'Giao hàng thành công', 'Đã hủy'].indexOf(o.statusOrder) >= 0 ? 'disabled' : ''}>Đang xử lý</option>
                                        <option value="Hoàn tất xác nhận" ${o.statusOrder == 'Hoàn tất xác nhận' ? 'selected' : ''} ${['Đóng gói', 'Đang giao hàng', 'Giao hàng thành công', 'Đã hủy'].indexOf(o.statusOrder) >= 0 ? 'disabled' : ''}>Hoàn tất xác nhận</option>
                                        <option value="Đóng gói" ${o.statusOrder == 'Đóng gói' ? 'selected' : ''} ${['Đang giao hàng', 'HGiao hàng thành công', 'Đã hủy'].indexOf(o.statusOrder) >= 0 ? 'disabled' : ''}>Đóng gói</option>
                                        <option value="Đang giao hàng" ${o.statusOrder == 'Đang giao hàng' ? 'selected' : ''} ${['Giao hàng thành công', 'Đã hủy'].indexOf(o.statusOrder) >= 0 ? 'disabled' : ''}>Đang giao hàng</option>
                                        <option value="Giao hàng thành công" ${o.statusOrder == 'Giao hàng thành công' ? 'selected' : ''} ${['Đã hủy'].indexOf(o.statusOrder) >= 0 ? 'disabled' : ''}>Giao hàng thành công</option>
                                        <option value="Đã hủy" ${o.statusOrder == 'Đã hủy' ? 'selected' : ''}>Đã hủy</option>

                                    </select>

                                </td>
                                <td>

                                </td>
                                    <%--  <td>
                                         <a href="loadInforProServlet?idpro=${p.productId }"  class="edit" ><i class="material-icons"  title="Edit">&#xE254;</i></a>
                                         <a href="ad_deleteproservlet?idpro=${p.productId }" class="delete" ><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872; </i></a>
                                     </td> --%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <br>
                    <button onclick="exportTableToExcel('2', 'orders_data')">Export Table Data To Excel File</button>

                    <div class="clearfix">
                        <br>
                        <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                        <br>
                        <ul class="pagination">
                            <li class="page-item disabled"><a href="#">Previous</a></li>
                            <li class="page-item"><a href="#" class="page-link">1</a></li>
                            <li class="page-item"><a href="#" class="page-link">2</a></li>
                            <li class="page-item active"><a href="#" class="page-link">3</a></li>
                            <li class="page-item"><a href="#" class="page-link">4</a></li>
                            <li class="page-item"><a href="#" class="page-link">5</a></li>
                            <li class="page-item"><a href="#" class="page-link">Next</a></li>
                        </ul>
                    </div>
                </div>
                <!-- ad_addproServlet -->

                <%--            table chi tiet don hang--%>
                <div class="col-sm-6">
                    <h2>Order <b>Details</b></h2>
                </div>
                <table id="orderDetailTable" class="display table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Order Detail ID</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>Price</th>

                    </tr>
                    </thead>
                    <tbody id="orderDetailBody">
                    <c:if test="${not empty orderDetails}">
                        <c:forEach items="${orderDetails}" var="detail">
                            <tr>
                                <td>${detail.orderDetailId}</td>
                                <td>${detail.product.productId}</td>
                                <td>${detail.quantity}</td>
                                <td>${detail.price}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>

             <br>
            <button onclick="exportTableToExcel('2', 'orders_data')">Export Table Data To Excel File</button>

                <a href="index"><button type="button" class="btn btn-primary">Back to home</button> </a>
            </div>
            <!-- add Modal HTML -->
            <div id="addEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="ad_addproServlet" method="post"  >
                            <div class="modal-header">
                                <h4 class="modal-title">Add Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input name="image" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input name="price" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>available</label>
                                    <input name="available" type="number" class="form-control" required>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add" >
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Delete Modal HTML -->
            <div id="deleteEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form>
                            <div class="modal-header">
                                <h4 class="modal-title">Delete Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <p>Are you sure you want to delete these Records?</p>
                                <p class="text-warning"><small>This action cannot be undone.</small></p>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-danger" value="Delete">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <script type="text/javascript">
                function exportTableToExcel(tableID, filename = ''){
                    var downloadLink;
                    var dataType = 'application/vnd.ms-excel';
                    var tableSelect = document.getElementById(tableID);
                    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');

                    // Specify file name
                    filename = filename?filename+'.xls':'excel_data.xls';

                    // Create download link element
                    downloadLink = document.createElement("a");

                    document.body.appendChild(downloadLink);

                    if(navigator.msSaveOrOpenBlob){
                        var blob = new Blob(['\ufeff', tableHTML], {
                            type: dataType
                        });
                        navigator.msSaveOrOpenBlob( blob, filename);
                    }else{
                        // Create a link to the file
                        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;

                        // Setting the file name
                        downloadLink.download = filename;

                        //triggering the function
                        downloadLink.click();
                    }
                }
            </script>

            <script>
                function viewOrderDetails(orderId) {
                    window.location.href = 'ordersServlet?action=viewOrderDetails&orderId=' + orderId;
                }

                var statusChangeProcessed = {};
                var processingOrders = [];

                function updateSelectColor(selectElement) {
                    var selectedColor = selectElement.find(":selected").css("background-color");
                    selectElement.css("background-color", selectedColor);
                }

                function updateOrderStatus(orderId, statusOrder) {
                    $.ajax({
                        type: "POST",
                        url: "ordersServlet",
                        data: {
                            orderId: orderId,
                            statusOrder: statusOrder,
                            ajax: "true"
                        },
                        success: function(response) {
                            if (response.status === "success") {
                                let selectElement = $("select[name='statusOrder'][data-order-id='" + orderId + "']");
                                selectElement.val(statusOrder);
                                updateSelectColor(selectElement);

                                if (statusOrder === "Đang xử lý" || statusOrder === "Đang giao hàng") {
                                    handleStatusChange(orderId, statusOrder);
                                }
                            } else {
                                alert("Cập nhật trạng thái đơn hàng thất bại. Vui lòng thử lại.");
                            }
                        },
                        error: function() {
                            alert("Có lỗi xảy ra khi cập nhật trạng thái đơn hàng.");
                        }
                    });
                }

                function handleStatusChange(orderId, status) {
                    const now = new Date();
                    const statusChangeTime = now.getTime();

                    processingOrders.push(orderId);

                    $.ajax({
                        type: "POST",
                        url: "ordersServlet",
                        data: {
                            orderId: orderId,
                            statusOrder: status,
                            statusChangeTime: statusChangeTime,
                            ajax: "true"
                        },
                        success: function(response) {
                            if (response.status === "success") {
                                let selectElement = $("select[name='statusOrder'][data-order-id='" + orderId + "']");
                                selectElement.val(status);
                                updateSelectColor(selectElement);

                                if (status === "Đang xử lý" && !statusChangeProcessed[orderId]) {
                                    statusChangeProcessed[orderId] = true;

                                    setTimeout(() => {
                                        $.ajax({
                                            type: "POST",
                                            url: "ordersServlet",
                                            data: {
                                                orderId: orderId,
                                                statusOrder: 'Hoàn tất xác nhận',
                                                ajax: "true"
                                            },
                                            success: function(response) {
                                                if (response.status === "success") {
                                                    let selectElement = $("select[name='statusOrder'][data-order-id='" + orderId + "']");
                                                    selectElement.val('Hoàn tất xác nhận');
                                                    updateSelectColor(selectElement);
                                                    processingOrders = processingOrders.filter(id => id !== orderId);
                                                    checkAllOrdersProcessed();
                                                } else {
                                                    alert("Cập nhật trạng thái đơn hàng thất bại. Vui lòng thử lại.");
                                                }
                                            },
                                            error: function() {
                                                alert("Có lỗi xảy ra khi cập nhật trạng thái đơn hàng.");
                                            }
                                        });
                                    }, 2 * 60 * 1000);
                                } else if (status === "Đang giao hàng" && !statusChangeProcessed[orderId]) {
                                    statusChangeProcessed[orderId] = true;

                                    $.ajax({
                                        type: "GET",
                                        url: "ordersServlet",
                                        data: {
                                            action: "getShippingAddress",
                                            orderId: orderId,
                                            ajax: "true"
                                        },
                                        success: function(response) {
                                            if (response.status === "success") {
                                                const province = response.province;
                                                const delay = (province === "nội tỉnh") ? 1 * 60 * 1000 : 2 * 60 * 1000;

                                                setTimeout(() => {
                                                    $.ajax({
                                                        type: "POST",
                                                        url: "ordersServlet",
                                                        data: {
                                                            orderId: orderId,
                                                            statusOrder: 'Giao hàng thành công',
                                                            ajax: "true"
                                                        },
                                                        success: function(response) {
                                                            if (response.status === "success") {
                                                                let selectElement = $("select[name='statusOrder'][data-order-id='" + orderId + "']");
                                                                selectElement.val('Giao hàng thành công');
                                                                updateSelectColor(selectElement);
                                                                processingOrders = processingOrders.filter(id => id !== orderId);
                                                                checkAllOrdersProcessed();
                                                            } else {
                                                                alert("Cập nhật trạng thái đơn hàng thất bại. Vui lòng thử lại.");
                                                            }
                                                        },
                                                        error: function() {
                                                            alert("Có lỗi xảy ra khi cập nhật trạng thái đơn hàng.");
                                                        }
                                                    });
                                                }, delay);
                                            } else {
                                                alert("Không thể xác định được tỉnh của đơn hàng.");
                                            }
                                        },
                                        error: function() {
                                            alert("Có lỗi xảy ra khi xác định tỉnh của đơn hàng.");
                                        }
                                    });
                                }
                            } else {
                                alert("Cập nhật trạng thái đơn hàng thất bại. Vui lòng thử lại.");
                            }
                        },
                        error: function() {
                            alert("Có lỗi xảy ra khi cập nhật trạng thái đơn hàng.");
                        }
                    });
                }

                function checkAllOrdersProcessed() {
                    if (processingOrders.length === 0) {
                        alert("Tất cả đơn hàng đã được xử lý xong.");
                    }
                }

                $(document).ready(function() {
                    $("select[name='statusOrder']").each(function() {
                        updateSelectColor($(this));
                    });

                    $("select[name='statusOrder']").change(function() {
                        updateOrderStatus($(this).data("order-id"), $(this).val());
                    });
                });
            </script>






            <script src="js/manager.js" ></script>

            <!-- content-wrapper ends -->
            <!-- partial:partials/_footer.html -->
            <c:import url="includes_ad/footer.jsp"></c:import>
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
</div>
<!-- container-scroller -->
<!-- plugins:js -->
<script src="assets/vendors/js/vendor.bundle.base.js"></script>
<!-- endinject -->
<!-- Plugin js for this page -->
<script src="assets/vendors/chart.js/Chart.min.js"></script>
<script src="assets/vendors/jquery-circle-progress/js/circle-progress.min.js"></script>
<script src="assets/js/jquery.cookie.js" type="text/javascript"></script>
<!-- End plugin js for this page -->
<!-- inject:js -->
<script src="assets/js/off-canvas.js"></script>
<script src="assets/js/hoverable-collapse.js"></script>
<script src="assets/js/misc.js"></script>
<!-- endinject -->
<!-- Custom js for this page -->
<script src="assets/js/dashboard.js"></script>
<!-- End custom js for this page -->
</body>
</html>