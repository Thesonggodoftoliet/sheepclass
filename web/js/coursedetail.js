function proxy(id){
    var courseid=$("#courseid").val();
    if(id===0){
        alert("您的身份验证已经过期，请重新登录");
    }else if(id===1){
        var html = "<a href='courseboss.jsp?courseid="+courseid+"class='read-more'>！进入课程 ! </a>";
        $("#enter").html(html);
    }else{
        var html = "<a href='courseboss.jsp?courseid="+courseid+"class='read-more'>！马上加入课程 ! </a>"
        $("#enter").html(html);
    }

}

//换成JSON
function GetJsonData() {
    var json = {
        "token":getCookie("token"),
        "courseid":$("#courseid").val()
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



$(function(){
    $.ajax({
        type:"post",
        url:"/course/getChapters",
        data:JSON.stringify(GetJsonData()),
        dataType:"json",
        success:function(data){
            setCookie("token",data.token);
            proxy(data.tag);
        },error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
})

