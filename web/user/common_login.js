function submit_judge(){
    var flag = true;
    var input_password = document.getElementById("input_password").value;
    var input_usernumber  = document.getElementById("input_usernumber").value;
     //alert("hhh"+input_usernumber+"jjj"+input_password);
    if (input_usernumber==="") {  //用户框value值为空
        document.getElementById("alert_usernumber").innerHTML = "请输入手机号或邮箱进行绑定";
        flag = false;
    }else if (input_password==="") {  //密码框value值为空
        document.getElementById("alert_password").innerHTML = "请输入密码！";
        flag = false;
    }else if(input_password.length<6 || input_password.length>20){
        document.getElementById("alert_password").innerHTML = "密码长度必须在6-20位置之间";
        flag = false;
    }else if(CheckPassWord(input_password)===false){
        document.getElementById("alert_password").innerHTML = "密码错误";
        flag = false;
    }else if(CheckUserPhone(input_usernumber)===false && CheckUserEmail(input_usernumber)===false){
        document.getElementById("alert_usernumber").innerHTML = "请输入正确的手机号或邮箱号格式";
        flag = false;
    }else{
        document.getElementById("alert_usernumber").innerHTML = "";
        document.getElementById("alert_password").innerHTML = "";
        flag = true;
    }
    return flag;
}

/**
 * @return {boolean}
 */
function CheckUserPhone(usernumber) {
    //手机号验证
    if(usernumber.length!==11) return false;
    var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if(!reg.test(usernumber)){
        return false;
    }else return true;
}

/***
 * @return {boolean}
 */
function CheckUserEmail(usernumber) {
    //对电子邮件的验证
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(!reg.test(usernumber)){
        return false;
    }else return true;

}


/**
 * @return {boolean}
 */
function CheckPassWord(password) {//必须为字母加数字
    var reg1;
    reg1 = new RegExp(/^[0-9A-Za-z]+$/);
    if (reg1.test(password)===false) {
        return false;
    }
    var reg2;
    reg2= new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
    if (reg2.test(password)===false) {
        return false;
    }
    return true;

}