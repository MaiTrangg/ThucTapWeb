<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap CRUD Data Table for Database with Modal Form</title> -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- link modal -->
    <!-- Bootstrap CSS -->
    <!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">  -->

    <!-- jQuery -->
<%--    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>--%>

    <!--  Popper.js -->
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>--%>

    <!-- Bootstrap JS-->
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>--%>

    <c:import url="includes_ad/header.jsp"></c:import>
    <link href="style.css" rel="stylesheet" />
</head>

<style>
    #sp  img{
        width: 200px;
        height: 120px;
    }
    /* DataTable Styling */
    #logTable_wrapper {
        width: 100%;
        margin: 20px 0;
    }

    #logTable {
        width: 100%;
        border-collapse: collapse;
        font-size: 16px;
        text-align: left;
    }

    #logTable thead th {
        background-color: #009879;
        color: #ffffff;
        text-align: left;
        font-weight: bold;
        padding: 12px 15px;
        cursor: pointer;
        position: relative;
    }

    #logTable thead th.sorting::after,
    #logTable thead th.sorting_asc::after,
    #logTable thead th.sorting_desc::after {
        content: '';
        position: absolute;
        right: 10px;
        border: 4px solid transparent;
        border-width: 4px 4px 0 4px;
    }

    #logTable thead th.sorting::after {
        border-top-color: #ffffff;
        border-bottom: none;
        top: 50%;
        margin-top: -2px;
    }

    #logTable thead th.sorting_asc::after {
        border-bottom-color: #ffffff;
        top: 50%;
        margin-top: -6px;
    }

    #logTable thead th.sorting_desc::after {
        border-top-color: #ffffff;
        top: 50%;
        margin-top: 4px;
    }

    #logTable tbody tr:nth-of-type(even) {
        background-color: #f3f3f3;
    }

    #logTable tbody tr:hover {
        background-color: #f1f1f1;
    }

    #logTable tbody td {
        padding: 12px 15px;
        border-bottom: 1px solid #dddddd;
    }

    /* DataTables pagination styling */
    .dataTables_wrapper .dataTables_paginate .paginate_button {
        padding: 0.5em 1em;
        margin-left: 2px;
        border-radius: 3px;
        border: 1px solid transparent;
        background: #009879;
        color: white;
        cursor: pointer;
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button:hover {
        background: #007d68;
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button.current {
        background: #007d68;
        color: white;
    }

    .dataTables_wrapper .dataTables_length,
    .dataTables_wrapper .dataTables_filter {
        margin-bottom: 20px;
    }

    /* Responsive Table */
    @media (max-width: 600px) {
        #logTable thead {
            display: none;
        }

        #logTable,
        #logTable tbody,
        #logTable tr,
        #logTable td {
            display: block;
            width: 100%;
        }

        #logTable tr {
            margin-bottom: 15px;
        }

        #logTable td {
            text-align: right;
            padding-left: 50%;
            position: relative;
        }

        #logTable td::before {
            content: attr(data-label);
            position: absolute;
            left: 0;
            width: 50%;
            padding-left: 15px;
            font-weight: bold;
            text-align: left;
        }
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
        <c:set var="currentPage" value="managerLog" />
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
                                <h2>Manage <b>Logs</b></h2>
                            </div>
                            <%--                            <div class="col-sm-6">--%>
                            <%--                                <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>--%>
                            <%--                                <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>--%>
                            <%--                            </div>--%>
                        </div>
                    </div>

                    <table id="logTable">
                        <thead>
                        <tr>
                            <th>logTime</th>
                            <th>logLevel</th>
                            <th>beforeMessage</th>
                            <th>afterMessage</th>
                            <th>userId</th>
                            <th>sessionId</th>
                            <th>ipAddress</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listLog}" var="l">
                            <tr>
                                <td data-label="logTime">${l.logTime}</td>
                                <td data-label="logLevel">${l.logLevel}</td>
                                <td data-label="beforeMessage">${l.beforeMessage}</td>
                                <td data-label="afterMessage">${l.afterMessage}</td>
                                <td data-label="userId">${l.userId}</td>
                                <td data-label="sessionId">${l.sessionId}</td>
                                <td data-label="ipAddress">${l.ipAddress}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <a href="index.jsp"><button type="button" class="btn btn-primary">Back to home</button> </a>
                <!-- ad_addproServlet -->
            </div>

<%--            <script src="js/manager.js" ></script>--%>

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
<script>
    $('#logTable').DataTable({
        paging: true,
        searching: true,
        ordering: true
    });

</script>

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
