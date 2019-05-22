<%--
  Created by IntelliJ IDEA.
  User: chenan
  Date: 2019/5/21
  Time: 下午3:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <meta charset="UTF-8">
    <!-- mobile responsive meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="js/dist/echarts.min.js"></script>

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
        <h2>学生 学习 报告</h2>
    </div>
</section>

<section class="about">
    <div class="container">

        <div class="row">
            <div class="single-column col-md-6 col-sm-12">
                <div class="wow fadeIn" data-wow-duration="2s" data-wow-delay="0.5s" data-wow-offset="0" style="visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;">
                    <div class="post-content" id="userinfo">
                    </div>
                </div>


            </div>
            <div class="single-column col-md-6 col-sm-12">
                <div class="wow fadeInRight" data-wow-duration="2s" data-wow-delay="0.5s" data-wow-offset="0" style="visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;">
                    <figure class="img-box">
                        <img src="images/resource/touxiang.jpg" alt="">
                    </figure>
                </div>
            </div>
        </div>
    </div>
</section>


<section class="call-out">
    <div class="container"></div>
</section>

<section class="team">
    <div class="container">
        <div class="section-title center pb-60">
            <h2>我们的 <span>精选课程</span></h2>
            <div id="logincharts" style="width: 1200px;height:400px;"></div>
            <div id="radarcharts" style="width: 1200px;height:900px;"></div>
        </div>

    </div>
</section>


<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var logincharts = echarts.init(document.getElementById('logincharts'));
    var radarcharts = echarts.init(document.getElementById('radarcharts'));

    function getVirtulData(year){
        year = year || '2017';
        var date = +echarts.number.parseDate(year + '-01-01');
        var end = +echarts.number.parseDate((+year + 1) + '-01-01');
        var dayTime = 3600 * 24 * 1440;
        var data = [];
        data.push([
            echarts.format.formatTime('2019-05-22', "2019-05-22"),
            Math.floor(Math.random() * 60)
        ]);

        return data;
    }

    var option1 = {
        title: {
            top: 40,
            left: 'center',
            text: '2019年用户登录与学习情况'
        },
        tooltip : {},

        calendar: {
            top: 130,
            left: 30,
            right: 30,
            cellSize: ['auto', 13],
            range: '2019',
            itemStyle: {
                normal: {borderWidth: 0.5}
            },
            yearLabel: {show: false}
        },
        series: {
            type: 'heatmap',
            coordinateSystem: 'calendar',
            data: getVirtulData(2019)
        }
    };


    // 使用刚指定的配置项和数据显示图表。
    logincharts.setOption(option1);


    var option2 = {
        title: {
            text: '能力雷达图'
        },
        tooltip: {},
        legend: {
            data: ['学生知识掌握能力']
        },
        radar: {
            // shape: 'circle',
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            indicator: [
                { name: '文化素养掌握', max: 6500},
                { name: '计算运算能力', max: 6500},
                { name: '空间想象能力', max: 6500},
                { name: '逻辑思考能力', max: 6500},
                { name: '基本尝试掌握', max: 6500}
            ]
        },
        series: [{
            name: '学生知识掌握能力',
            type: 'radar',
            data : [
                {
                    value : [4300,3500,2500,3800,3200],
                    name : '学生知识掌握能力'
                }
            ]
        }]
    };

    radarcharts.setOption(option2);




</script>

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
<script src="js/report.js"></script>
</body>
</html>
