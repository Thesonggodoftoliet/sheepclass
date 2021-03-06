function GetJsonData(subject)
{
    var datajson = {
        "token":getCookie("token"),
        "subject":subject
    };
    return datajson;
}

//COOKIE存储TOKEN
function setCookie(cname,cvalue)
{
    var expdate = new Date();   //初始化时间
    expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间单位毫秒
    var expires = "expires="+expdate.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
    //alert("调用cookie成功");
}

//cookie读取TOKEN
function getCookie(cname)
{
    var reg = new RegExp("(^| )" + cname + "=([^;]*)(;|$)");
    var cvalue;

    if (cvalue = document.cookie.match(reg))
        return unescape(cvalue[2]);   //消除空格之类的转义字符
    else
        return null;
}


$(function auto_ajax_subject(){
    $.ajax({
        type:"post",
        url:"/course/getCourse",
        data:JSON.stringify(GetJsonData("")),
        cache:false,
        dataType:"json",
        success:function(json){
            if(json.tag===0){
                layer.open({
                    content:'<h3 style="color:#f8b54d;">身份验证过期，请重新登录</h3>'
                    ,btn:['<p style="color:#f8b54d;">前往登录</p>']
                    ,yes:function(){
                        window.location.href="login.jsp";
                    }
                });
            }else{
                //alert("auto_ajax_subject");
                setCookie("token",json.token); //更新TOKEN
                var course = json.course;
                var tem = "<section class='event style-2'><div class='container'><div class='row'>";
                for(var i=0,l=course.length;i<l;i++){
                    //alert(course[i].courseid);
                    tem+="<div class='col-md-4 col-sm-6 col-xsw-12 item wow fadeIn' data-wow-duration='2s' data-wow-delay='0."+(i+5)+"s' data-wow-offset='0' style='visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;'>";
                    tem+="<div class='img-holder'><figure>";

                    // language=HTML
                    tem+="<a href='/coursedetail.jsp?courseid="+course[i].courseid+"&courseinfo="+course[i].info+"&courseimg="+course[i].img+"&coursename="+course[i].coursename+"'>";
                    //5.5日下午
                    tem+=" <img src='/images/course/"+course[i].img+"'>";
                    tem+="</a>"+"</figure>";
                    tem+="<div class='content bg-color-1'><div class='inner-box'><div class='btn-box'><div class='month'>"+course[i].subject+"</div></div>";
                    tem+="<h4>"+course[i].coursename+"</h4>";
                    tem+="<p>"+course[i].info+"</p>";
                    tem+="</div></div> </div></div>";

                }
                tem+="</div><div class='link-btn center'><a href='#' class='theme-btn btn-style-one'>load more</a></div></div></section>";
                $("#coursediv").html(tem);

            }
        }
    });
});

$(".subject li").click(
    function ajax_subject(){

    //取当前选中 li 的value值
    var subject = $(this).attr("value");
    //alert(subject);
    $.ajax({
        type:"post",
        url:"/course/getCourse",
        data:JSON.stringify(GetJsonData(subject)),
        cache:false,
        dataType:"json",
        success:function(json){
            if(json.tag===0){
                layer.open({
                    content:'<h3 style="color:#f8b54d;">身份验证过期，请重新登录</h3>'
                    ,btn:['<p style="color:#f8b54d;">前往登录</p>']
                    ,yes:function(){
                        window.location.href="login.jsp";
                    }
                });
            }else{
                //alert("auto_subject");
                setCookie("token",json.token); //更新TOKEN
                var course = json.course;
                var tem = "<section class='event style-2'><div class='container'><div class='row'>";
                if(course.length===0){
                    tem+="<div class='link-btn center'><h3 style=\"color:#f8b54d;\">当前分类下没有课程～ 尽情期待之后的新课程哟～</h3></div>";
                }else{
                    for(var i=0,l=course.length;i<l;i++){
                        // alert(course[i].courseid);
                        tem+="<div class='col-md-4 col-sm-6 col-xsw-12 item wow fadeIn' data-wow-duration='2s' data-wow-delay='0."+(i+5)+"s' data-wow-offset='0' style='visibility: visible; animation-duration: 2s; animation-delay: 0.5s; animation-name: fadeIn;'>";
                        tem+="<div class='img-holder'><figure>";
                        // language=HTML
                        tem+="<a href='/coursedetail.jsp?courseid="+course[i].courseid+"&courseinfo="+course[i].info+"&courseimg="+course[i].img+"&coursename="+course[i].coursename+"'>";
                        //5.5日下午
                        tem+=" <img src='/images/course/"+course[i].img+"'>";
                        tem+="</a>"+"</figure>";
                        tem+="<div class='content bg-color-1'><div class='inner-box'><div class='btn-box'><div class='month'>"+course[i].subject+"</div></div>";
                        tem+="<h4>"+course[i].coursename+"</h4>";
                        tem+="<p>"+course[i].info+"</p>";
                        tem+="</div></div> </div></div>";

                    }
                }
                tem+="</div><div class='link-btn center'><a href='#' class='theme-btn btn-style-one'>load more</a></div></div></section>";
                $("#coursediv").html(tem);

            }
        }
    });
});