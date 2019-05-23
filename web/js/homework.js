//换成JSON
function GetJsonData() {
    var json = {
        "token":getCookie("token"),
        "courseid":$("#courseid").html(),
        "serialnum":$("#serialnum").html()
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
    alert("hhh");
    $.ajax({
        type:"post",
        url:"/homework/getHomework",
        data:JSON.stringify(GetJsonData()),
        dataType:"json",
        success:function(data){
            alert("success");
            if(data.tag===0){
                alert("身份验证过期，请重新登录");
            }
            else{
                setCookie("token",data.token);
                var homework=data.homework;
                var tem = "<section class='event style-2'><div class='container'><div class='row'>";
                for(var i=0;i<homework.length;i++){
                    tem+=homework[i].content;
                    tem+="<br><br>";
                    tem+="<input type='radio' id='aanswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\");' value='a'>"+homework[i].A;
                    tem+="<input type='radio' id='banswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\");' value='b'>"+homework[i].B;
                    tem+="<input type='radio' id='canswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\");' value='c'>"+homework[i].C;
                    tem+="<input type='radio' id='danswer' name='answer"+homework[i].homeworkid+"' onchange='getAnswer("+homework[i].homeworkid+",\""+homework[i].correct+"\");' value='d'>"+homework[i].D;
                }
                tem+="</div></div></section>";
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
    // var numString = (num-1).toString();
    for(var i=0;i<ans.length;i++)
    {

        if(ans.item(i).checked) {
            document.getElementById("aanswer").disabled = true;
            document.getElementById("banswer").disabled = true;
            document.getElementById("canswer").disabled = true;
            document.getElementById("danswer").disabled = true;
            strAns = ans.item(i).getAttribute("value");
            if (strAns == correct) alert("你真棒～ 你怎么这么厉害呢！~");
            else {
                alert("答案是" + correct + "哦~ 下次再接再厉！！");
                alert(homeworkid+"  "+$("#courseid").html());
                $.ajax({
                    type: "post",
                    url: "/homework/updateMistakes",
                    cache: false,
                    data: JSON.stringify(GetJsonData1(homeworkid,$("#courseid").html())),
                    dataType: "json",
                    success: function () {
                    }
                });
            }
            break;
        }
    }

};