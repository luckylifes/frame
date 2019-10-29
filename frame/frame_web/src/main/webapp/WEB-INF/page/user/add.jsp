<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2019/1/21
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/layer/layer.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/md5-min.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/My97DatePicker/WdatePicker.js"></script>

</head>
<body>
<form id="fm">
    用户名 <input type = "text" name= "username"/> <br>
    密码    <input type = "password" id = "password" name= "password"/><br>
     <%-- <input type> = "text"  name= "birth"/><br--%>
   生日 :<input type="text" id="d243" name= "birth" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/><br>
    性别	  男： <input type="radio" name="sex" value="1"/>
    女：  <input type="radio" name="sex" value="2" /><br>
    身份证号<input type = "text" name= "idCard"/> <br>
    邮箱<input type = "text" name= "email"/> <br>
    手机号<input type = "text" name= "phone"/> <br>
    <input type = "button" value= "add" onclick="add()"/>
</form>
${msg }
</body>

<script type="text/javascript">

    function flush(){
        var img = document.getElementById("img");
        img.src = "<%=request.getContextPath()%>/user/getVerifCode?now="+new Date();
    }
    function reImg(){
        /* 点击图片刷新  点击按钮刷新   登陆失败 再调方法刷新验证码  */
        flush();
    }




    function add(){
        var index1 = layer.load();
        $.post(
            "<%=request.getContextPath()%>/user/add?token=${token}",
            $("#fm").serialize()
            ,function(data){
                if(data.code == 200){
                    layer.msg('success', {
                        icon: 1,shade: 0.01,time: 500
                    }, function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        window.location.href = "<%=request.getContextPath()%>/user/toIndex?token="+data.data.token;
                    });
                } else {
                    layer.close(index1);
                    layer.msg(data.msg,{icon: 5,time:500});
                }
            }
        )
    }

    <%-- function register(){
        /* var index = layer.load(2, {time: 10*1000}); //又换了种风格，并且设定最长等待10秒  */
            $.post(
                "<%=request.getContextPath()%>/user/register",
                $("#fm").serialize(),
                function(data){
                    if(data.code == 200){
                        layer.msg('注册成功', {
                              icon: 6,
                              time: 400 //1秒关闭（如果不配置，默认是3秒）
                        },function(){
                            var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                            parent.location.href = "<%=request.getContextPath()%>/user/toIndex";
                            parent.layer.close(index);
                        });
                    } else {
                        /* var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2 */
                        layer.msg(data.msg,{icon: 5,time:500});
                        /* parent.layer.close(index); */
                    }
                }
            );
        } --%>



    <%-- function register(){
        $.post("<%=request.getContextPath()%>/user/register",
                $("#fm").serialize(),
                function(data){
                    if(data.code == 200){
                        layer.msg(data.msg,{icon: 6,time:3000});
                        window.location.href = "<%=request.getContextPath()%>/user/toIndex";
                    }else{
                        layer.msg(data.msg,{icon: 5,time:1000});
                    }
        })
    } --%>


    <%--  function register(){
        var index = layer.load(2, {time: 10*1000}); //又换了种风格，并且设定最长等待10秒
        $.post(
                "<%=request.getContextPath()%>/user/register",
                $("#fm").serialize(),
                function(data){
                    layer.close(index);
                    if(data.code == 200){
                        layer.alert(data.msg, {icon: 6});
                        window.location.href ="<%=request.getContextPath()%>/user/toIndex";
                    } else {
                        layer.alert(data.msg, {icon: 5});
                        }
                }
        );
    } --%>

</script>
</html>
