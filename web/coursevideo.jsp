<%--
  Created by IntelliJ IDEA.
  User: chenan
  Date: 2019/5/20
  Time: 下午2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <meta charset="UTF-8">
    <!-- mobile responsive meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/layer/mobile/layer.js"></script>

    <link rel="stylesheet" href="css/common-style.css">
    <link rel="stylesheet" href="css/responsive.css">

</head>

<body class="home-one">
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
                                <li><a href="#">个 人 天 地</a>
                                    <ul class="submenu">
                                        <li><a href="wrong.jsp">错 题 园 地</a></li>
                                        <li><a href="reports.jsp">分 析 报 告</a></li>
                                        <li><a href="person-center.jsp">亲 情 账 号</a></li>
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
        <h2>章节——视频</h2>
        <div id="courseid"><%=request.getParameter("courseid")%></div>
        <div id="serialnum"><%=request.getParameter("serialnum")%></div>
        <ul>
            <li><a href="courseboss.jsp?serialnum=<%=request.getParameter("serialnum")%>&courseid=<%=request.getParameter("courseid")%>">返回课程目录</a></li>

        </ul>
    </div>
</section>

<section class="team">
    <div class="container">
        <div class="section-title center pb-60">
            <%--视频--%>
            <div class="col-md-8 col-sm-7 col-xs-12">
                <!--Billing Details-->
                <div class="billing-details">
                    <video id="myVideo" class="myVideo" width="750" controls>
                    </video>
                </div>

            </div>
            <%--列表--%>
                <div class="col-md-4 col-sm-5 col-xs-12">
                    <aside class="sidebar">

                        <!-- Popular Categories -->
                        <div class="widget wow fadeInUp animated animated" data-wow-delay="0ms" data-wow-duration="1500ms" style="visibility: visible; animation-duration: 1500ms; animation-delay: 0ms; animation-name: fadeInUp;">
                            <div class="sidebar-title"><h2>本次 <span>添加知识点情况</span></h2></div>
                            <ul class="list catagories product-info" id="list">
                            </ul>
                        </div>

                    </aside>
                </div>

           </div>

    </div>
    <br><br><br><br><br><br>
</section>

<br><br><br><br><br><br>


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
<script src="js/script.js"></script>
<script src="js/coursevideo.js"></script>

</body>
</html>


