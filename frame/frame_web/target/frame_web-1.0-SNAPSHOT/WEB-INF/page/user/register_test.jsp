<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2019/2/12
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/layer/layer.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/md5-min.js"></script>
    <script src="<%=request.getContextPath()%>/res/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/res/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
</head>
<body>

<form id="fm">
    <p>
        <label for="username">用户名：</label>
        <input id="username" name="username" type="text"/>
    </p>
    <p>
        <label for="password">密码：</label>
        <input id="password" name="password" type="password">
    </p>
    <p>
        <label for="password1">确认密码：</label>
        <input id="password1" name="password1" type="password">
    </p>
    <p>
        <label for="birth">生日：</label>
        <input id="birth" name="birth" type="text">
    </p>
    <p>
        <label for="sex">性别：</label>
        <input name="sex" type="radio" value="0">男
        <input name="sex" type="radio" value="1">女
    </p>
    <p>
        <label for="phone">手机号：</label>
        <input id="phone" name="phone" type="text"/>
        <%--<input type="button" value="获取短信验证码" onclick="getKey()" id="code"/>--%>
    </p>

    <p>
        <label for="idCard">身份证号：</label>
        <input id="idCard" name="idCard" type="text">
    </p>
    <p>
        <label for="email">邮箱：</label>
        <input id="email" name="email" type="text"/>
    </p>
    <p>
        <input type="button" onclick="reg()" value="确认注册">
        <a href="<%=request.getContextPath()%>/user/toLogin">已有账号，去登陆</a>
    </p>
</form>

</body>
<script>
    $(function () {
        $("#fm").validate({
            errorPlacement: function(error,element) {
                //将错误信息展示在父元素的后面
                $(element).parent().after(error);
            },
            rules:{
                username: {
                    required: true,
                    minlength: 2,
                    remote: {
                        url: "<%=request.getContextPath()%>/user/uniq",
                        type: "post",
                        data: {
                            userName: function () {
                                return $("#username").val()
                            }

                        }
                    }
                },
                    password: {
                        required: true,
                        //rangelength:[6,14],
                    },
                    password1: {
                        required: true,
                        //rangelength:[6,14],
                        equalTo: "#password" //输入值必须和 #passWord相同。
                    },
                    phone: {
                        required: true,
                        rangelength:[11,11],// 手机号必须是11位
                        remote:{
                            url:"<%=request.getContextPath()%>/user/uniq",
                            type:"post",
                            data:{userName:function(){return $("#phone").val()}}
                        }
                    },
                    birth: {
                        required: true,
                        // rangelength:[6,14],
                    },
                    idCard: {
                        required: true,
                        rangelength:[18,18],// 身份证必须是11位
                        remote:{
                            url:"<%=request.getContextPath()%>/user/uniq",
                            type:"post",
                            data:{userName:function(){return $("#idCard").val()}}
                        }
                    },
                    email: {
                        required: true,
                        email: true ,//必须输入正确格式的电子邮件。
                        remote:{
                            url:"<%=request.getContextPath()%>/user/uniq",
                            type:"post",
                            data:{userName:function(){return $("#email").val()}}
                        }
                    },
                    sex:"required",
                },

            messages:{
                    username: {
                    required: "请输入用户名",
                    minlength: "用户名至少由2个字母/数字/汉字组成",
                    remote:"用户名已存在,请重新注册"
                },
                password: {
                    required: "请输入密码",
                    // rangelength: "密码长度为 6-14 个字符",
                },
                password1: {
                    required: "请输入确认密码",
                    //rangelength: "密码长度为 6-14 个字符",
                    equalTo: "两次密码输入不一致"
                },
                phone: {
                    required: "请输入手机号密码",
                    rangelength: "手机号必须是11位的整数",
                    remote:"手机号密码已存在,请重新注册"
                },
                birth: {
                    required: "请输入生日",
                },
                idCard: {
                    required: "请输入身份证号码",
                    rangelength: "身份证号码必须是18位的整数",
                    remote:"身份证已存在,请重新注册"
                },
                email: {
                    required: "请输入邮箱",
                    rangelength: "请输入正确的邮箱格式",
                    remote:"邮箱已存在,请重新注册"
                },
                sex:"请选择性别",
            }
        })


    })


    $.validator.setDefaults({
        submitHandler: function(form) {
            var loading=layer.load();
            $.post("<%=request.getContextPath()%>/user/register",
                $("#fm").serialize(),
                function (data){
                    if(data.code == 200){
                        layer.msg("success", {icon: 6},function () {
                            window.location.href = "<%=request.getContextPath()%>/user/toLogin"

                        })
                        return
                    }
                    layer.close(loading);
                    layer.msg("error", {icon: 5})
                }
            )
        }
    })

</script>
</html>
