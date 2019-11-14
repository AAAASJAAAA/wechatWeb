function CheckRegForm() {
    var inputName = $("#username").val()
    var inputPassword = $("#password").val()
    var inputPassword2 = $("#password2").val()
    if (inputName.length == 0) {
        alert("用户名不能为空！!");
        return false;
    } else if (inputPassword.length == 0) {
        alert("密码不能为空！");
        return false;
    } else if (inputPassword != inputPassword2) {
        alert("密码不一致！！!");
        return false;
    } else {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/reg",
            data: {"username": inputName,"password":inputPassword,"password2":inputPassword2},
            success : function (data) {
                alert(data.msg);
                window.location.href = "login";
            },
            error : function (data) {
                alert("error" + data)
            }
        });
        return true;
    }
}

$(document).ready(function(){
    $("#username").blur(function(){
        var inputName = $("#username").val()
        $.ajax({
            type: "POST",
            url: "/checkid",
            dataType: "text",
            data:{"username":inputName},
            success: function(result){
                console.log(result)
                alert("")
            }
        });
    });
});