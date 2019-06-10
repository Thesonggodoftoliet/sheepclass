<%--
  Created by IntelliJ IDEA.
  User: chenan
  Date: 2019/4/28
  Time: 上午10:01
  To change this template use File | Settings | File Templates.
--%>
<<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>羊村课堂</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-style.css">
    <link href="${pageContext.request.contextPath}/css/coursepage.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src= "${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/layer/mobile/layer.js"></script>


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

<div  style="padding-top:60px;">

    <div class="skill_all">
        <ul class="subject">
            <li class="active" value=""><a>不限</a></li>
            <li value="chinese"><a>语文国学</a></li>
            <li value="olympiad"><a>基础奥数</a></li>
            <li value="science"><a>自然科学</a></li>
            <li value="biology"><a>生物故事</a></li>
            <li value="history"><a>外国历史</a></li>
            <li value="geography"><a>中国地理</a></li>

        </ul>
    </div>
    <div class="skill_all">
        <ul>
            <li><a class="active" href="#">默认排序</a></li>
            <li><a href="#">最新</a></li>
            <li><a href="#">最好</a></li>
            <li><a href="#">评价</a></li>
        </ul>
    </div>


    <div class="skill_video" id="coursediv">
    </div>

    <br><br><br>
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

<br><br>

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

<script src="js/getcourse.js"></script>

</body>

</html>




