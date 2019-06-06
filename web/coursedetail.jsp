<%--
  Created by IntelliJ IDEA.
  User: chenan
  Date: 2019/5/13
  Time: 下午2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>

    <!-- mobile responsive meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <link rel="stylesheet" href="css/common-style.css">
    <link rel="stylesheet" href="css/responsive.css">
</head>
<body class="about-page">
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


<section class="about">
    <div class="container">

        <div class="row">
            <div class="single-column col-md-6 col-sm-12">
                <div class="wow fadeIn" data-wow-duration="2s" data-wow-delay="0.5s" data-wow-offset="0" style="visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;">
                    <div class="post-content">
                        <div class="section-title">
                            <h2> 课程名字 </h2>
                            <br/>
                            <h2><span><%=request.getParameter("coursename")%></span></h2>
                            <br/><br/>
                        </div>
                        <br/>
                        <div class="section-title">
                            <h2> 课程内容 </h2>
                            <br/>
                            <div class="text">
                                <p><%=request.getParameter("courseinfo")%></p>
                            </div>
                        </div>

                        <ul class="list">
                            <li>你将会获得：全网的免费学习课程</li>
                            <li>你将会发现：这里会提供你的个性化学习报告</li>
                            <li>你将会拥有：一个完整的学习闭环——学习+练习+订正+复习</li>
                        </ul>
                        <br/><br/>
                        <div class="link" id="enter">

                        </div>
                    </div>
                </div>


            </div>
            <div class="single-column col-md-6 col-sm-12">
                <div class="wow fadeInRight" data-wow-duration="2s" data-wow-delay="0.5s" data-wow-offset="0" style="visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;">
                    <figure class="img-box">
                        <a href="#"><img src="/images/course/<%=request.getParameter("courseimg")%>" alt=""></a>
                    </figure>
                    <div id="courseid"><%=request.getParameter("courseid")%></div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="two-column style-3">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-sm-12 tab-column">
                <div class="section-title">
                    <h2>常见问题</h2>
                    <br/>
                </div>
                <div class="inner-box tab-pane fade in active " >
                    <div class="content">
                        <h3>1. 课程怎么学习？</h3>
                        <p>答：一个课程分为几个章节，每一章节有对应的视频和习题卷子。我们建议学习章节的时候先观看视频再做习题，只有当这一章节的习题做完了，才能进行下一章的学习。</p>
                        <br/><br/>
                        <h3>2. 观看视频的时候知识点怎么添加的？</h3>
                        <p>  答：每一个章节的背后本身就有一些知识点。在观看视频的过程中，如果学生遇到了不懂的地方暂停视频，系统将自动识别当前节点背后的知识点，并加入到学生的知识系统中。也就是说，学生无需手动添加知识点。</p>
                        <br/><br/>
                        <h3>3. 做完了一章习题之后该干什么？</h3>
                        <p>   答：学生在做完一章习题之后，可以进行下一章的学习，也可以前往错题中心处，对错题进行巩固。</p>
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

<script src="js/coursedetail.js"></script>






</body>
</html>
