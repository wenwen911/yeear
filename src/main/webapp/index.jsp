<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/1/29
  Time: 下午 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--    java脚本 : <%java代码%> 在前端里写java代码必须写在<%这个里面%> 他才能运行--%>
    <%--        cookie时与服务器断了他也有数据,session与服务器断了就断了,所以为了保存数据和能查到数据,在前端里写获取以便于保存的到的java_cookie方法   --%>
    <%--        用来获取所有cookies--%>
    <%--       遍历数组   --%>
    <%--        获取value值--%>
    <%--    放入作用域中--%>
    <%
        Cookie[] cookies = request.getCookies();
        String username="";
        String password="";
    if (cookies!=null){
        for(Cookie cookie:cookies){
            if ("username".equals(cookie.getName())){
                username=cookie.getValue();
            }
            if ("password".equals(cookie.getName())){
                password=cookie.getValue();
            }
        }
    }
    request.setAttribute("username",username);
    request.setAttribute("password",password);
    %>
</head>
<body>
${w}
<form action="dao?m=login" method="post">
    <input type="text" name="username"><br>
    <input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
