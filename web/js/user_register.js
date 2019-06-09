function GetJsonData(){

    var regist_time = (new Date()).getTime();
    console.log(regist_time); //ms

    var sex;
    var identity;

    if($("#sex").val()==="女"){
        sex=1;
    }else{
        sex=2;
    }

    if($("#identity").val()==="学生"){
        identity=2;
    }else{
        identity=3;
    }
    alert("sex"+sex+"identity"+identity);

    var json = {
        "username":$("#username").val(),
        "userpwd":$("#userpwd").val(),
        "email":$("#email").val(),
        "phone":$("#phone").val(),
        "sex":sex,
        "identity":identity,
        "birthday":$("#birthday").val(),
        "regist_time":regist_time,
        "login_time":regist_time
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
    //alert("调用cookie成功");
}

function ajax_reg_servlet() {
   // alert(JSON.stringify(GetJsonData()));
    $.ajax({
        type:"post",
        url:"/auth/Register", //跳转
        data:JSON.stringify(GetJsonData()),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        async:false,
        success:function(msg){
            if(msg.tag===0){
                layer.open({
                    title:  ['提示', 'color:#8373ce;']
                    ,content: '<h3 style="color:#f8b54d;">检查一下账号密码是否规范哦～</h3>'
                });
            }else{
                window.location.href="index.jsp";
                setCookie("token",msg.token);
            }
        }, error: function (XMLHttpRequest, textStatus) {
            // alert(XMLHttpRequest.status);
            // alert(XMLHttpRequest.readyState);
            // alert(textStatus);
        }
    });

}

function register_submit(){
    if(judgeUsername(document.getElementById("username").value)===true){
        if(judgeUserpwd(document.getElementById("userpwd").value)===true){
            if(judgeEmail(document.getElementById("email").value)===true){
                if(judgePhone(document.getElementById("phone").value)===true){
                    if( judgeBirthday(document.getElementById("birthday").value)===true){
                        ajax_reg_servlet();
                    }

                }
            }
        }
    }
}

function judgeUsername(username) {
    if(username==="") {
        document.getElementById("alert_username").innerHTML="记得写名字";
        return false;
    }
    else {
        document.getElementById("alert_username").innerHTML="";
        return true;
    }
}

function judgeUserpwd(password) {
    var flag = 0;
    if(password.length<6 || password.length>20){
        flag = 1;
    }
    var Reg = /^[0-9a-zA-Z]*$/;
    if (Reg.test(password)===false) flag = 1;
  
    if (flag === 1)  {
        document.getElementById("alert_password").innerHTML="密码格式不对";
        return false;
    }
    else {
        document.getElementById("alert_password").innerHTML="";
        return true;
    }


}

function judgeEmail(email) {
    var flag1 = 0;
    if(email.length===0)
    {
        flag1 = 1;
    }
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

    if(myreg.test(email) === false){
        flag1 = 1;
    }

    if (flag1 === 1)  {
        document.getElementById("alert_email").innerHTML="邮箱格式不对";
        return false;
    }
    else {
        document.getElementById("alert_email").innerHTML="";
        return true;
    }

}

function judgePhone(email) {
    var flag2 = 0;
    if(email.length===0)
    {
        flag2 = 1;
    }
    var myreg3 =  /^(((13[0-9])|(15[0-9])|(18[0-9]))+\d{8})$/;

    if(myreg3.test(email) === false){
        flag2 = 1;
    }
    if (flag2 === 1)  {
        document.getElementById("alert_phone").innerHTML="phone格式不对";
        return false;
    }
    else {
        document.getElementById("alert_phone").innerHTML="";
        return true;
    }

}

function judgeBirthday(birthday) {
    if(birthday.length!==8) {
        document.getElementById("alert_birthday").innerHTML="生日格式注意";
        return false;
    }
    else {
        document.getElementById("alert_birthday").innerHTML="";
        return true;
    }

}