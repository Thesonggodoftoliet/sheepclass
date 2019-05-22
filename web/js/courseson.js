//换成JSON
function GetJsonData() {
    var json = {
        "token":getCookie("token"),
        "courseid":$("#courseid").html()
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

//* 5。22改动
//  应首先改成自动的，只用调用一次CHAPTER，
//  然后，如果点击CHAPTER就嘻嘻嘻嘻嘻，如果点击HOMEWORK就嘻嘻嘻嘻嘻
// *//

$(".chapter").click(
    function ajax_subject(){
        $.ajax({
            type:"post",
            url:"/course/getChapters",
            data:JSON.stringify(GetJsonData()),
            dataType:"json",
            success:function(data){
                alert("success");
                if(data.tag===0){
                    alert("身份验证过期，请重新登录");
                }
                else{
                    setCookie("token",data.token);
                    var size=data.chapters.length;
                    var i=0;
                    var tem="";
                    alert(size);
                    for(i=0;i<size;i++){
                        if((data.serialnum-data.chapters[i].serialnum)>=0.1){
                            tem+="<li class=\"clearfix total\"><div class=\"col st-4\">"+data.chapters[i].chaptername+"</div><div class=\"col st-4\">已学习</div></li>";

                        }else if((data.chapters[i].serialnum-data.serialnum)>=0.1){
                             tem+="<li class=\"clearfix total\"><div class=\"col st-4\">"+data.chapters[i].chaptername+"</div><div class=\"col st-4\">未解锁</div></li>";

                        }else{
                             tem+="<a class=\"clearfix\"><div class=\"col st-3\"><a href='../coursevideo.jsp?serialnum="+data.chapters[i].serialnum+"&courseid="+data.courseid+"' >"+data.chapters[i].chaptername+"</div><div class=\"col st-3\">已解锁～ 快点学习我</div>></div></a></li>";

                        }
                    }

                    $("#courseson").html(tem);
                }

            },error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
});


$(".homework").click(
    function ajax_subject(){
        $.ajax({
            type:"post",
            url:"/course/getChapters",
            data:JSON.stringify(GetJsonData()),
            dataType:"json",
            success:function(data){
                alert("success");
                if(data.tag===0){
                    alert("身份验证过期，请重新登录");
                }
                else{
                    setCookie("token",data.token);
                    var size=data.chapters.length;
                    var i=0;
                    var tem="";
                    alert(size);
                    for(i=0;i<size;i++){
                        if((data.serialnum-data.chapters[i].serialnum)>=0.1){
                            tem+="<li class=\"clearfix total\"><div class=\"col st-4\">"+data.chapters[i].chaptername+"</div><div class=\"col st-4\">已学习</div></li>";

                        }else if((data.chapters[i].serialnum-data.serialnum)>=0.1){
                            tem+="<li class=\"clearfix total\"><div class=\"col st-4\">"+data.chapters[i].chaptername+"</div><div class=\"col st-4\">未解锁</div></li>";

                        }else{
                            tem+="<a class=\"clearfix\"><div class=\"col st-3\"><a href='../homework.jsp?serialnum="+data.chapters[i].serialnum+"&courseid="+data.courseid+"' >"+data.chapters[i].chaptername+"</div><div class=\"col st-3\">已解锁～ 快点学习我</div>></div></a></li>";

                        }
                    }

                    $("#courseson").html(tem);
                }

            },error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    });




