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

    <link rel="stylesheet" href="css/common-style.css">
    <link rel="stylesheet" href="css/responsive.css">

</head>

<body>

<div id="courseid"><%=request.getParameter("courseid")%></div>
<div id="serialnum"><%=request.getParameter("serialnum")%></div>

<p>该实例中我们使用 addEventListener() 方法向 video 元素添加 "ontimeupdate" 事件。</p>
<video id="myVideo" class="myVideo" controls>
    <%--<source src="VIDEO/history_sanguo.mp4" type="video/mp4">--%>
    <%--您的浏览器不支持 HTML5 video。--%>
</video>
<p>当前播放位置: <span id="demo"></span></p>
<div id="imgput"></div>





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
<script src="js/coursevideo.js"></script>

</body>
</html>


