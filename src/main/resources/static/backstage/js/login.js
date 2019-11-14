login = {
    inputcheck: function () {
        var inputstuid = $("#stuid").val();
        var inputpassword = $("#password").val();
        if (inputstuid.length == 0) {
            login.showNotification("StuID Can`t Be Empty",'top','center')
            return;
        } else if (inputpassword.length == 0) {
            login.showNotification("Password Can't Be Empty!",'top','center')
            return;
        } else {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/login",
                async: false,
                data: {"stuid": inputstuid, "password": inputpassword},
                success: function (result) {
                    if(result.msg == "not have this stuid"){
                        login.showNotification(result.msg,'top','center');
                        $("#stuid").val("")
                        $("#password").val("");
                        $("#stuid").focus();
                    }
                    else if(result.msg == "password error"){
                        login.showNotification(result.msg,'top','center');
                        $("#password").val("");
                        $("#password").focus();
                    }
                    else if (result.msg == "login failed"){
                        login.showNotification(result.msg,'top','center');
                        $("#stuid").val("")
                        $("#password").val("");
                        $("#stuid").focus();
                    }
                    else if (result.msg == "login success") {
                        var cookiedate = new Date();
                        cookiedate.setTime(cookiedate.getTime() + 5 * 60 * 1000);
                        $.cookie("token",result.token,{expires: cookiedate,path:"/",domain:window.DomainName});
                        $.cookie("stuid",result.stuid,{expires: cookiedate,path:"/",domain:window.DomainName});
                        window.location.href = "student";
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