function clearCookie(){
    var myDate=new Date();
    myDate.setTime(-1000);//设置时间
    var data=document.cookie;
    var dataArray=data.split("; ");
    for(var i=0;i<dataArray.length;i++){
        var varName=dataArray[i].split("=");
        document.cookie=varName[0]+"=''; expires="+myDate.toGMTString();
    }
    window.location.reload();
}