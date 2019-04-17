<%--
  Created by IntelliJ IDEA.
  User: chenan
  Date: 2019/4/17
  Time: 上午8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/course-page.css">
    <title>课程中心</title>
</head>
<body class="home-one" onload="Quitfun()">
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
                        <a href="#">Register</a>
                        <a href="#">Login </a>
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
                                <li><a href="index.html">首 页</a></li>
                                <li><a href="events.html">课 程 学 习</a></li>
                                <li><a href="about.html">个 人 天 地</a>
                                    <ul class="submenu">
                                        <li><a href="product-details.html">错 题 园 地</a></li>
                                        <li><a href="cart-page.html">分 析 报 告</a></li>
                                        <li><a href="checkout-page.html">账 户 中 心</a></li>
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
        <h2>Course</h2>
        <ul>
            <li><a href="${pageContext.request.contextPath}/courseintro.jsp">课程中心</a></li>
        </ul>
    </div>
</section>

<div  style="padding-top:60px;">
    <div class="skill_all" style="padding-top:150px;">
        <ul>
            <li onclick="get_course_ajax('all')">不限</li>
            <li onclick="get_course_ajax('literature')">文学</li>
            <li onclick="get_course_ajax('biology')">生物</li>
            <li onclick="get_course_ajax('history')">历史</li>
            <li onclick="get_course_ajax('geography')">地理</li>

        </ul>
    </div>
    <hr/>

    <div class="skill_video" id="coursediv">
    </div>

    <!--分页-->
    <div class="page">
        <ul class="pagination">
            <li><a href="#" onclick="alert('当前已是首页')">首页</a></li>
            <li><a href="#" onclick="alert('当前已是首页')">上一页</a></li>
            <li><a class="active" href="#">1</a></li>
            <li><a href="#" onclick="alert('当前已是最后一页')">下一页</a></li>
            <li><a href="#" onclick="alert('当前已是最后一页')">尾页</a></li>
        </ul>
    </div>
</div>
<section class="footer">

    <div class="footer-bottom">
        <div class="footer-bottom-bg">
            <div class="container">
                <div class="pull-left">
                    <figure>
                        <a href="#"><img
                                src="${pageContext.request.contextPath}/images/logo/2.png"
                                alt="" style="height: 80px; width: 250px;"></a>
                    </figure>
                </div>
                <div class="pull-right">
                    <div class="copy-right">Copyright &copy; 2017.Company name
                        All rights reserved.</div>
                </div>
            </div>
        </div>

    </div>
</section>


<!--Scroll to top-->
<div class="scroll-to-top">
    <i class="fa fa-long-arrow-up"></i>
</div>


<!-- jQuery js -->
<script
        src="${pageContext.request.contextPath}/assets/jquery/jquery-1.12.3.min.js"></script>
<!-- bootstrap js -->
<script
        src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
<!-- jQuery ui js -->
<script
        src="${pageContext.request.contextPath}/assets/jquery-ui-1.11.4/jquery-ui.js"></script>

<!-- wow js -->
<script src="${pageContext.request.contextPath}/assets/wow.js"></script>

<!-- owl carousel js -->
<script
        src="${pageContext.request.contextPath}/assets/owl.carousel-2/owl.carousel.min.js"></script>
<!-- jquery.bxslider js -->
<script
        src="${pageContext.request.contextPath}/assets/jquery.bxslider/jquery.bxslider.min.js"></script>
<!-- jQuery validation -->
<script
        src="${pageContext.request.contextPath}/assets/jquery-validation/dist/jquery.validate.min.js"></script>
<!-- gmap.js helper -->
<!--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRvBPo3-t31YFk588DpMYS6EqKf-oGBSI"></script>-->
<!-- gmap.js -->
<!--<script src="assets/gmaps.js"></script>-->

<!-- mixit u -->
<script
        src="${pageContext.request.contextPath}/assets/jquery.mixitup.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/isotope.pkgd.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/jquery.countdown.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/masterslider/masterslider.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/SmoothScroll.js"></script>

<!-- revolution slider js -->
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/jquery.themepunch.tools.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/jquery.themepunch.revolution.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/extensions/revolution.extension.actions.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/extensions/revolution.extension.carousel.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/extensions/revolution.extension.kenburn.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/extensions/revolution.extension.migration.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/extensions/revolution.extension.navigation.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/extensions/revolution.extension.parallax.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/revolution/js/extensions/revolution.extension.video.min.js"></script>

<script
        src="${pageContext.request.contextPath}/assets/Polyglot-Language-Switcher-master/js/jquery.polyglot.language.switcher.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/fancyapps-fancyBox/source/jquery.fancybox.pack.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/scrollbar.js"></script>

<!--<script src="js/default-map-script.js"></script>-->
<script src="${pageContext.request.contextPath}/js/script.js"></script>

<script src="${pageContext.request.contextPath}/js/course-intro.js"></script>

</body>
</html>


