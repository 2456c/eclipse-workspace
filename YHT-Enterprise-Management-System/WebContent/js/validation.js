function validatePassword(password) {
    // 规则：长度>=8, 2字母(1大1小), 2数字
    var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d.*\d)[A-Za-z\d@$!%*?&]{8,}$/;
    return regex.test(password);
}

function validateLogin() {
    // 简单登录验证
    var u = document.getElementsByName("username")[0].value;
    var p = document.getElementsByName("password")[0].value;
    if(u.trim() == "" || p.trim() == "") {
        alert("用户名和密码不能为空");
        return false;
    }
    return true;
}

function validateApply() {
    var pwd = document.getElementsByName("desiredPassword")[0].value;
    if(!validatePassword(pwd)) {
        alert("密码强度不足：需至少8位，包含大小写字母及至少2个数字");
        return false;
    }
    return true;
}
