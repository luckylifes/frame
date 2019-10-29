<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2019/1/21
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/layer/layer.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/md5-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/jsencrypt/jsencrypt.min.js"></script>

</head>
<body>
        <form id="fm">
            用户名 <input type="text" name= "username" placeholder="用户名/手机号/邮箱"  />
            密码    <input type="password" id = "password" name= "password" placeholder="密码" /><br>
            <input type="button" value= "login" onclick="login()"/>
        </form>
        <br>

        <a href="javascript:reg()">还没有账号?点这里注册</a> 1234567890

        <a href="javascript:exportList()">导出excel</a>
</body>
<script>

    function login(){
        if("" != $("#password").val()){
            var password = md5($("#password").val());
            $("#password").val(password);
        }

        var encrypt = new JSEncrypt();
        // 公钥
        encrypt.setPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmclSGCuE0u94oxbkIB5vFK/yqSMECpJQnZkPA9u0fPC8QZTTi+Vsv5DgvweJWxXuaylJoyLbWFjaOFhB2eSgFAvjpi7V3YDti0g9gltsyK1LJzdNonKQudkaDk+UawSuMr5zWMGx35M3I3YtspGfI1CG3dCQAV88/yseUN1MifU8bYiFJkZkYdHfBUrBK6zR4pdghLAeucaWofyH7pLcLC7wbSgnkZ/lf9lxkcWQSD55/j5UTynixHJ9cTvLcFokG32OuFj/tVRADSCfIzfN1xhWRelFms5/KWJRRF3Ql37HVeI8+q5fvdql0wz3nNR6j0zphwe9gVJFFRmxMhqgIQIDAQAB");
        // 加密方式   == 数据格式     "name="+$("#name").val()+"&pwd="+$("#name").val()
        var encrypted = encrypt.encrypt($("#fm").serialize());
        /* $("#age").val(encrypted); */
        $.post(
            "<%=request.getContextPath()%>/user/login.rsa",
            {"data":encrypted},
            function(data){
                if(data.code == 200){
                    layer.msg('登陆成功', {
                        icon: 6,
                        time: 500 //1秒关闭（如果不配置，默认是3秒）
                    },function(){
                        var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                        window.location.href = "<%=request.getContextPath()%>/user/toIndex?token="+data.data.token;
                        parent.layer.close(index);
                    });
                } else {
                    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                    layer.msg(data.msg,{icon: 5,time:500});
                    parent.layer.close(index);

                }
            }
        );
    }


    function reg(){
        window.location.href = "<%=request.getContextPath()%>/user/toRegister";
    }
    function exportList(){
        window.location.href = "<%=request.getContextPath()%>/exportUserList";
    }


</script>
</html>
