<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/1/29
  Time: 下午 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="dao?m=update" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td>
                <input type="hidden" name="id" value="${q.id}">
                <input type="text" name="username" value="${q.username}">
            </td>
        </tr>
        <tr>
            <td>密码:</td>
            <td>
                <input type="password" name="password" value="${q.password}">
            </td>
        </tr>
        <tr>
            <td>住址:</td>
            <td>
                <input type="text" name="address" value="${q.address}">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="修改">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
