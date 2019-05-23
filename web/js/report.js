
//换成JSON
function GetJsonData1() {
    var json = {
        "token":getCookie("token"),
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



//显示个人信息
$(function(){
    $.ajax({
        type:"post",
        url:"/personal/getUserInfo",
        data:JSON.stringify(GetJsonData1()),
        dataType:"json",
        success:function(data){
            setCookie("token",data.token);

            var tem="<div className=\"section-title\">" + "<h2>个 人 <span>信 息</span>"+data.username+"</h2>" + "</div>";
            tem+=" <ul className = \"list\" >" +
                "<li>用户邮箱: "+data.email+" </li>";
            tem+=" <ul className = \"list\" >" +
                "<li>用户电话: "+data.phone+" </li>";
            tem+=" <ul className = \"list\" >" +
                "<li>用户性别: "+data.sex+" </li>";
            if(data.identity==3){
                tem+=" <ul className = \"list\" >" +
                    "<li> 用户身份: 家长 </li>";
            }else{
                tem+=" <ul className = \"list\" >" +
                    "<li>用户身份: 学生 </li>";
            }
            tem+="</ul>";
            $("#userinfo").html(tem);
        },error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
});

