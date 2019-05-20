function GetJsonData3(curTime,videoFileName){
    var json = {
        "token":getCookie("token"),
        "curTime":curTime,
        "videoFileName":videoFileName
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
    alert("hhh");
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


