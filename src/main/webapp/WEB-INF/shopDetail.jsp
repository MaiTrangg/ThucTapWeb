<%--
  Created by IntelliJ IDEA.
  User: Thanh Truc
  Date: 02-Mar-24
  Time: 11:21 PM
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
<c:set var="currentPage" value="shopDetail" />
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
  <h1 class="text-center text-white display-6">Shop Detail</h1>
  <ol class="breadcrumb justify-content-center mb-0">
    <li class="breadcrumb-item"><a href="#">Home</a></li>
    <li class="breadcrumb-item"><a href="#">Pages</a></li>
    <li class="breadcrumb-item active text-white">Shop Detail</li>
  </ol>
</div>
<!-- Single Page Header End -->


<!-- Single Product Start -->
<div class="container-fluid py-5 mt-5">
  <div class="container py-5">
    <div class="row g-4 mb-5">
      <div class="col-lg-8 col-xl-9">
        <div class="row g-4">
          <div class="col-lg-6">
            <div class="border rounded">
              <a href="#">
                <img src="img/single-item.jpg" class="img-fluid rounded" alt="Image">
              </a>
            </div>
          </div>
          <div class="col-lg-6">
            <h4 class="fw-bold mb-3">Brocoli</h4>
            <p class="mb-3">Category: Vegetables</p>
            <h5 class="fw-bold mb-3">3,35 $</h5>
            <div class="d-flex mb-4">
              <i class="fa fa-star text-secondary"></i>
              <i class="fa fa-star text-secondary"></i>
              <i class="fa fa-star text-secondary"></i>
              <i class="fa fa-star text-secondary"></i>
              <i class="fa fa-star"></i>
            </div>
            <p class="mb-4">The generated Lorem Ipsum is therefore always free from repetition injected humour, or non-characteristic words etc.</p>
            <p class="mb-4">Susp endisse ultricies nisi vel quam suscipit. Sabertooth peacock flounder; chain pickerel hatchetfish, pencilfish snailfish</p>
            <div class="input-group quantity mb-5" style="width: 100px;">
              <div class="input-group-btn">
                <button class="btn btn-sm btn-minus rounded-circle bg-light border" >
                  <i class="fa fa-minus"></i>
                </button>
              </div>
              <input type="text" class="form-control form-control-sm text-center border-0" value="1">
              <div class="input-group-btn">
                <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                  <i class="fa fa-plus"></i>
                </button>
              </div>
            </div>
            <a href="#" class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
          </div>
          <div class="col-lg-12">
            <nav>
              <div class="nav nav-tabs mb-3">
                <button class="nav-link active border-white border-bottom-0" type="button" role="tab"
                        id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
                        aria-controls="nav-about" aria-selected="true">Description</button>
                <button class="nav-link border-white border-bottom-0" type="button" role="tab"
                        id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission"
                        aria-controls="nav-mission" aria-selected="false">Reviews</button>
              </div>
            </nav>
            <div class="tab-content mb-5">
              <div class="tab-pane active" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
                <p>The generated Lorem Ipsum is therefore always free from repetition injected humour, or non-characteristic words etc.
                  Susp endisse ultricies nisi vel quam suscipit </p>
                <p>Sabertooth peacock flounder; chain pickerel hatchetfish, pencilfish snailfish filefish Antarctic
                  icefish goldeye aholehole trumpetfish pilot fish airbreathing catfish, electric ray sweeper.</p>
                <div class="px-2">
                  <div class="row g-4">
                    <div class="col-6">
                      <div class="row bg-light align-items-center text-center justify-content-center py-2">
                        <div class="col-6">
                          <p class="mb-0">Weight</p>
                        </div>
                        <div class="col-6">
                          <p class="mb-0">1 kg</p>
                        </div>
                      </div>
                      <div class="row text-center align-items-center justify-content-center py-2">
                        <div class="col-6">
                          <p class="mb-0">Country of Origin</p>
                        </div>
                        <div class="col-6">
                          <p class="mb-0">Agro Farm</p>
                        </div>
                      </div>
                      <div class="row bg-light text-center align-items-center justify-content-center py-2">
                        <div class="col-6">
                          <p class="mb-0">Quality</p>
                        </div>
                        <div class="col-6">
                          <p class="mb-0">Organic</p>
                        </div>
                      </div>
                      <div class="row text-center align-items-center justify-content-center py-2">
                        <div class="col-6">
                          <p class="mb-0">Сheck</p>
                        </div>
                        <div class="col-6">
                          <p class="mb-0">Healthy</p>
                        </div>
                      </div>
                      <div class="row bg-light text-center align-items-center justify-content-center py-2">
                        <div class="col-6">
                          <p class="mb-0">Min Weight</p>
                        </div>
                        <div class="col-6">
                          <p class="mb-0">250 Kg</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="tab-pane" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">
                <div class="d-flex">
                  <img src="img/avatar.jpg" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
                  <div class="">
                    <p class="mb-2" style="font-size: 14px;">April 12, 2024</p>
                    <div class="d-flex justify-content-between">
                      <h5>Jason Smith</h5>
                      <div class="d-flex mb-3">
                        <i class="fa fa-star text-secondary"></i>
                        <i class="fa fa-star text-secondary"></i>
                        <i class="fa fa-star text-secondary"></i>
                        <i class="fa fa-star text-secondary"></i>
                        <i class="fa fa-star"></i>
                      </div>
                    </div>
                    <p>The generated Lorem Ipsum is therefore always free from repetition injected humour, or non-characteristic
                      words etc. Susp endisse ultricies nisi vel quam suscipit </p>
                  </div>
                </div>
                <div class="d-flex">
                  <img src="img/avatar.jpg" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
                  <div class="">
                    <p class="mb-2" style="font-size: 14px;">April 12, 2024</p>
                    <div class="d-flex justify-content-between">
                      <h5>Sam Peters</h5>
                      <div class="d-flex mb-3">
                        <i class="fa fa-star text-secondary"></i>
                        <i class="fa fa-star text-secondary"></i>
                        <i class="fa fa-star text-secondary"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                      </div>
                    </div>
                    <p class="text-dark">The generated Lorem Ipsum is therefore always free from repetition injected humour, or non-characteristic
                      words etc. Susp endisse ultricies nisi vel quam suscipit </p>
                  </div>
                </div>
              </div>
              <div class="tab-pane" id="nav-vision" role="tabpanel">
                <p class="text-dark">Tempor erat elitr rebum at clita. Diam dolor diam ipsum et tempor sit. Aliqu diam
                  amet diam et eos labore. 3</p>
                <p class="mb-0">Diam dolor diam ipsum et tempor sit. Aliqu diam amet diam et eos labore.
                  Clita erat ipsum et lorem et sit</p>
              </div>
            </div>
          </div>
          <form action="#">
            <h4 class="mb-5 fw-bold">Leave a Reply</h4>
            <div class="row g-4">
              <div class="col-lg-6">
                <div class="border-bottom rounded">
                  <input type="text" class="form-control border-0 me-4" placeholder="Yur Name *">
                </div>
              </div>
              <div class="col-lg-6">
                <div class="border-bottom rounded">
                  <input type="email" class="form-control border-0" placeholder="Your Email *">
                </div>
              </div>
              <div class="col-lg-12">
                <div class="border-bottom rounded my-4">
                  <textarea name="" id="" class="form-control border-0" cols="30" rows="8" placeholder="Your Review *" spellcheck="false"></textarea>
                </div>
              </div>
              <div class="col-lg-12">
                <div class="d-flex justify-content-between py-3 mb-5">
                  <div class="d-flex align-items-center">
                    <p class="mb-0 me-3">Please rate:</p>
                    <div class="d-flex align-items-center" style="font-size: 12px;">
                      <i class="fa fa-star text-muted"></i>
                      <i class="fa fa-star"></i>
                      <i class="fa fa-star"></i>
                      <i class="fa fa-star"></i>
                      <i class="fa fa-star"></i>
                    </div>
                  </div>
                  <a href="#" class="btn border border-secondary text-primary rounded-pill px-4 py-3"> Post Comment</a>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
      <div class="col-lg-4 col-xl-3">
        <div class="row g-4 fruite">
          <div class="col-lg-12">
            <div class="input-group w-100 mx-auto d-flex mb-4">
              <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
              <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
            </div>
            <div class="mb-4">
              <h4>Categories</h4>
              <ul class="list-unstyled fruite-categorie">
                <li>
                  <div class="d-flex justify-content-between fruite-name">
                    <a href="#"><i class="fas fa-apple-alt me-2"></i>Apples</a>
                    <span>(3)</span>
                  </div>
                </li>
                <li>
                  <div class="d-flex justify-content-between fruite-name">
                    <a href="#"><i class="fas fa-apple-alt me-2"></i>Oranges</a>
                    <span>(5)</span>
                  </div>
                </li>
                <li>
                  <div class="d-flex justify-content-between fruite-name">
                    <a href="#"><i class="fas fa-apple-alt me-2"></i>Strawbery</a>
                    <span>(2)</span>
                  </div>
                </li>
                <li>
                  <div class="d-flex justify-content-between fruite-name">
                    <a href="#"><i class="fas fa-apple-alt me-2"></i>Banana</a>
                    <span>(8)</span>
                  </div>
                </li>
                <li>
                  <div class="d-flex justify-content-between fruite-name">
                    <a href="#"><i class="fas fa-apple-alt me-2"></i>Pumpkin</a>
                    <span>(5)</span>
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <div class="col-lg-12">
            <h4 class="mb-4">Featured products</h4>
            <div class="d-flex align-items-center justify-content-start">
              <div class="rounded" style="width: 100px; height: 100px;">
                <img src="img/featur-1.jpg" class="img-fluid rounded" alt="Image">
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
              <div class="rounded" style="width: 100px; height: 100px;">
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
              <div class="rounded" style="width: 100px; height: 100px;">
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
            <div class="d-flex align-items-center justify-content-start">
              <div class="rounded me-4" style="width: 100px; height: 100px;">
                <img src="img/vegetable-item-4.jpg" class="img-fluid rounded" alt="">
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
                <img src="img/vegetable-item-5.jpg" class="img-fluid rounded" alt="">
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
                <img src="img/vegetable-item-6.jpg" class="img-fluid rounded" alt="">
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
    </div>
    <h1 class="fw-bold mb-0">Related products</h1>
    <div class="vesitable">
      <div class="owl-carousel vegetable-carousel justify-content-center">
        <div class="border border-primary rounded position-relative vesitable-item">
          <div class="vesitable-img">
            <img src="img/vegetable-item-6.jpg" class="img-fluid w-100 rounded-top" alt="">
          </div>
          <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
          <div class="p-4 pb-0 rounded-bottom">
            <h4>Parsely</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
            <div class="d-flex justify-content-between flex-lg-wrap">
              <p class="text-dark fs-5 fw-bold">$4.99 / kg</p>
              <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
            </div>
          </div>
        </div>
        <div class="border border-primary rounded position-relative vesitable-item">
          <div class="vesitable-img">
            <img src="img/vegetable-item-1.jpg" class="img-fluid w-100 rounded-top" alt="">
          </div>
          <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
          <div class="p-4 pb-0 rounded-bottom">
            <h4>Parsely</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
            <div class="d-flex justify-content-between flex-lg-wrap">
              <p class="text-dark fs-5 fw-bold">$4.99 / kg</p>
              <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
            </div>
          </div>
        </div>
        <div class="border border-primary rounded position-relative vesitable-item">
          <div class="vesitable-img">
            <img src="img/vegetable-item-3.png" class="img-fluid w-100 rounded-top bg-light" alt="">
          </div>
          <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
          <div class="p-4 pb-0 rounded-bottom">
            <h4>Banana</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
            <div class="d-flex justify-content-between flex-lg-wrap">
              <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
              <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
            </div>
          </div>
        </div>
        <div class="border border-primary rounded position-relative vesitable-item">
          <div class="vesitable-img">
            <img src="img/vegetable-item-4.jpg" class="img-fluid w-100 rounded-top" alt="">
          </div>
          <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
          <div class="p-4 pb-0 rounded-bottom">
            <h4>Bell Papper</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
            <div class="d-flex justify-content-between flex-lg-wrap">
              <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
              <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
            </div>
          </div>
        </div>
        <div class="border border-primary rounded position-relative vesitable-item">
          <div class="vesitable-img">
            <img src="img/vegetable-item-5.jpg" class="img-fluid w-100 rounded-top" alt="">
          </div>
          <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
          <div class="p-4 pb-0 rounded-bottom">
            <h4>Potatoes</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
            <div class="d-flex justify-content-between flex-lg-wrap">
              <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
              <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
            </div>
          </div>
        </div>
        <div class="border border-primary rounded position-relative vesitable-item">
          <div class="vesitable-img">
            <img src="img/vegetable-item-6.jpg" class="img-fluid w-100 rounded-top" alt="">
          </div>
          <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
          <div class="p-4 pb-0 rounded-bottom">
            <h4>Parsely</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
            <div class="d-flex justify-content-between flex-lg-wrap">
              <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
              <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
            </div>
          </div>
        </div>
        <div class="border border-primary rounded position-relative vesitable-item">
          <div class="vesitable-img">
            <img src="img/vegetable-item-5.jpg" class="img-fluid w-100 rounded-top" alt="">
          </div>
          <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
          <div class="p-4 pb-0 rounded-bottom">
            <h4>Potatoes</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
            <div class="d-flex justify-content-between flex-lg-wrap">
              <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
              <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
            </div>
          </div>
        </div>
        <div class="border border-primary rounded position-relative vesitable-item">
          <div class="vesitable-img">
            <img src="img/vegetable-item-6.jpg" class="img-fluid w-100 rounded-top" alt="">
          </div>
          <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">Vegetable</div>
          <div class="p-4 pb-0 rounded-bottom">
            <h4>Parsely</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
            <div class="d-flex justify-content-between flex-lg-wrap">
              <p class="text-dark fs-5 fw-bold">$7.99 / kg</p>
              <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Single Product End -->

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
</body>
</html>
