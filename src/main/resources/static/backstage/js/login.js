login = {
    inputcheck: function () {
        var inputstuid = $("#stuid").val();
        var inputpassword = $("#password").val();
        if (inputstuid.length == 0) {
            login.showNotification("Code Can`t Be Empty",'top','center')
            return;
        } else if (inputpassword.length == 0) {
            login.showNotification("Password Can't Be Empty!",'top','center')
            return;
        } else {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/admin/login",
                async: false,
                data: {"code": inputstuid, "password": inputpassword},
                success: function (result) {
                    console.log(result)
                    if(result.msg == "用户名或密码错误"){
                        login.showNotification(result.msg,'top','center');
                        $("#password").val("");
                        $("#password").focus();
                    }
                    else if(result.msg = "登录成功"){
                        var cookiedate = new Date();
                        cookiedate.setTime(cookiedate.getTime() + 5 * 60 * 1000);
                        $.cookie("token",result.token,{expires: cookiedate,path:"/",domain:window.DomainName});
                        $.cookie("code",result.code,{expires: cookiedate,path:"/",domain:window.DomainName});
                        window.location.href = "/main";
                    }
                },
                error: function () {
                    login.showNotification(result.msg,'top','center');
                },
            });
            return true;
        }
        ;
    },
    showNotification: function (msg, from, align) {
        color = 'primary';

        $.notify({
            icon: "now-ui-icons ui-1_bell-53",
            message: msg

        }, {
            type: color,
            timer: 2000,
            placement: {
                from: from,
                align: align
            }
        });
    }
}