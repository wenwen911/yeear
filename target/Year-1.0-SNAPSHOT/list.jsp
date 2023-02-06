<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/1/29
  Time: 上午 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script>
        function page(nowoage){
            //获取输入文本mohu中的内容
            let mohu = document.getElementById("momo").value
            location="dao?m=list&nowoage="+nowoage+"&mohu="+mohu;
        }
    </script>
</head>
<body>

<form action="dao?m=list" method="post">
<%--    将模糊查询所查询的关键字发送到list(servlet)中去  --%>
    <input type="text" name="mohu" id="momo" value="${mohu}">
    <input type="submit" value="查询">
</form>
    <table>
        <tr>
            <td>编号</td>
            <td>用户名</td>
            <td>密码</td>
            <td>地址</td>
            <td>操作 | <a href="add.jsp">添加</a></td>
        </tr>
        <c:forEach var="u" items="${list}">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.address}</td>
                <td>
                    <a href="dao?m=queryByid&id=${u.id}">修改</a>
                    <a href="dao?m=delete&id=${u.id}">删除</a>
                </td>
            </tr>

        </c:forEach>
    </table>
        <%--<a href="dao?m=list&nowoage=1">首页</a>
        <a href="dao?m=list&nowoage=${aaa.onpage}">上一页</a>
        <a href="dao?m=list&nowoage=${aaa.nextpage}">下一页</a>
        <a href="dao?m=list&nowoage=${aaa.lastpage}">尾页</a>--%>
        <input type="button" value="首页" onclick="page(1)">
        <input type="button" value="上一页" onclick="page(${aaa.onpage})">
        <input type="button" value="下一页" onclick="page(${aaa.nextpage})">
        <input type="button" value="尾页" onclick="page(${aaa.lastpage})">
</body>
</html>
