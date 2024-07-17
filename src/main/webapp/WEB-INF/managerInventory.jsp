<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap CRUD Data Table with Tabs</title>
<%--    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">--%>
<%--    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/dataTables.bulma.min.css">--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">--%>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>--%>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>--%>
<%--    <link href="style.css" rel="stylesheet" />--%>
<%--    <!-- DataTables CSS -->--%>
<%--    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">--%>
    <!-- Font và Icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">

    <!-- CSS tùy chỉnh -->
    <link href="style.css" rel="stylesheet" />

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper.js (cần thiết cho Bootstrap 4) -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <title>Bootstrap Tabs Example</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">



</head>

<style>
    /* Main container styling */
    .main {
        display: flex;
        flex-direction: row;
        margin: 0;
        padding: 0;
    }

    /* Sidebar styling */
    .sidebar {
        width: 250px;
        background-color: #f8f9fa;
        padding: 15px;
        position: fixed;
        height: 100vh;
    }

    /* Main panel styling */
    .main-panel {
        margin-left: 250px;
        padding: 20px;
        flex: 1;
        margin-top: 50px;
    }

    /* Tabs styling */
    .nav-tabs {
        border-bottom: 2px solid #ddd;
        margin-bottom: 20px;
    }

    .nav-tabs .nav-item .nav-link {
        border: 1px solid transparent;
        border-radius: 4px 4px 0 0;
        padding: 10px 15px;
        color: #495057;
        font-weight: 600;
    }

    .nav-tabs .nav-item .nav-link.active {
        color: #44ce42;
        background-color: #fff;
        border-color: #ddd #ddd #fff;
    }

    /* Tab content styling */
    .tab-content {
        padding: 15px;
        border: 1px solid #ddd;
        border-radius: 0 0 4px 4px;
        background-color: #fff;
    }

    /* Table styling within tabs */
    .table-wrapper {
        margin-top: 20px;
    }

    .table-title h2 {
        margin: 0;
        font-size: 24px;
        font-weight: 600;
    }
    /* Styling the DataTables pagination buttons */
    .dataTables_wrapper .dataTables_paginate .paginate_button {
        padding: 0.5em 1em;
        margin-left: 2px;
        border-radius: 4px;
        border: 1px solid transparent;
        color: white;
        background-color: #44ce42;
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button.current,
    .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
        /*color: white !important;*/
        border: 1px solid transparent;
        background-color: #1d8c1bf2 !important;
    }
    #productTable_info{
        margin-bottom: 20px;
    }



    .dataTables_wrapper .dataTables_paginate .paginate_button:active {
        outline: none;
        background-color: #1d8c1bf2;
        color: white;
    }

    #logTable {
        width: 100%;
        border-collapse: collapse;
        font-size: 16px;
        text-align: left;
    }



    img {
        max-width: 80px;
        height: 80px;
        border-radius: 10px;
    }

    /* Button styling */
    .btn-primary {
        background-color:  #44ce42;
        border-color:  #44ce42;
    }

    .btn-primary:hover {
        background-color: #44ce42;
        border-color: #1d8c1bf2;
    }
</style>

<body>
<div class="container-scroller">
    <!-- Navigation and Header -->
    <c:import url="includes_ad/header.jsp"></c:import>

    <!-- Navigation and Sidebar -->
    <c:import url="includes_ad/navbar.jsp"></c:import>
    <c:set var="currentPage" value="managerInventory" />
    <div class="main">
        <c:import url="includes_ad/sidebar.jsp">
            <c:param name="currentPage" value="${currentPage}" />
        </c:import>

        <!-- Main Content -->
        <div class="main-panel">
            <div class="container" id="sp">
                <!-- Tabs -->
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-bs-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " id="profile-tab" data-bs-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="contact-tab" data-bs-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Contact</a>
                    </li>
                </ul>
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <h2 style="margin-bottom: 20px">Danh sách sản phẩm không có đơn trong 3 tháng gần nhất</h2>
                                    </div>
                                </div>
                            </div>
                            <table id="ProNotOrder" class="display">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Category</th>
                                    <th>Quantity</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listProNotOrdered }" var="p">
                                    <tr>
                                        <td>${p.product.productId}</td>
                                        <td>
                                            <img src="${p.product.img}">
                                        </td>
                                        <td>${p.product.name}</td>
                                        <td>${p.product.descriptionP}</td>
                                        <td>${p.product.sellingPrice}</td>
                                        <td>${p.product.category.category}</td>
                                        <td>${p.quantity}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <h2 style="margin-bottom: 20px">Danh sách sản phẩm cần nhập</h2>
                                    </div>
                                </div>
                            </div>
                            <table id="needImported" class="display">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Category</th>
                                    <th>NeedImported</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listNeedImported }" var="p">
                                    <tr>
                                        <td>${p.product.productId}</td>
                                        <td>
                                            <img src="${p.product.img}">
                                        </td>
                                        <td>${p.product.name}</td>
                                        <td>${p.product.descriptionP}</td>
                                        <td>${p.product.sellingPrice}</td>
                                        <td>${p.product.category.category}</td>
                                        <td>${p.quantity}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <button  id="exportButton"  class="export-css">Export Table Product To Excel File</button>
                    </div>
                    <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                        <!-- Content for Contact tab -->
                        <h3>Contact</h3>
                        <p>Content for the Contact tab goes here.</p>
                    </div>
                </div>
                <a href="index"><button type="button" class="btn btn-primary">Back to home</button></a>
            </div>
            <!-- Footer -->
            <c:import url="includes_ad/footer.jsp"></c:import>
        </div>

    </div>
</div>



<!-- Include JavaScript files -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>
<script>
    $(document).ready(function() {
        $('#ProNotOrder').DataTable({
            paging: true,
            searching: true,
            ordering: true
        });
        $('#needImported').DataTable({
            paging: true,
            searching: true,
            ordering: true
        });
    });
</script>

<script type="text/javascript">
    $(document).ready(function() {
        function exportTableToExcel(tableID, filename = '') {
            var table = $('#' + tableID).DataTable();
            var wb = XLSX.utils.book_new();
            var ws_data = [];
            var headers = [];

            // Lấy tiêu đề của bảng
            $('#' + tableID + ' thead th').each(function() {
                headers.push($(this).text());
            });
            ws_data.push(headers);

            // Lấy tất cả dữ liệu từ DataTables
            table.rows({search: 'applied'}).every(function() {
                var rowData = this.nodes().to$().find('td');
                var row = [];
                rowData.each(function(index, td) {
                    if (index === 1) { // Nếu là cột ảnh
                        var img = $(td).find('img').attr('src');
                        row.push(img);
                    } else {
                        var text = $(td).text();
                        row.push(text);
                    }
                });
                ws_data.push(row);
            });

            var ws = XLSX.utils.aoa_to_sheet(ws_data);
            XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

            var wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });

            function s2ab(s) {
                var buf = new ArrayBuffer(s.length);
                var view = new Uint8Array(buf);
                for (var i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
                return buf;
            }

            var downloadLink = document.createElement("a");
            document.body.appendChild(downloadLink);
            filename = filename ? filename + '.xlsx' : 'product_data.xlsx';

            var blob = new Blob([s2ab(wbout)], { type: 'application/octet-stream' });
            var url = URL.createObjectURL(blob);
            downloadLink.href = url;
            downloadLink.download = filename;
            downloadLink.click();
            document.body.removeChild(downloadLink);
        }

        $('#exportButton').click(function() {
            exportTableToExcel('needImported', 'product_data');
        });
    });
</script>


</body>
</html>
