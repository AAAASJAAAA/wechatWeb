$(function () {
    var stuid = $.cookie("stuid");
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/signinfo",
        data: {"stuid": stuid},
        success: function (list) {
            var html = ''
            console.log(list)
            for (var i = 0; i < list.length; i++) {
                var s = '<tr><td>'+list[i]['stu_name']+'</td><td>'+list[i]['cou_name']+'</td><td>'+list[i]['signinfo']+'</td></tr>';
                html +=s;
            }
            $("#info").html(html);
        },
        error: function (result) {

        }
    })
});