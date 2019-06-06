function GetJsonData3(curTime,videoFileName){
    var json = {
        "token":getCookie("token"),
        "time":curTime,
        "path":videoFileName,
        "courseid":$("#courseid").html(),
    };
    return json;

}
function GetJsonData2() {
    var json = {
        "token":getCookie("token"),
        "courseid":$("#courseid").html(),
        "serialnum":$("#serialnum").html()
    };
    return json;
}

function doScreenshot(curTime){
    alert(videonum);
    $.ajax({
        type:"POST",
        url:"/homework/QuestionBypic",
        data:JSON.stringify(GetJsonData3(curTime,videonum)),
        dataType:"json",
        success:function(data){
        },error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }

    });
}


// 获取 id="myVideo" 的 video 元素
var x = document.getElementById("myVideo");
// 向 video 元素添加 ontimeupdate 事件，然后再当前播放位置发生改变时执行函数
x.addEventListener("timeupdate", myFunction);


function myFunction() {
    // 显示 id="demo" 的 p 元素中视频的当前播放位置
    if(x.paused) {
        var myDate = new Date();
        var hours=myDate.getHours();       //获取当前小时数(0-23)
        var minutes=myDate.getMinutes();     //获取当前分钟数(0-59)
        var seconds=myDate.getSeconds();     //获取当前秒数(0-59)
        var tem="<li><i class=\"icon-clock\"></i>"+hours+" "+minutes+" "+seconds+" 添加知识点成功</li>";
        $("#list").html(tem);
        doScreenshot(x.currentTime);
    }
}



//COOKIE存储TOKEN
function setCookie(cname,cvalue)
{
    var expdate = new Date();   //初始化时间
    expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间单位毫秒
    var expires = "expires="+expdate.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
    alert("调用cookie成功");
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

var videonum="";

$(function(){
    $.ajax({
        type:"post",
        url:"/course/getChapter",
        data:JSON.stringify(GetJsonData2()),
        dataType:"json",
        success:function(data){
            alert("success");
            if(data.tag===0){
                alert("身份验证过期，请重新登录");
            }
            else{
                setCookie("token",data.token);
                videonum=data.chapter.video;
                alert(videonum);
                var tem="<source src=\""+"VIDEO/"+data.chapter.video+"\" type=\"video/mp4\">" +
                    "您的浏览器不支持 HTML5 video。";
                $("#myVideo").html(tem);
             }
        },error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
});

