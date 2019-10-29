<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2019/1/21
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<frameset rows="30%,*">
    <frame src="<%=request.getContextPath()%>/user/toTop?token=${token}" name="top">
    <frameset cols="30%,*">
        <frame src="<%=request.getContextPath()%>/user/toLeft?token=${token}" name="left">
        <frame src="<%=request.getContextPath()%>/user/toRight?token=${token}" name="right">
    </frameset>
</frameset>

</html>
