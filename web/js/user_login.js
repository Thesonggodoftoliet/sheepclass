function ajax_login_servlet() {
    alert($('#account').val());
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/sheepclass_war_exploded/auth/Login", //跳转
        data: {account:$('#account').val(),userpwd:$('#userpwd').val()},
        dataType:"json",
        contentType:"application/json",
        async:false,
        success:function(msg){
            alert(msg.tag);
        }, error: function (XMLHttpRequest, textStatus) {
            alert("失败");
        }
    });

}

function login_submit() {
    var usernum = document.getElementById("account").value;
    var password = document.getElementById("userpwd").value;
     //alert("9999");
     if(checkPassword(password)===false) {
         document.getElementById("alert_password").innerHTML="请输入正确的密码";
         document.getElementById("alert_usernum").innerHTML="";
         return false;

     }else if(checkPhone(usernum)===false){
         if( checkEmail(usernum)===false) {
             document.getElementById("alert_usernum").innerHTML = "请输入正确的手机号或者邮箱";
             document.getElementById("alert_password").innerHTML = "";
             return false;
         }else{
             document.getElementById("alert_password").innerHTML="";
             document.getElementById("alert_usernum").innerHTML="2";
             ajax_login_servlet();
             return true;
         }
     }else if(checkPhone(usernum)===true){
         document.getElementById("alert_password").innerHTML="";
         document.getElementById("alert_usernum").innerHTML="";
         ajax_login_servlet();
         return true;
     }else{
         document.getElementById("alert_password").innerHTML="";
         document.getElementById("alert_usernum").innerHTML="";
         ajax_login_servlet();
         return true;
     }


}

function checkPassword(password){

    if(password.length<6 || password.length>20){
        return false;
    }
    var Reg = /^[0-9a-zA-Z]*$/;
    return Reg.test(password) !== false;

}

function checkPhone(usernum) {
    if (usernum.length !== 11)
    {
        alert(1);
        return false;
    }
    var myreg1 = /^(((13[0-9])|(15[0-9])|(18[0-9]))+\d{8})$/;
    alert(myreg1.test(usernum));
    return !myreg1.test(usernum) !== false;


}

function checkEmail(usernum) {
    if(usernum.length===0)
    {
        return false;
    }
    var myreg2 = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    alert(myreg2.test(usernum));
    return !myreg2.test(usernum) !== false;
}