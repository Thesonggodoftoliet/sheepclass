<%--
  Created by IntelliJ IDEA.
  User: 53564
  Date: 2019/4/9
  Time: 16:32
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

  <section class="rev_slider_wrapper">
      <h2 class="hidden">rev slider</h2>
      <div id="slider1" class="rev_slider"  data-version="5.0">
          <ul>
              <li class="gradient-overlay" data-transition="slotzoom-horizontal" data-slotamount="1" data-masterspeed="1000" data-thumb="images/main-slider/1.jpg"  data-saveperformance="off"  data-title="Awesome Title Here">
                  <img src="images/main-slider/1.jpg"  alt=""  data-bgposition="center top" data-bgfit="cover" data-bgrepeat="no-repeat">

                  <div class="tp-caption lfl sfb tp-resizeme start"
                       data-x="left" data-hoffset="30"
                       data-y="center" data-voffset="0"
                       data-speed="1500"
                       data-start="1000"
                       data-easing="easeOutExpo"
                       data-splitin="none"
                       data-splitout="none"
                       data-elementdelay="0.01"
                       data-endelementdelay="0.3"
                       data-endspeed="1200"
                       data-endeasing="Power4.easeIn"><img src="images/main-slider/index3.png" style="width:1200px;height:600px;" alt=""></div>

              </li>
              <li class="gradient-overlay overly" data-transition="slotzoom-horizontal" data-slotamount="1" data-masterspeed="1000" data-thumb="images/main-slider/2.jpg"  data-saveperformance="off"  data-title="Awesome Title Here">
                  <img src="images/main-slider/2.jpg"  alt=""  data-bgposition="center top" data-bgfit="cover" data-bgrepeat="no-repeat">


                  <div class="tp-caption lfl sfb tp-resizeme start"
                       data-x="left" data-hoffset="0"
                       data-y="center" data-voffset="0"
                       data-speed="1500"
                       data-start="1000"
                       data-easing="easeOutExpo"
                       data-splitin="none"
                       data-splitout="none"
                       data-elementdelay="0.01"
                       data-endelementdelay="0.3"
                       data-endspeed="1200"
                       data-endeasing="Power4.easeIn"><img src="images/main-slider/index2.png" style="width:1200px;height:600px;"  alt=""></div>

                  <div class="tp-caption lft sfb tp-resizeme start"
                       data-x="right" data-hoffset="0"
                       data-y="center" data-voffset="0"
                       data-speed="1500"
                       data-start="1500"
                       data-easing="easeOutExpo"
                       data-splitin="none"
                       data-splitout="none"
                       data-elementdelay="0.01"
                       data-endelementdelay="0.3"
                       data-endspeed="1200"
                       data-endeasing="Power4.easeIn"><div class="outer-box">
                      <p>成为一个快乐博学的羊羊</p>
                      <h3>在我们的羊村课堂 <br> 你可以学习你想学习到的 <br> 任何知识</h3>
                  </div></div>

              </li>

              <li class="gradient-overlay" data-transition="slotzoom-horizontal" data-slotamount="1" data-masterspeed="1000" data-thumb="images/main-slider/3.jpg"  data-saveperformance="off"  data-title="Awesome Title Here">
                  <img src="images/main-slider/3.jpg"  alt=""  data-bgposition="center top" data-bgfit="cover" data-bgrepeat="no-repeat">


                  <div class="tp-caption lfl sfb tp-resizeme start"
                       data-x="left" data-hoffset="0"
                       data-y="center" data-voffset="0"
                       data-speed="1500"
                       data-start="1000"
                       data-easing="easeOutExpo"
                       data-splitin="none"
                       data-splitout="none"
                       data-elementdelay="0.01"
                       data-endelementdelay="0.3"
                       data-endspeed="1200"
                       data-endeasing="Power4.easeIn"><img src="images/main-slider/index1.png" style="width:1200px;height:600px;" alt=""></div>

                  <div class="tp-caption lft sfb tp-resizeme start"
                       data-x="left" data-hoffset="600"
                       data-y="center" data-voffset="-100"
                       data-speed="1500"
                       data-start="1500"
                       data-easing="easeOutExpo"
                       data-splitin="none"
                       data-splitout="none"
                       data-elementdelay="0.01"
                       data-endelementdelay="0.3"
                       data-endspeed="1200"
                       data-endeasing="Power4.easeIn"><h2>欢 迎 来 到 <br>羊 村 课 堂</h2></div>

                  <div class="tp-caption lfr sfr tp-resizeme start"
                       data-x="left" data-hoffset="600"
                       data-y="center" data-voffset="0"
                       data-speed="1500"
                       data-start="2000"
                       data-easing="easeOutExpo"
                       data-splitin="none"
                       data-splitout="none"
                       data-elementdelay="0.01"
                       data-endelementdelay="0.3"
                       data-endspeed="1200"
                       data-endeasing="Power4.easeIn"><p>这是我们的特色历史课程<br>妙趣横生的讲述历史故事～～</p></div>

                  <div class="tp-caption lfb sfb tp-resizeme start"
                       data-x="left" data-hoffset="600"
                       data-y="center" data-voffset="80"
                       data-speed="1500"
                       data-start="2500"
                       data-easing="easeOutExpo"
                       data-splitin="none"
                       data-splitout="none"
                       data-elementdelay="0.01"
                       data-endelementdelay="0.3"
                       data-endspeed="1200"
                       data-endeasing="Power4.easeIn"><a href="coursepage.jsp" class="theme-btn btn-style-one">进入课程中心</a></div>

              </li>


          </ul>
      </div>
  </section>



  <section class="about">
      <div class="container">

          <div class="row">
              <div class="single-column col-md-6 col-sm-12">
                  <div class="wow fadeIn" data-wow-duration="2s" data-wow-delay="0.5s" data-wow-offset="0" style="visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;">
                      <div class="post-content">
                          <div class="section-title">
                              <h2>欢迎 来到<span>羊村课堂</span> </h2>
                          </div>
                          <div class="text">
                              <p>  “羊村课堂”是一个针对K12在线视频学习的网站，为用户提供课程资源与学习情况分析。项目基于软件工程技术构建一个新型的学习平台，旨在汇聚并共享优质教育资源，引领教育教学模式创新，提升教学质量，促进教育公平，精准教育扶贫，服务国家和社会。</p>
                          </div>
                          <ul class="list">
                              <li>亲情账号绑定</li>
                              <li>智能化学习监测</li>
                              <li>寻找志同道合的伙伴</li>
                          </ul>

                      </div>
                  </div>


              </div>
              <div class="single-column col-md-6 col-sm-12">
                  <div class="wow fadeInRight" data-wow-duration="2s" data-wow-delay="0.5s" data-wow-offset="0" style="visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;">
                      <figure class="img-box">
                          <img src="images/resource/1.png" alt="">
                      </figure>
                  </div>
              </div>
          </div>
      </div>
  </section>


  <section class="call-out">
      <div class="container">
          <div class="row">

              <div class="column col-md-9 col-sm-12 col-xs-12">
                  <div class="text-left clearfix">
                      <h2><a href="register.jsp">加入我们!</a></h2>
                      <p><span><span><span></span></span></span>让我们一起在羊村课堂快乐成长～fighting~~~~~~</p>
                  </div>

              </div>
          </div>
      </div>
  </section>

  <section class="team">
      <div class="container">
          <div class="section-title center pb-60">
              <h2>我们的 <span>精选课程</span></h2>
          </div>

          <div class="team-list">
              <div class="row">
                  <div class="col-md-3 col-sm-6 col-xs-12 wow fadeIn" data-wow-duration="2s" data-wow-delay="0.5s" data-wow-offset="0" style="visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;">
                      <div class="item">
                          <div class="img-holder">
                              <figure><a href="#"><img src="images/team/1.jpg" alt="Awesome Image"></a></figure>
                              <div class="content">
                                  <h2><a href="#">《戏说五代十国》</a></h2>
                                  <p>国学</p>
                              </div>

                          </div>

                          <div class="overlay">
                              <div class="inner">
                                  <div class="content">
                                      <h2><a href="#">《戏说五代十国》</a></h2>
                                      <p>国学</p>
                                  </div>

                              </div>
                          </div>
                      </div>

                  </div>
                  <div class="col-md-3 col-sm-6 col-xs-12 wow fadeIn" data-wow-duration="2s" data-wow-delay="0.6s" data-wow-offset="0" style="visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;">
                      <div class="item">
                          <div class="img-holder">
                              <figure><a href="#"><img src="images/team/2.jpg" alt="Awesome Image"></a></figure>
                              <div class="content">
                                  <h2><a href="#">《一起学习数学奥林匹克》</a></h2>
                                  <p>数学</p>

                              </div>
                          </div>
                          <div class="overlay">
                              <div class="inner">
                                  <div class="content">
                                      <h2><a href="#">《一起学习数学奥林匹克》</a></h2>
                                      <p>数学</p>

                                  </div>

                              </div>
                          </div>
                      </div>
                  </div>
                  <div class="col-md-3 col-sm-6 col-xs-12 wow fadeIn" data-wow-duration="2s" data-wow-delay="0.6s" data-wow-offset="0" style="visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;">
                      <div class="item">
                          <div class="img-holder">
                              <figure><a href="#"><img src="images/team/3.jpg" alt="Awesome Image"></a></figure>
                              <div class="content">
                                  <h2><a href="#">《三国演义》</a></h2>
                                  <p>中国历史</p>

                              </div>
                          </div>
                          <div class="overlay">
                              <div class="inner">
                                  <div class="content">
                                      <h2><a href="#">《三国演义》</a></h2>
                                      <p>中国历史</p>

                                  </div>

                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>


      </div>
  </section>



  <!--Scroll to top-->
  <div class="scroll-to-top"><i class="fa fa-long-arrow-up"></i></div>
  <!--Gallery Popup-->
  <div class="gallery-box" id="gallery-popup">
      <!--Bg Fade Layer-->
      <div class="bg-fade-layer"></div>

      <div class="popup-container">
          <div class="popup-content">

              <div class="popup-header">
                  <button type="button" class="btn-close"><span class="fa fa-close"></span></button>
                  <h3>Opening Remark</h3>
              </div>

              <div class="content-outer">

                  <!--Vertical Slider-->
                  <div class="vertical-slider">

                      <!-- template -->
                      <div class="ms-vertical-template  ms-tabs-vertical-template">
                          <!-- masterslider -->
                          <div class="master-slider ms-skin-default" id="masterslider">

                              <div class="ms-slide">
                                  <img src="css/masterslider/style/blank.gif" data-src="images/gallery/1.jpg" alt="">
                                  <img class="ms-thumb" src="images/gallery/1.jpg" alt="thumb" />
                              </div>

                              <div class="ms-slide">
                                  <img src="css/masterslider/style/blank.gif" data-src="images/gallery/2.jpg" alt="">
                                  <img class="ms-thumb" src="images/gallery/2.jpg" alt="thumb" />
                              </div>

                              <div class="ms-slide">
                                  <img src="css/masterslider/style/blank.gif" data-src="images/gallery/3.jpg" alt="">
                                  <img class="ms-thumb" src="images/gallery/3.jpg" alt="thumb" />
                              </div>

                              <div class="ms-slide">
                                  <img src="css/masterslider/style/blank.gif" data-src="images/gallery/4.jpg" alt="">
                                  <img class="ms-thumb" src="images/gallery/4.jpg" alt="thumb" />
                              </div>

                              <div class="ms-slide">
                                  <img src="css/masterslider/style/blank.gif" data-src="images/gallery/5.jpg" alt="">
                                  <img class="ms-thumb" src="images/gallery/5.jpg" alt="thumb" />
                              </div>

                              <div class="ms-slide">
                                  <img src="css/masterslider/style/blank.gif" data-src="images/gallery/6.jpg" alt="">
                                  <img class="ms-thumb" src="images/gallery/6.jpg" alt="thumb" />
                              </div>
                              <div class="ms-slide">
                                  <img src="css/masterslider/style/blank.gif" data-src="images/gallery/7.jpg" alt="">
                                  <img class="ms-thumb" src="images/gallery/7.jpg" alt="thumb" />
                              </div>
                              <div class="ms-slide">
                                  <img src="css/masterslider/style/blank.gif" data-src="images/gallery/8.jpg" alt="">
                                  <img class="ms-thumb" src="images/gallery/8.jpg" alt="thumb" />
                              </div>



                          </div>
                          <!-- end of masterslider -->
                      </div>
                      <!-- end of template -->

                  </div>

              </div>

          </div>
          <!--popup-content -->
      </div>
      <!-- popup-container -->
  </div>
  <!-- Gallery Box -->

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
  </body>
</html>
