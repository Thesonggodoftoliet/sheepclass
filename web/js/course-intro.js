$(function default_ajax(){

    var obj = JSON.stringify({"subject":"all"});

    $.ajax({
        type:"post",
        url:"<%=request.getContextPath()%>/xxxxxxx",
        cache: false,
        data:obj,
        dataType:"json",
        success:function(json){
            var tem = "<section class='event style-2'><div class='container'><div class='row'>";
            for(var i=0,l=json.length;i<l;i++){
                tem+="<div class='col-md-4 col-sm-6 col-xsw-12 item wow fadeIn' data-wow-duration='2s' data-wow-delay='0."+(i+5)+"s' data-wow-offset='0' style='visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;'>";
                tem+="<div class='img-holder'><figure>";
                tem+="<a href='${pageContext.request.contextPath}/startClass?courseid="+json[i].courseid+"'>";
                tem+=" <img src='${pageContext.request.contextPath}"+json[i].img+"'>";
                tem+="</a>"+"</figure>";
                tem+="<div class='content bg-color-1'><div class='inner-box'><div class='btn-box'><div class='count'>25</div><div class='month'>July</div></div>";
                tem+="<h4><a href='#'>"+json[i].coursename+"</a></h4>";
                tem+="<p><span class='fa fa-clock-o'></span> 8.00 am to 12.00 pm</p>";
                tem+="<p><span class='fa fa-map-marker'></span>"+json[i].info+"</p>";
                tem+="</div></div> </div></div>";

            }
            tem+="</div><div class='link-btn center'><a href='#' class='theme-btn btn-style-one'>load more</a></div></div></section>";
            $("#coursediv").html(tem);
        }
    });
});

function get_course_ajax(subject){

    var obj = JSON.stringify({"subject":subject});
    alert(subject);

    $.ajax({
        type:"post",
        url:"<%=request.getContextPath()%>/xxxxxxx",
        cache: false,
        data:obj,
        dataType:"json",
        success:function(json){
            var tem = "<section class='event style-2'><div class='container'><div class='row'>";
            for(var i=0,l=json.length;i<l;i++){
                tem+="<div class='col-md-4 col-sm-6 col-xsw-12 item wow fadeIn' data-wow-duration='2s' data-wow-delay='0."+(i+5)+"s' data-wow-offset='0' style='visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;'>";
                tem+="<div class='img-holder'><figure>";
                tem+="<a href='${pageContext.request.contextPath}/startClass?courseid="+json[i].courseid+"'>";
                tem+=" <img src='${pageContext.request.contextPath}"+json[i].img+"'>";
                tem+="</a>"+"</figure>";
                tem+="<div class='content bg-color-1'><div class='inner-box'><div class='btn-box'><div class='count'>25</div><div class='month'>July</div></div>";
                tem+="<h4><a href='#'>"+json[i].coursename+"</a></h4>";
                tem+="<p><span class='fa fa-clock-o'></span> 8.00 am to 12.00 pm</p>";
                tem+="<p><span class='fa fa-map-marker'></span>"+json[i].info+"</p>";
                tem+="</div></div> </div></div>";

            }
            tem+="</div><div class='link-btn center'><a href='#' class='theme-btn btn-style-one'>load more</a></div></div></section>";
            $("#coursediv").html(tem);
        }
    });
}