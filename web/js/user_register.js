function GetJsonData() {

    var regist_time = (new Date()).getTime();
    console.log(regist_time); //ms

    var json = {
        "username":$("#username").val(),
        "userowd":$("#userpwd").val(),
        "email":$("#email").val(),
        "phone":$("#phone").val(),
        "sex":$("#sex").val(),
        "identity":$("#identity").val(),
        "birthday":$("#birthday").val(),
        "register_time":regist_time,
        "login_time":regist_time
    };
    return json;
}

function ajax_reg_servlet() {
    $.ajax({
        type:"get",
        url:"/auth/Register", //跳转
        data:JSON.stringify(GetJsonData),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        async:false,
        success:function(msg){
            alert(msg.tag);
        }, error: function (XMLHttpRequest, textStatus) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
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