$("#inputface").change(function() {
    readURL(this);
});
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#stuface').attr('src', e.target.result).fadeIn('slow');
        }
        reader.readAsDataURL(input.files[0]);
    }
    submit2()

    function submit2() {
        var type = "file";
        var stuid = $("#stuid").val(); //后台接收时需要的参数名称，自定义即可
        var formData = new FormData();
        formData.append("file", $("#inputface")[0].files[0]);    //生成一对表单属性
        formData.append("id", stuid);    //生成一对表单属性
        $.ajax({
            type: "POST",           //因为是传输文件，所以必须是post
            url: '/imageUpload',         //对应的后台处理类的地址
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                alert(data);
            }
        });
    }

    function inputimg() {
        var formdata = new FormData();
        var stuid = $("#stuid").val();
        formdata.append('fileName', $('#inputface').get(0).files[0]);
        $.ajax({
            type: 'POST',
            url: "/imageUpload",
            data: {"formdata": formdata},
            headers: {'Content-Type': 'multipart/form-data'},
            contentType: false,//ajax上传图片需要添加
            processData: false,//ajax上传图片需要添加
            success: function (data) {
                // if(data.hasOwnProperty("relativePath")){
                //     $("#stuface").html("<img src='"+data.relativePath+"'/>");
                // }
                // else {
                //     $("#showImage").html("上传失败");
                // }
                alert(data);
                window.location.reload();
            },
            error: function (e) {
                alert("error");
            }
        })
    }
}