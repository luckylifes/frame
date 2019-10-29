<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2019/1/21
  Time: 18:59
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
    <input type="hidden" name="id" value="${user.id}"/>
    用户名:<input type="text" name="username" value="${user.username}"/><br>
    密--码 :<input type="password" id = "password" name="password" value="${user.password}"/><br>
    生日 :<input type="text" id="d243" name= "birth" value="${user.birth}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/><br>
    性别	  男： <input type="radio" name="sex" value="1" <c:if test="${user.sex == 1}"> checked</c:if>/>
    女：  <input type="radio" name="sex" value="2"  <c:if test="${user.sex == 2}"> checked</c:if>/><br>
    身份证号<input type = "text" name= "idCard" value="${user.idCard}"/> <br>
    邮箱<input type = "text" name= "email" value="${user.email}"/> <br>
    手机号<input type = "text" name= "phone" value="${user.phone}"/> <br>
    <input type="hidden" name="_method" value="PUT"/>
    <input type="button" value="up" onclick="update()" />



</form>

</body>
<script type="text/javascript">
    function update(){
        var index1 = layer.load();
        $.post(
            "<%=request.getContextPath()%>/user/update?token=${token}",
            $("#fm").serialize(),
            function(data){
                if(data.code == 200){
                    layer.msg('success', {
                        icon: 1,shade: 0.01,time: 500
                    }, function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        parent.location.href = "<%=request.getContextPath()%>/user/toList?token="+data.data.token;
                    });
                } else {
                    layer.close(index1);
                    layer.msg(data.msg,{icon: 5,time:500});
                }
            }
        );
    }


















</script>
</html>
