$(function(){
    var stuid = $.cookie("stuid");
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/student",
        async:false,
        data: {"stuid": stuid},
        success : function (result) {
            $("#stuid").attr("value",result.stuid);
            $("#stusex").attr("value",result.stusex);
            $("#firstname").attr("value",result.stuname.substring(0,1));
            $("#lastname").attr("value",result.stuname.substring(1));
            $("#name_right").html(result.stuname);
            $("#username").text(result.stuname);
            $("#stuface").attr("src",result.stuface);
            $("#userface").attr("src",result.stuface);
        }
    })
});
$("#stuemail").blur(function () {
    var info = null;
    info = $("#stuemail").val();
    if (info.length != 0) {
        $("#button").removeAttr("disabled");
    }
    else {
        $("#button").attr("disabled","false");
    }
});
