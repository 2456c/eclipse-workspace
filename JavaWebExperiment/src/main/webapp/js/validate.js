// 验证用户名/密码格式（与Service层正则一致）
function validateFormat(str) {
    var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d.*\d)(?=.*[a-zA-Z].*[a-zA-Z]).{8,}$/;
    return regex.test(str);
}

// 登录表单验证
function checkLoginForm() {
    var username = document.getElementById("username").value.trim();
    var password = document.getElementById("password").value.trim();

    if (username === "") {
        alert("用户名不能为空！");
        return false;
    }
    if (password === "") {
        alert("密码不能为空！");
        return false;
    }
    return true;
}

// 注册表单验证
function checkRegisterForm() {
    var username = document.getElementById("username").value.trim();
    var password = document.getElementById("password").value.trim();
    var name = document.getElementById("name").value.trim();
    var studentId = document.getElementById("studentId").value.trim();

    if (username === "") {
        alert("用户名不能为空！");
        return false;
    }
    if (!validateFormat(username)) {
        alert("用户名格式错误（≥8字符，含2字母、2数字、1大小写）！");
        return false;
    }
    if (password === "") {
        alert("密码不能为空！");
        return false;
    }
    if (!validateFormat(password)) {
        alert("密码格式错误（≥8字符，含2字母、2数字、1大小写）！");
        return false;
    }
    if (name === "") {
        alert("姓名不能为空！");
        return false;
    }
    if (studentId === "") {
        alert("学号不能为空！");
        return false;
    }
    return true;
}

// 修改用户名表单验证
function checkUsernameForm() {
    var newUsername = document.getElementById("newUsername").value.trim();

    if (newUsername === "") {
        alert("新用户名不能为空！");
        return false;
    }
    if (!validateFormat(newUsername)) {
        alert("新用户名格式错误（≥8字符，含2字母、2数字、1大小写）！");
        return false;
    }
    return true;
}

// 修改密码表单验证
function checkPasswordForm() {
    var oldPwd = document.getElementById("oldPwd").value.trim();
    var newPwd = document.getElementById("newPwd").value.trim();
    var confirmPwd = document.getElementById("confirmPwd").value.trim();

    if (oldPwd === "") {
        alert("旧密码不能为空！");
        return false;
    }
    if (newPwd === "") {
        alert("新密码不能为空！");
        return false;
    }
    if (!validateFormat(newPwd)) {
        alert("新密码格式错误（≥8字符，含2字母、2数字、1大小写）！");
        return false;
    }
    if (newPwd !== confirmPwd) {
        alert("新密码与确认密码不一致！");
        return false;
    }
    return true;
}