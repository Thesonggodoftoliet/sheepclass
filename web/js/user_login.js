function login_submit() {
    var usernum = document.getElementById("account").value;
    var password = document.getElementById("userpwd").value;
     alert("9999");
     if(checkPassword(password)===false) {
         document.getElementById("alert_password").innerHTML="请输入正确的密码";
         document.getElementById("alert_usernum").innerHTML="";
         return false;

     }else if(checkPhone(usernum)===false && checkEmail(usernum)===false) {
         document.getElementById("alert_usernum").innerHTML="请输入正确的手机号或者邮箱";
         document.getElementById("alert_password").innerHTML="";
         return false;

     }else{
         document.getElementById("alert_password").innerHTML="";
         document.getElementById("alert_usernum").innerHTML="";
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
    if(usernum.length!==11)
    {
        return false;
    }
    var myreg = /^(((13[0-9])|(15[0-9])|(18[0-9]))+\d{8})$/;
    return !myreg.test(usernum) !== false;


}

function checkEmail(usernum) {
    if(usernum.length===0)
    {
        return false;
    }
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return !myreg.test(usernum) !== false;
}