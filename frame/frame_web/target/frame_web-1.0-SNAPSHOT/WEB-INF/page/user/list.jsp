<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2019/1/21
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/res/layui-v2.4.5/layui/css/layui.css"  media="all">
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/layer/layer.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/layui-v2.4.5/layui/layui.js"></script>
</head>
<body>
<br>
<center>
    <form id = "fmt">
        <input type="hidden" name="pageNo" id = "pageNo" value="1" />
        根据名称查询<input type="text" name="username"  /><br>
        手机邮箱<input type="text" name="phoneAndEmail"  /><br>
        性别查询	  男： <input type="radio" name="sex" value="1"/>
        女：  <input type="radio" name="sex" value="2" /><br>
        <input type="button" value="search" onclick="search(page(1))" />
        <input type="reset" value="reset" onclick="search(page(1))" />
        <input type="text" id = "token" value="${token}">
    </form><br>
    <form id = "fm">
        <input type="hidden" name="_method" value="DELETE"/>
    </form>
</center>

<div align="right" style="border: 1px; width:800px ; height:600px; margin-left: 50px">
    <h2 align="center">用户管理展示</h2> <br>
    <div id = "div" align="center"></div>
    <br>
    <table border="1px" align="center" cellspacing="0px">
        <tr >
            <th>id</th>
            <th>用户名</th>
            <th>密码</th>
            <th>生日</th>
            <th>性别</th>
            <th>身份证</th>
            <th>邮箱</th>
            <th>手机号</th>
            <th>操作</th>
        </tr>
        <tbody id = "tbody"></tbody>
    </table>
    ${msg} <br/>
    <div align="center" id = "pagediv"></div>
</div>

</body>

<script type="text/javascript">
    $(function () {
        $.ajaxSetup({
            layerIndex:-1,
            beforeSend: function () { //插件加载前
                this.layerIndex = layer.load(0, { shade: [0.5, '#fff'] });
                $('#submit').val("正在登录")
            },
            complete: function () { //完成加载后执行
                layer.close(this.layerIndex); //完成加载后关闭loading
            },
            error: function () { //报错时执行
                layer.alert('显示异常，请刷新后重试', {
                    skin: 'layui-layer-molv'
                    , closeBtn: 0
                    , shift: 4 //动画类型
                });
            }
        });
    });


    $(function () {
        search();
    });

    function search() {
        $.post(
            "<%=request.getContextPath()%>/user/list?token=${token}",
            $("#fmt").serialize(),
            function (data) {
                var html = "";
                for (var i = 0; i < data.data.list.length; i++) {
                    var user = data.data.list[i];

                    html += "<tr>";
                    html += "<td>"+user.id+"</td>";
                    html += "<td>"+user.username+"</td>";
                    html += "<td>"+user.password+"</td>";
                    html += "<td>"+user.birth+"</td>";
                    if(user.sex == 1){
                        html += "<td>男</td>";
                    } else {
                        html += "<td>女</td>";
                    }
                    html += "<td>"+user.idCard+"</td>";
                    html += "<td>"+user.email+"</td>";
                    html += "<td>"+user.phone+"</td>";
                    html += "<td>";
                    html += "<input type='button' value='删除' onclick='deleteUser("+user.id+")'/>";
                    html += "<input type='button' value='修改' onclick='updateUser("+user.id+")'/>";
                    html += "</td>";
                    html += "</tr>";

                }
                $("#tbody").html(html);

                var divhtml = "";
                divhtml += "<input type='button' value='添加用户' onclick='add()'/>";
                $("#div").html(divhtml);

                var pagehtml = "";
                pagehtml += "当前"+(data.data.pageNo)+"页";
                pagehtml += "<input type='button' value='上一页' onclick='page("+(eval(data.data.pageNo)-1)+" , "+data.data.totalPage+"  )' />";
                pagehtml += "<input type='button' value='下一页' onclick='page("+(eval(data.data.pageNo)+1)+" , "+data.data.totalPage+" )' />";
                pagehtml += "总"+(data.data.totalPage)+"页";
                $("#pagediv").html(pagehtml);
            }
        );}

    function page(pageNo,totalPage) {
        $("#pageNo").val(pageNo);
        if(pageNo < 1){
            layer.msg('已经是第一页了', {time: 500, icon:6});
            return;
        }
        if(pageNo > totalPage ){
            layer.msg('已经是第一页了', {time: 500, icon:5});
            return;
        }
        search();
    }


    /*		全选反选											*/
    /* function selectQuanOrFan(){
        var productBox = document.getElementsByName("idcheck");
        var checkBox = document.getElementById("checkAll");
             for(var i = 0; i < productBox.length; i++){
                if(productBox[i].checked == true){
                    if(checkBox.checked == true){
                        productBox[i].checked = false;
                    }else{
                        productBox[i].checked = true;
                    }
                }else{
                    productBox[i].checked = checkBox.checked;
                }
        }
    } */

    function reset() {
        search();
    }



    function add(){
        layer.open({
            type: 2,
            title: '添加',
            shadeClose: true,
            shade: 0.8,
            area: ['380px', '90%'],
            content:'<%=request.getContextPath()%>/user/toAdd?token=${token}',
            end: function(){
                location.reload();
            }
        });
    }


    /*  */
    function updateUser(id){
        layer.open({
            type: 2,
            area: ['400px', '400px'],
            maxmin:true,
            content: ['<%=request.getContextPath()%>/user/toUpdate?token=${token}'+"&id="+id, 'no'],
            end: function(){
                location.reload();
            }
        });
    }


    function deleteUser(id){
        layer.confirm('确认删除？', {
            btn: ['确认', '取消'],
            btn1: function(index, layero){
                var index1 = layer.load();
                var token = $("#token").val();
                $.post(
                    "<%=request.getContextPath()%>/user/del?token="+token+"&id="+id,
                    $("#fm").serialize()
                    ,function(data){
                        if(data.code == 200){
                            layer.msg('删除成功', {
                                icon: 1,shade: 0.01,time: 500
                            }, function(){
                                /*  var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);   */   //关闭展示框

                                window.location.href = "<%=request.getContextPath()%>/user/toList?token="+data.data.token;
                            });
                        } else {
                            layer.close(index1);
                            layer.msg(data.msg,{icon: 5,time:500});
                        }
                    }
                )
            },
            btn2:function(index, layero){
                return;
            }
        })
    }



    /* 管理角色 */
    function userRole(userId){
        layer.open({
            type: 2,
            area: ['400px', '400px'],
            maxmin:true,
            content: ['<%=request.getContextPath()%>/user/userRoleList/'+userId, 'no'],
            end: function(){
                location.reload();
            }
        });
    }




    /* 管理角色  树*/
    function userRoleTree(userId){
        layer.open({
            type: 2,
            area: ['400px', '400px'],
            maxmin:true,
            content: ['<%=request.getContextPath()%>/user/userRoleListTree/'+userId, 'no'],
            end: function(){
                location.reload();
            }
        });
    }


</script>
</html>
