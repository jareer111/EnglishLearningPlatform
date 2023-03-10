<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abdullo
  Date: 2/13/23
  Time: 12:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zxx" class="no-js">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon-->
    <link rel="shortcut icon" href="views/user/img/fav.png">
    <!-- Author Meta -->
    <meta name="author" content="codepixer">
    <!-- Meta Description -->
    <meta name="description" content="">
    <!-- Meta Keyword -->
    <meta name="keywords" content="">
    <!-- meta character set -->
    <meta charset="UTF-8">
    <!-- Site Title -->
    <title>Book</title>

    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700" rel="stylesheet">
    <!--
    CSS
    ============================================= -->
    <link rel="stylesheet" href="views/user/css/linearicons.css">
    <link rel="stylesheet" href="views/user/css/font-awesome.min.css">
    <link rel="stylesheet" href="views/user/css/bootstrap.css">
    <link rel="stylesheet" href="views/user/css/magnific-popup.css">
    <link rel="stylesheet" href="views/user/css/nice-select.css">
    <link rel="stylesheet" href="views/user/css/animate.min.css">
    <link rel="stylesheet" href="views/user/css/owl.carousel.css">
    <link rel="stylesheet" href="views/user/css/main.css">
</head>
<body>

<header id="header" id="home1">
    <div class="container">
        <div class="row align-items-center justify-content-between d-flex">
            <div id="logo">
                <a href="/"><img src="views/user/img/logo.png" alt="Logo" title="" width="12%"/></a>
            </div>
            <nav id="nav-menu-container">
                <ul class="nav-menu ">
                    <li class="menu-active"><a href="/">Home</a></li>

                    <li class="menu-has-children"><a href="#">Practises</a>
                        <ul>
                            <li><a href="/reading-list-page">Reading</a></li>
                            <li><a href="/grammar-list-page">Grammar</a></li>
                            <li><a href="/vocabulary">Vocabulary</a></li>
                        </ul>
<%--                            <% if (request.getCookies() != null && Arrays.stream(request.getCookies()).anyMatch(cookie -> cookie.getName().equalsIgnoreCase("remember_me"))) { %>--%>
                            <% if (session.getAttribute("user_id") != null) { %>
                    <li class="menu-has-children"><a href="#">Logged in</a>
                        <ul>
                            <li><a href="/profile">Go to Profile</a></li>
                            <li><a href="/logout">Log Out</a></li>
                        </ul>
                    </li>
                    <% } else { %>
                    <li><a href="/login">Sign in</a></li>
                    <% } %>

                </ul>
            </nav><!-- #nav-menu-container -->
        </div>
    </div>
</header><!-- #header -->


<!-- start banner Area -->
<section class="banner-area" id="home">
    <div class="container">
        <div class="row fullscreen d-flex align-items-center justify-content-start">
            <div class="banner-content col-lg-7">
                <%--        <h5 class="text-white text-uppercase">Tafakkoor Books</h5>--%>
                <h1 class="text">
                    Increase your knowledge <br>with us
                </h1>
                <p class="text-white pt-20 pb-20">
                    Success is the sum of small efforts repeated day in and day out
                <div style="color: white">
                    <i><strong> Robert Collier</strong></i>
                </div>
                </p>
                <a href="#" class="primary-btn text-uppercase">Start learning!</a>
            </div>
            <div class="col-lg-5 banner-right">
                <img class="img-fluid" src="views/user/img/header-img.png" alt="">
            </div>
        </div>
    </div>
</section>
<!-- End banner Area -->


<!-- start footer Area -->
<footer class="footer-area section-gap">
    <div class="container">
        <div class="row">
            <div class="col-lg-5 col-md-6 col-sm-6">
                <div class="single-footer-widget">
                    <h6>About Us</h6>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                        labore dolore magna aliqua.
                    </p>
                    <p class="footer-text">
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                        All rights reserved | Made with <i class="fa fa-heart-o" aria-hidden="true"></i> by Tafakkoor
                        Team</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    </p>
                </div>
            </div>
            <div class="col-lg-5  col-md-6 col-sm-6">
                <div class="single-footer-widget">
                    <h6>Newsletter</h6>
                    <p>Stay update with our latest</p>
                    <div class="" id="mc_embed_signup">
                        <form target="_blank" novalidate="true"
                              action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
                              method="get" class="form-inline">
                            <input class="form-control" name="EMAIL" placeholder="Enter Email"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Email '"
                                   required="" type="email">
                            <button class="click-btn btn btn-default"><i class="fa fa-long-arrow-right"
                                                                         aria-hidden="true"></i></button>
                            <div style="position: absolute; left: -5000px;">
                                <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value="" type="text">
                            </div>

                            <div class="info"></div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-6 col-sm-6 social-widget">
                <div class="single-footer-widget">
                    <h6>Follow Us</h6>
                    <p>Let us be social</p>
                    <div class="footer-social d-flex align-items-center">
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-dribbble"></i></a>
                        <a href="#"><i class="fa fa-behance"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- End footer Area -->

<script src="views/user/js/vendor/jquery-2.2.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="views/user/js/vendor/bootstrap.min.js"></script>
<script type="text/javascript"
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
<script src="views/user/js/easing.min.js"></script>
<script src="views/user/js/hoverIntent.js"></script>
<script src="views/user/js/superfish.min.js"></script>
<script src="views/user/js/jquery.ajaxchimp.min.js"></script>
<script src="views/user/js/jquery.magnific-popup.min.js"></script>
<script src="views/user/js/owl.carousel.min.js"></script>
<script src="views/user/js/jquery.sticky.js"></script>
<script src="views/user/js/jquery.nice-select.min.js"></script>
<script src="views/user/js/parallax.min.js"></script>
<script src="views/user/js/waypoints.min.js"></script>
<script src="views/user/js/jquery.counterup.min.js"></script>
<script src="views/user/js/mail-script.js"></script>
<script src="views/user/js/main.js"></script>
</body>
</html>



