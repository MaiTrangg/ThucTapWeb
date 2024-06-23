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
        </style>
  <body>
    <div class="container-scroller">
      <div class="row p-0 m-0 proBanner" id="proBanner">
        <div class="col-md-12 p-0 m-0">
          <div class="card-body card-body-padding d-flex align-items-center justify-content-between">
            <div class="ps-lg-1">
              <div class="d-flex align-items-center justify-content-between">
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
        <c:set var="currentPage" value="managerCustomer" />
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
                            <h2>Manage <b>User</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Customer</span></a>
                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
                        </div>
                    </div>
                </div>
                <table id="1" class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                            <th>User</th>
                            <th>isAdmin</th>
                            <th>Email</th>
                            <th>numberphone</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <c:forEach items="${listCus }" var="c">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
                                
                                <td> ${c.username} </td>
                                <td>
                                   ${c.isAdmin }
                                </td> 
                                <td>${c.email}</td>
                                <td> ${ c.numberPhone } </td>
                                <td>
                                    <a href="loadInforCusServlet?username_c=${c.username }&email_c=${c.email }"  class="edit" ><i class="material-icons"  title="Edit">&#xE254;</i></a>
                                    <a href="ad_deleteCusServlet?username_c=${c.username }&email_c=${c.email }" class="delete" ><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872; </i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
                <button onclick="exportTableToExcel('1', 'product_data')">Export Table Data To Excel File</button>
 
                <div class="clearfix">
                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
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
            <a href="index"><button type="button" class="btn btn-primary">Back to home</button> </a>
<!-- ad_addproServlet -->
        </div>
        <!-- add Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="ad_addCusServlet" method="post"  >
                        <div class="modal-header">						
                            <h4 class="modal-title">Add User</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>User Name</label>
                                <input name="username" type="text" class="form-control" required>
                            </div>
                            
                            <div class="form-group">
                                <label>Password</label>
                                <input name="pass" type="text" class="form-control" required>
                            </div>
                            
                            <div class="form-group">
                                <div>
                                <input name="isadmin" type="radio"  value="1" required>
                                <label>is Admin</label>
                                </div> 
                                <div>
                                <input name="isadmin" type="radio" value="0" required checked="checked">
                                <label>is User</label>
                                </div> 
                                
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input name="email" type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Numberphone</label>
                                <input name="numberphone" type="tel" class="form-control" required>
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