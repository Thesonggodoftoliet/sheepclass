function GetJsonData() {
    var json = {
        "account":$("#account").val(),
        "userpwd":$("#userpwd").val()
    };
    return json;
}

function ajax_login_servlet() {
    alert($('#account').val());
    $.ajax({
        type:"post",
        url:"/auth/Login", //跳转
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify(GetJsonData()),
        dataType:"json",
        async:false,
        success:function(msg){
            alert("hhh");
        }, error: function (XMLHttpRequest, textStatus) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });

}

function login_submit() {
    var usernum = document.getElementById("account").value;
    var password = document.getElementById("userpwd").value;
    if(checkPhone(usernum)===true ||checkEmail(usernum)===true){
        if(checkPassword(password)===true){
            ajax_login_servlet();
        }
    }

}

function checkPassword(password){
    var flag1 = 0;
    if(password.length<6 || password.length>20){
        flag1 = 1;
    }
    var Reg = /^[0-9a-zA-Z]*$/;
    if(Reg.test(password)===false){
        flag1 = 1;
    }else{
        flag1 = 0;
    }

    if(flag1===1){
        document.getElementById("alert_password").innerHTML="请输入正确的密码";
        return false;
    }else{
        document.getElementById("alert_password").innerHTML="";
        return true;
    }
}

function checkPhone(usernum) {
    if (usernum.length !== 11)
    {
        document.getElementById("alert_usernum").innerHTML = "请输入正确的手机号或者邮箱";
        return false;
    }
    var myreg1 = /^(((13[0-9])|(15[0-9])|(18[0-9]))+\d{8})$/;
    if(myreg1.test(usernum) === false){
        document.getElementById("alert_usernum").innerHTML = "请输入正确的手机号或者邮箱";
        return false;
    }else{
        document.getElementById("alert_usernum").innerHTML = "";
        return true;
    }


}

function checkEmail(usernum) {
    if(usernum.length===0)
    {
        document.getElementById("alert_usernum").innerHTML = "请输入正确的手机号或者邮箱";
        return false;
    }
    var myreg2 = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(myreg2.test(usernum) === false){
        document.getElementById("alert_usernum").innerHTML = "请输入正确的手机号或者邮箱";
        return false;
    }else{
        document.getElementById("alert_usernum").innerHTML = "";
        return true;
    }
}