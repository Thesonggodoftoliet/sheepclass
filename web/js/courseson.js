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


$(function ajax_subject(){
        $.ajax({
            type:"post",
            url:"/course/getChapters",
            data:JSON.stringify(GetJsonData()),
            dataType:"json",
            success:function(data){
                var serialnum=data.serialnum.toFixed(1);
                if(data.tag===0){
                    alert("身份验证过期，请重新登录");
                }
                else{
                    setCookie("token",data.token);
                    var size=data.chapters.length;
                    var i=0;
                    var tem="";
                    for(i=0;i<size;i++){
                        var cs=data.chapters[i].serialnum.toFixed(1); // sishewuru
                        if((serialnum-cs)>0){

                            tem+="<li>"+data.chapters[i].chaptername+"<span>已学习</span></li>";

                        }else if((cs-serialnum)>0){
                             tem+="<li>"+data.chapters[i].chaptername+"<span>未解锁</span></li>";

                        }else{
                             tem+="<li><p><a href='../coursevideo.jsp?serialnum="+cs+"&courseid="+data.courseid+"' >"+data.chapters[i].chaptername+"</a><span>已解锁～ 快点学习我</span></a></li>";

                        }
                    }

                    $("#chapter").html(tem);
                }

            },error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
});


$(function ajax_subject(){
        $.ajax({
            type:"post",
            url:"/course/getChapters",
            data:JSON.stringify(GetJsonData()),
            dataType:"json",
            success:function(data){
                var serialnum=data.serialnum.toFixed(1);
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
                        var cs=data.chapters[i].serialnum.toFixed(1); // sishewuru
                        if((serialnum-cs)>0){

                            tem+="<li>"+data.chapters[i].chaptername+"<span>已学习</span></li>";

                        }else if((cs-serialnum)>0){
                            tem+="<li>"+data.chapters[i].chaptername+"<span>未解锁</span></li>";

                        }else{
                            tem+="<li><p><a href='../homework.jsp?serialnum="+cs+"&courseid="+data.courseid+"' >"+data.chapters[i].chaptername+"</a><span>已解锁～ 快点学习我</span></a></li>";

                        }
                    }

                    $("#homework").html(tem);
                }

            },error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    });




