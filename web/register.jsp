<%--
  Created by IntelliJ IDEA.
  User: chenan
  Date: 2019/4/12
  Time: 下午6:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>
<html>
<head>
	<meta http-equiv="content-type" content="txt/html; charset=utf-8" />
    <title></title>

    <!-- mobile responsive meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <link rel="stylesheet" href="css/common-style.css">
    <link rel="stylesheet" href="css/responsive.css">

</head>
<body class="checkout-pages">
<div class="preloader"></div>
<header class="header clearfix">
    <div class="main-header stricky bubble">
        <div class="container">
            <div class="logo pull-left">
                <a href="index.html">
                    <img src="images/logo/1.png" alt="Awesome Image">
                </a>
            </div>

            <div class="nav-outer">
                <div class="header-top">
                    <div class="contact">
                        <span class="icon fa fa-phone"></span>欢 迎 你
                    </div>
                    <div class="register">
                        <a href="register.jsp">Register</a>
                        <a href="login.jsp">Login </a>
                    </div>
                    <div id="top-search" class="top-search">
                        <span class="flaticon-search search"></span>
                        <ul class="search-box">
                            <li>
                                <form action="#">
                                    <input type="text" placeholder="Search for something...">
                                    <button type="submit"><i class="fa fa-search"></i></button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
                <nav class="mainmenu-area">
                    <div class="navbar" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>

                        </div>

                        <div class="navbar-collapse collapse text-center">
                            <ul>
                                <li><a href="index.jsp">首 页</a></li>
                                <li><a href="coursepage.jsp">课 程 学 习</a></li>
                                <li><a href="person.jsp">个 人 天 地</a>
                                    <ul class="submenu">
                                        <li><a href="wrong.jsp">错 题 园 地</a></li>
                                        <li><a href="reports.jsp">分 析 报 告</a></li>
                                        <li><a href="person-center.jsp">账 户 中 心</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>

        </div>
    </div>
</header>



<section class="page-title center">
    <div class="container">
        <h2>用 户 注 册</h2>

    </div>
</section>



<div class="checkout-page">
    <div class="container">

        <!--Default Links-->
        <ul class="default-links">
            <li>已经有账号 ？ <a href="login.jsp">点击这里登录</a></li>
        </ul>
        <div class="row clearfix">
            <div class="col-md-7 col-sm-12 col-xs-12">
                <!--Billing Details-->
                <div class="billing-details">
                    <div class="shop-form" style="margin-left: 200px">

                            <div class="default-title" style="margin-left: 200px"><h2>       注 册 信 息 </h2></div>

                            <div class="row clearfix">
                                <!--Form Group-->
                                <div class="form-group col-md-12 col-sm-12 col-xs-12">
                                    <div class="field-label">     姓 名 <sup>*</sup> </div>
                                    <input type="text" name="field-name" value="" placeholder="" id="username">
                                    <div id="alert_username"></div>
                                </div>

                                <!--Form Group-->
                                <div class="form-group col-md-12 col-sm-12 col-xs-12">
                                    <div class="field-label">   密 码 <sup>*</sup> </div>
                                    <input type="text" name="field-name" value="" placeholder=""  id="userpwd">
                                    <div id="alert_password"></div>
                                </div>

                                <!--Form Group-->
                                <div class="form-group col-md-12 col-sm-12 col-xs-12">
                                    <div class="field-label">   emails <sup>*</sup> </div>
                                    <input type="text" name="field-name" value="" placeholder=""  id="email">
                                    <div id="alert_email"></div>
                                </div>

                                <!--Form Group-->
                                <div class="form-group col-md-12 col-sm-12 col-xs-12">
                                    <div class="field-label">   电 话 <sup>*</sup> </div>
                                    <input type="text" name="field-name" value="" placeholder=""  id="phone">
                                    <div id="alert_phone"></div>
                                </div>

                                <!--Form Group-->
                                <div class="form-group col-md-12 col-sm-12 col-xs-12">
                                    <div class="field-label">   性 别 <sup>*</sup> </div>
                                    <select id="sex">
                                        <option>女</option>
                                        <option>男</option>
                                    </select>
                                </div>

                                <!--Form Group-->
                                <div class="form-group col-md-12 col-sm-12 col-xs-12">
                                    <div class="field-label"> 身份 <sup>*</sup> </div>
                                    <select id="identity">
                                        <option>家长</option>
                                        <option>学生</option>
                                    </select>
                                </div>

                                <!--Form Group-->
                                <div class="form-group col-md-12 col-sm-12 col-xs-12">
                                    <div class="field-label">   生 日 (格式为19980928) <sup>*</sup> </div>
                                    <input type="text" name="field-name" value="" placeholder=""  id="birthday">
                                    <div id="alert_birthday"></div>
                                </div>

                                <button type="submit" class="theme-btn btn-style-one" style="margin-left: 200px" onclick="register_submit()">   确  定 <span class="fa fa-long-arrow-right"></span></button>
                            </div>

                    </div>

                </div><!--End Billing Details-->
            </div>

        </div>
    </div>



    <section class="footer">

        <div class="footer-bottom">
            <div class="footer-bottom-bg">
                <div class="container">
                    <div class="pull-left">
                        <figure><a href="#"><img src="images/logo/2.png" alt=""></a></figure>
                    </div>
                    <div class="pull-right">
                        <div class="copy-right">
                            Copyright &copy; 2017.Company name All rights reserved.
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </section>


    <!--Scroll to top-->
    <div class="scroll-to-top"><i class="fa fa-long-arrow-up"></i></div>



    <!-- jQuery js -->
    <script src="assets/jquery/jquery-1.12.3.min.js"></script>
    <!-- bootstrap js -->
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <!-- jQuery ui js -->
    <script src="assets/jquery-ui-1.11.4/jquery-ui.js"></script>

    <!-- wow js -->
    <script src="assets/wow.js"></script>

    <!-- owl carousel js -->
    <script src="assets/owl.carousel-2/owl.carousel.min.js"></script>              <!-- jquery.bxslider js -->
    <script src="assets/jquery.bxslider/jquery.bxslider.min.js"></script>
    <!-- jQuery validation -->
    <script src="assets/jquery-validation/dist/jquery.validate.min.js"></script>
    <!-- gmap.js helper -->
    <!--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRvBPo3-t31YFk588DpMYS6EqKf-oGBSI"></script>-->
    <!-- gmap.js -->
    <!--<script src="assets/gmaps.js"></script>-->

    <!-- mixit u -->
    <script src="assets/jquery.mixitup.min.js"></script>
    <script src="assets/isotope.pkgd.min.js"></script>
    <script src="assets/jquery.countdown.min.js"></script>
    <script src="assets/masterslider/masterslider.js"></script>
    <script src="assets/bootstrap-touch-spin/jquery.bootstrap-touchspin.js"></script>
    <script src="assets/SmoothScroll.js"></script>

    <!-- revolution slider js -->
    <script src="assets/revolution/js/jquery.themepunch.tools.min.js"></script>
    <script src="assets/revolution/js/jquery.themepunch.revolution.min.js"></script>
    <script src="assets/revolution/js/extensions/revolution.extension.actions.min.js"></script>
    <script src="assets/revolution/js/extensions/revolution.extension.carousel.min.js"></script>
    <script src="assets/revolution/js/extensions/revolution.extension.kenburn.min.js"></script>
    <script src="assets/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
    <script src="assets/revolution/js/extensions/revolution.extension.migration.min.js"></script>
    <script src="assets/revolution/js/extensions/revolution.extension.navigation.min.js"></script>
    <script src="assets/revolution/js/extensions/revolution.extension.parallax.min.js"></script>
    <script src="assets/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
    <script src="assets/revolution/js/extensions/revolution.extension.video.min.js"></script>

    <script src="assets/Polyglot-Language-Switcher-master/js/jquery.polyglot.language.switcher.js"></script>
    <script src="assets/fancyapps-fancyBox/source/jquery.fancybox.pack.js"></script>
    <script src="assets/scrollbar.js"></script>

    <!--<script src="js/default-map-script.js"></script>-->
    <script src="js/script.js"></script>
    <script src="js/user_register.js"></script>
</div>
</body>
</html>
