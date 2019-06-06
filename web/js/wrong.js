
//换成JSON
function GetJsonData1() {
    var json = {
        "token":getCookie("token"),
    };
    return json;
}

function GetJsonData2(courseid) {
    var json = {
        "token":getCookie("token"),
        "courseid":courseid
    };
    return json;

}



function GetoJsonArray(homeworkid,courseid,reviewtimes,wrongtimes) {
    var json=[{
        "homeworkid": homeworkid,
        "courseid": courseid,
        "reviewtimes": reviewtimes,
        "wrongtimes":wrongtimes
    }];
    return json;
}

//换成JSON
function GetJsonData3(homeworkid,courseid,reviewtimes,wrongtimes) {
    var json = {
        "token":getCookie("token"),
        "mistakes":GetoJsonArray(homeworkid,courseid,reviewtimes,wrongtimes)
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
        url:"/personal/getStuInfo",
        data:JSON.stringify(GetJsonData1()),
        dataType:"json",
        success:function(data){
            setCookie("token",data.token);
            if(data.tag===0){
                layer.open({
                    content:'<h3 style="color:#f8b54d;">身份验证过期，请重新登录</h3>'
                    ,btn:['<p style="color:#f8b54d;">前往登录</p>']
                    ,yes:function(){
                        window.location.href="login.jsp";
                    }
                });
            }else{
               var courses=data.courses;
               for(var i=0;i<courses.length;i++){
                    var tem="<li><div class=\"bk-book book-1 bk-bookdefault\"> " +
                   "<div class=\"bk-front\"> " +
                   "<div class=\"bk-cover\" style=\"background-image: url(images/course/small/"+courses[i].img+");\"> " +
                   "<h2> " +
                   "<span>"+courses[i].coursename+"</span> " +
                   "</h2> " +
                   "</div> " +
                   "<div class=\"bk-cover-back\"></div> " +
                   "</div> " +
                   "<div class=\"bk-page\"> " +
                   "<div class=\"bk-right\"></div> " +
                   "<div class=\"bk-top\"></div> " +
                   "<div class=\"bk-bottom\"></div> " +
                   "</div> " +
                   "<div class=\"bk-info\"> " +
                   "<button class=\"bk-bookview\" onclick='getMistakes("+courses[i].courseid+");'>开始做题</button> " +
                   "<h3>" +
                   "<span>"+courses[i].coursename+"的错题本</span> " +
                   "</h3>" +
                   "</div> </li>";

               }
            $("#bk-list").html(tem);
        }},error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
});



function getMistakes(courseid){
    $.ajax({
        type:"post",
        url:"/homework/getMistakes",
        data:JSON.stringify(GetJsonData2(courseid)),
        dataType:"json",
        success:function(data){
            if(data.tag===0){
                alert("身份验证过期，请重新登录");
            }
            else{
                setCookie("token",data.token);
                var homework=data.mistakes;
                var tem = "";
                for(var i=0;i<homework.length;i++){
                    tem+="<tr><td colSpan='2' class='prod-column'>" +
                        "<div class='column-box'><h3 class='prod-title padd-top-20'>" +
                        homework[i].content+"</h3></div>" +"</td>";
                    tem+="<br>";
                    tem+="<td><input type='radio' id='aanswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\","+courseid+");' value='a'>"+homework[i].A+"</td>";
                    tem+="<td><input type='radio' id='banswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\","+courseid+");' value='b'>"+homework[i].B+"</td>";
                    tem+="<td><input type='radio' id='canswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\","+courseid+");' value='c'>"+homework[i].C+"</td>";
                    tem+="<td><input type='radio' id='danswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\","+courseid+");' value='d'>"+homework[i].D+"</td>";
                }
                tem+="</tr>";
                $("#homework").html(tem);
            }
        },error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}


function getAnswer(homeworkid,correct,courseid){
    var a="answer"+homeworkid;
    var ans=document.getElementsByName(a);
    var strAns;
    for(var i=0;i<ans.length;i++)
    {

        if(ans.item(i).checked) {
            document.getElementById("aanswer").disabled = true;
            document.getElementById("banswer").disabled = true;
            document.getElementById("canswer").disabled = true;
            document.getElementById("danswer").disabled = true;
            strAns = ans.item(i).getAttribute("value");
            if (strAns===correct){
                $.ajax({
                    type: "post",
                    url: "/homework/updateMistakes",
                    cache: false,
                    data: JSON.stringify(GetJsonData3(homeworkid,courseid,1,0)),
                    dataType: "json",
                    success: function(){
                    }
                });
            }else {
                $.ajax({
                    type: "post",
                    url: "/homework/updateMistakes",
                    cache: false,
                    data: JSON.stringify(GetJsonData3(homeworkid,courseid,0,1)),
                    dataType: "json",
                    success: function(){
                        layer.open({
                            content:'<span style="color:#f8b54d;">这道题选错啦～答案是'+correct+',下次再接再厉哦</span>'
                        });
                    }
                });
            }
            break;
        }
    }

}
//弹出一个提示层
$('.test1').click(function(){
    layer.open({
        title:  ['作业结果', 'color:#8373ce;']
        ,content: '<h3 style="color:#f8b54d;">恭喜你完成本课程错题, 连续做对三次以上错题就会删除了哦，再接再厉！～</h3>'
        ,anim:1
    });
});
