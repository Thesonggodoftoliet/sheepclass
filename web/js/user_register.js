function ajax_reg_servlet() {

    var regist_time = (new Date()).getTime();
    console.log(regist_time); //ms

    var username = document.getElementById("username").value;
    var userpwd = document.getElementById("userpwd").value;
    var email = document.getElementById("email").value;
    var phone = document.getElementById("phone").value;
    var sex = document.getElementById("sex").value;
    var identity = document.getElementById("identity").value;
    var birthday =  document.getElementById("birthday").value;
    var obj = JSON.stringify({"username":username,
        "userpwd":userpwd,
        "email":email,
        "phone":phone,
        "sex":sex,
        "identity":identity,
        "birthday":birthday,
        "register_time":regist_time,
        "login_time":regist_time});
    alert(obj);
    $.ajax({
        type:"post",
        url:"http://localhost:8080/sheepclass_war_exploded/auth/Register", //跳转
        data: obj,
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
    judgeUsername(document.getElementById("username").value);
    judgeUserpwd(document.getElementById("userpwd").value);
    judgeEmail(document.getElementById("email").value);
    judgePhone(document.getElementById("phone").value);
    judgeBirthday(document.getElementById("birthday").value);

    ajax_reg_servlet();
}

function judgeUsername(username) {
    if(username==="") document.getElementById("alert_username").innerHTML="记得写名字";
    else document.getElementById("alert_username").innerHTML="";

}

function judgeUserpwd(password) {
    var flag = 0;
    if(password.length<6 || password.length>20){
        flag = 1;
    }
    var Reg = /^[0-9a-zA-Z]*$/;
    if (Reg.test(password)===false) flag = 1;

    if (flag === 1)  document.getElementById("alert_password").innerHTML="密码格式不对";
    else document.getElementById("alert_password").innerHTML="";


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

    if (flag1 === 1)  document.getElementById("alert_email").innerHTML="邮箱格式不对";
    else document.getElementById("alert_email").innerHTML="";

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

    if (flag2 === 1)  document.getElementById("alert_phone").innerHTML="phone格式不对";
    else document.getElementById("alert_phone").innerHTML="";

}

function judgeBirthday(birthday) {
    alert("记得写生日");
    if(birthday==="") document.getElementById("alert_birthday").innerHTML="记得写生日";
    else document.getElementById("alert_birthday").innerHTML="";

}