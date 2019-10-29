<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2019/1/21
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/res/zTree_v3/css/demo.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/res/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/zTree_v3/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/zTree_v3/js/jquery.ztree.core.js"></script>
    <script type="text/javascript">

        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    pIdKey: "pid",
                },
                key: {
                    name: "resourceName",
                    url: "resourceUrl"            //urlztree规定的值  给个resourceUrl 不存在的值
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    if(!treeNode.isParent){                   //不是父节点不跳转路径  right targht="right"
                        parent.right.location.href = "<%=request.getContextPath()%>"+treeNode.url;
                    }
                }
            }
        };




        //ready事件
        $(function(){
            //加载数据
            $.post(
                "<%=request.getContextPath()%>/user/menu",
                {},
                function (data){
                    $.fn.zTree.init($("#treeDemo"), setting, data.data);
                }

            );

        });

    </script>
</head>
<body bgcolor="#FFECF5" align="center">

<div id="treeDemo" class="ztree" ></div>

<hr>
<ul>
    <c:forEach var="resource" items="${resourceList}">
        <li><a target="right" href="<%=request.getContextPath()%>${resource.url}">${resource.resourceName}</a></li>
    </c:forEach>

</ul>


<a target="right" href="<%=request.getContextPath()%>/user/toList?token=${token}" style="color: red">用户管理</a> <br>

</body>
</html>
