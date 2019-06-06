//换成JSON
function GetJsonData() {
    var json = {
        "token":getCookie("token"),
        "courseid":$("#courseid").html(),
        "serialnum":$("#serialnum").html()
    };
    return json;
}

function GetJsonData2(isFinish,next){
    var json = {
        "token":getCookie("token"),
        "courseid":$("#courseid").html(),
        "serialnum" : next,
        "isFinish" : isFinish,
        "breaktime" : 123,
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
function GetJsonData1(homeworkid,courseid) {
    var json = {
        "token":getCookie("token"),
        "mistakes":GetoJsonArray(homeworkid,courseid,0,1)
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
        url:"/homework/getHomework",
        data:JSON.stringify(GetJsonData()),
        dataType:"json",
        success:function(data){
            if(data.tag===0){
                alert("身份验证过期，请重新登录");
            }
            else{
                setCookie("token",data.token);
                var homework=data.homework;
                var tem = "";
                for(var i=0;i<homework.length;i++){
                    tem+="<tr><td colSpan='2' class='prod-column'>" +
                         "<div class='column-box'><h3 class='prod-title padd-top-20'>" +
                         homework[i].content+"</h3></div>" +"</td>";
                    tem+="<br>";
                    tem+="<td><input type='radio' id='aanswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\");' value='a'>"+homework[i].A+"</td>";
                    tem+="<td><input type='radio' id='banswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\");' value='b'>"+homework[i].B+"</td>";
                    tem+="<td><input type='radio' id='canswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\");' value='c'>"+homework[i].C+"</td>";
                    tem+="<td><input type='radio' id='danswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\");' value='d'>"+homework[i].D+"</td>";
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
});


function getAnswer(homeworkid,correct){
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
                right=right+1;
            }else {
                $.ajax({
                    type: "post",
                    url: "/homework/updateMistakes",
                    cache: false,
                    data: JSON.stringify(GetJsonData1(homeworkid,$("#courseid").html())),
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
    alert(parseFloat($("#serialnum").html())+0.1);
    $.ajax({
        type:"post",
        url:"/course/updateBreakTime",
        data:JSON.stringify(GetJsonData2(0,parseFloat($("#serialnum").html())+0.1)),
        dataType:"json",
        success:function(data){
        },error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });

    layer.open({
        title:  ['作业结果', 'color:#8373ce;']
        ,content: '<h3 style="color:#f8b54d;">恭喜你完成作业, 你的所有错题都加入到了错题中心中哦～</h3>'
        ,anim:1
    });
});