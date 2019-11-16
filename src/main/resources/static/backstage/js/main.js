String.prototype.format= function() {
    if(arguments.length === 0) return this;
    var param = arguments[0], str= this;
    if(typeof(param) === 'object') {
        for(var key in param)
            str = str.replace(new RegExp("\\{" + key + "\\}", "g"), param[key]);
        return str;
    } else {
        for(var i = 0; i < arguments.length; i++)
            str = str.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
        return str;
    }
}


main_js = {
    index: function () {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/apponitment/findOrder",
                async: false,
                // data: {"code": inputstuid, "password": inputpassword},
                success: function (result) {
                    html = ''
                    for (var data in result)
                    {
                        res = result[data]
                        status = ""
                        if(res.status == "1")
                        {
                            status = "未接单"
                        }else if (res.status == "2")
                        {
                            status = "已接单"
                        }else if (res.status == "3")
                        {
                            status = "已完成"
                        }

                        text = '<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td><td>{5}</td><td><button class="btn btn-info btn-sm" onclick="main_js.ordered(\'{6}\')">接单</button><button class="btn btn-danger btn-sm"  onclick="main_js.finished(\'{6}\')">完成</button></td></tr>'.format(res.name,res.address,res.phonenumber,res.chinaid,res.appointmenttime,status,res.id)
                        html += text
                    }
                    $("#info").html(html)


                },
                error: function () {
                    login.showNotification("获取数据失败！",'top','center');
                }
        })
    },
    ordered:function(id)
    {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/apponitment/ordered",
            async: true,
            data: {"id":id},
            success: function (result) {
                main_js.showNotification(result.msg,'top','center');
                main_js.index()
            },
            error: function () {
                main_js.showNotification("执行失败",'top','center');
                main_js.index()
            }
        })
    },
    finished:function(id)
    {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/apponitment/finished",
            async: true,
            data: {"id":id},
            success: function (result) {
                main_js.showNotification(result.msg,'top','center');
                main_js.index()
            },
            error: function () {
                main_js.showNotification("执行失败",'top','center');
                main_js.index()
            }
        })
    },
    showNotification: function (msg, from, align) {
        color = 'primary';

        $.notify({
            icon: "now-ui-icons ui-1_bell-53",
            message: msg

        }, {
            type: color,
            timer: 500,
            placement: {
                from: from,
                align: align
            }
        });
    },
}