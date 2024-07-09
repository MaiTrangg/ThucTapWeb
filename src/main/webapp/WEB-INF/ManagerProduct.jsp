    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<%--            link cho improt file excel--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css">
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
<%--    <!-- Bootstrap CSS -->--%>
<%--    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">--%>

<%--    <!-- jQuery -->--%>
<%--    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>--%>

<%--    <!-- Bootstrap JavaScript -->--%>
<%--    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>--%>

</head>
  
  <style>
         #sp  img{
                width: 200px;
                height: 120px;
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
             color: white !important;
             border: 1px solid transparent;
             background-color: #1d8c1bf2;
         }
         #productTable_info{
             margin-bottom: 20px;
         }

         .dataTables_wrapper .dataTables_paginate .paginate_button:hover {
             color: white;
             border: 1px solid #1d8c1bf2;
             background-color: #44ce42;
         }

         .dataTables_wrapper .dataTables_paginate .paginate_button:active {
             outline: none;
             background-color: #1d8c1bf2;
             color: white;
         }
  /*       css cho import file excel*/
         .upload-form {
             margin-top: 20px;
         }

         .upload-form label {
             font-weight: bold;
         }

         .upload-form .custom-file-label::after {
             content: "Browse";
         }

         .upload-form .btn-primary {
             margin-top: 10px;
         }
         .setcolorbtn{
             background: #44ce42;
             margin-bottom: 20px;
         }
         .form-group label{
             padding-top: 10px;
         }
         .custom-file{
             width: 36%;
         }
         .delete{
             color: #f17373;
         }
         .delete:hover{
             color: rgba(243, 21, 21, 0.91);
         }
         .edit{
             color: rgba(248, 248, 113, 0.99);
         }
         .edit:hover{
             color: rgba(248, 248, 14, 0.91);
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
        <c:set var="currentPage" value="managerProduct" />
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
                            <h2>Manage <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover" id="productTable">
                    <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <c:forEach items="${listP }" var="p">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="${p.productId}">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
                                <td>${p.productId}</td>
                                <td> ${p.name} </td>
                                <td>
                                    <img src= ${p.img } >
                                </td> 
                                <td> ${ p.sellingPrice } </td>
                                <td>
                                    <a href="loadInforProServlet?idpro=${p.productId }"  class="edit" ><i class="material-icons"  title="Edit">&#xE254;</i></a>
<%--                                    <a href="ad_deleteproservlet?idpro=${p.productId }" class="delete" ><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872; </i></a>--%>
                                    <a href="#deleteEmployeeModal" class="delete" id="icon-delete" data-id="${p.productId}" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872; </i></a>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

             <div class="container upload-form">
                 <h2 class="my-4">Upload Excel File</h2>
                 <form action="upload" method="post" enctype="multipart/form-data">
                     <div class="form-group">
                         <label for="file">Select Excel File:</label>
                         <div class="custom-file">
                             <input type="file" class="custom-file-input" id="file" name="file" accept=".xls,.xlsx">
                             <label class="custom-file-label" for="file">Choose file</label>
                         </div>
                     </div>
                     <button type="submit" class="btn btn-primary setcolorbtn">Upload</button>
                 </form>
             </div>
<%--                <div class="clearfix">--%>
<%--                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>--%>
<%--                    <ul class="pagination">--%>
<%--                        <li class="page-item disabled"><a href="#">Previous</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">1</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">2</a></li>--%>
<%--                        <li class="page-item active"><a href="#" class="page-link">3</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">4</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">5</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">Next</a></li>--%>
<%--                    </ul>--%>
<%--                </div>--%>
            </div>
            <a href="index"><button type="button" class="btn btn-primary">Back to home</button> </a>
<!-- ad_addproServlet -->
        </div>
        <!-- add Modal HTML -->
        <div id="addEmployeeModal" class="modal fade" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="ad_addproServlet" method="post" enctype="multipart/form-data"  >
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="file" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>available</label>
                                <input name="available" type="number" class="form-control" required>
</div>
                             <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach  items="${categoryList}" var="cate">
                                        <option value="${cate.quantity}">${cate.category}</option>
                                    </c:forEach>
                                </select>
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
                    <form method="post">
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
<script>
    $(document).ready(function() {
        var table = $('#productTable').DataTable({
            paging: true,
            searching: true,
            ordering: true
        });

        // Array to store the selected checkboxes
        var selected = [];

        // Handle click on "Select all" control
        $('#selectAll').on('click', function() {
            var rows = table.rows({ 'search': 'applied' }).nodes();
            $('input[type="checkbox"]', rows).prop('checked', this.checked);

            // Add all selected checkboxes to the selected array
            if (this.checked) {
                selected = [];
                $('input[type="checkbox"]', rows).each(function() {
                    selected.push($(this).val());
                });
            } else {
                selected = [];
            }

            console.log('Selected values:', selected);
        });

        // Handle click on individual checkbox to maintain "Select all" control state
        $('#productTable tbody').on('change', 'input[type="checkbox"]', function() {
            if (!this.checked) {
                var el = $('#selectAll').get(0);
                if (el && el.checked && ('indeterminate' in el)) {
                    el.indeterminate = true;
                }

                var value = $(this).val();
                var index = selected.indexOf(value);
                if (index !== -1) {
                    selected.splice(index, 1);
                }
            } else {
                var value = $(this).val();
                if (selected.indexOf(value) === -1) {
                    selected.push(value);
                }
            }

            console.log('Selected values:', selected);
        });
        var productId =0;
        // Handle form submission event for the modal
        $('#deleteEmployeeModal').on('show.bs.modal', function(event) {
            var anchor = $(event.relatedTarget);  // Button that triggered the modal
            var modal = $(this);

            if (anchor.hasClass('delete')) {
                // Case: deleting a single product
                 productId = anchor.data('id'); // Extract info from data-* attributes
                console.log("pro1: "+productId);
                if (productId) {
                    modal.find('.modal-body p').text('Are you sure you want to delete the product with ID: ' + productId + '?');
                    modal.find('form').attr('action', 'ad_deleteproservlet?idpro=' + productId);
                } else {
                    modal.find('.modal-body p').text('Please select at least one product to delete.');
                }
            } else {
                // Case: deleting multiple products
                if (selected.length > 0) {
                    var idString = selected.join(',');
                    modal.find('form').attr('action', 'ad_deleteproservlet?idpro=' + idString);
                    modal.find('.modal-body p').text('Are you sure you want to delete the selected products?');
                } else {
                    modal.find('.modal-body p').text('Please select at least one product to delete.');
                }
            }
        });

        $('#deleteEmployeeModal').find('form').on('submit', function(event) {
            if (selected.length === 0 &&productId===0) {
                event.preventDefault();
                alert('Please select at least one product to delete.');
            }
        });
    });

</script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // Update the file input label with the selected file name
        $('.custom-file-input').on('change', function(event) {
            var inputFile = event.currentTarget;
            $(inputFile).parent()
                .find('.custom-file-label')
                .html(inputFile.files[0].name);
        });
    </script>
  </body>
</html>
