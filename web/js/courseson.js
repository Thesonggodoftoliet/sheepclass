//换成JSON
function GetJsonData() {
    var json = {
        "token":getCookie("token"),
        "courseid":$("#courseid").html()
    };
    return json;
}

function GetJsonData2() {
    var json = {
        "token":getCookie("token"),
        "courseid":$("#courseid").html(),
        "serialnum":$("#getvideo").getAttribute("value")
    };
    return json;
}

function GetJsonData3(curTime,videoFileName){
    var json = {
        "token":getCookie("token"),
        "curTime":curTime,
        "videoFileName":videoFileName
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
                             tem+="<li class=\"clearfix\"><div class=\"col st-3\">"+data.chapters[i].chaptername+"</div><div class=\"col st-3\"><a class='getvideo' value='"+data.chapters[i].serialnum+"' >已解锁～ 快点学习我</a></div></li>";

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


$(".getvideo").click( //在chapter.click添加的ID
    function ajax_Screenshot(){
        alert("Screenshot");
        var serialnum=$("#getvideo").getAttribute("value");
        $.ajax({
            type:"post",
            url:"/doScreenshot",
            data:JSON.stringify(GetJsonData2()),
            dataType:"json",
            success:function(data){
                alert("success");
                if(data.tag===0){
                    alert("身份验证过期，请重新登录");
                }
                else{
                    setCookie("token",data.token);
                    var video=data.chapter.video;//获取JSON重的chapter
                    var chaptername=data.chapter.chaptername;
                    var tem=""; //要插入的HTML代码

                    tem+="<video id=\"myVideo\" controls>";
                    tem+="<source src=\"VIDEO/"+video+" type=\"video/mp4\">";
                    tem+="您的浏览器不支持 HTML5 video。";
                    tem+="</video>";

                    //添加AJAX DOSCREENSHOT函数
                    tem+="<script type=\"text/javascript\">" +
                    "function doScreenshot(curTime){" +
                    "var videoFileName = "+serialnum+";" +
                    "$.ajax({" +
                    "type:\"POST\"," +
                    "url:\"/doScreenshot\"," +
                    "data:JSON.stringify(GetJsonData3())," +
                    "dataType:\"json\","+
                    "success:function(data){" +
                    "},error: function (XMLHttpRequest, textStatus, errorThrown) {" +
                    "alert(XMLHttpRequest.status);" +
                    "alert(XMLHttpRequest.readyState);" +
                    "alert(textStatus);" +
                    "}});} </script>";

                    //添加判断是否暂停的函数
                    tem+="<script>";
                        // 获取 id="myVideo" 的 video 元素
                    tem+="var x = document.getElementById(\"myVideo\");";
                        // 向 video 元素添加 ontimeupdate 事件，然后再当前播放位置发生改变时执行函数
                    tem+="x.addEventListener(\"timeupdate\", myFunction);";
                    // 显示 id="demo" 的 p 元素中视频的当前播放位置
                    tem+="function myFunction() { " + "if(x.paused) { doScreenshot(x.currentTime);}" + "</script>";
                    $("#courseson").html(tem);//插入到子版
                }

            },error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    });

