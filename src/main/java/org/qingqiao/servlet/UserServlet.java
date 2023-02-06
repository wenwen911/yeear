package org.qingqiao.servlet;

import org.qingqiao.bean.User;
import org.qingqiao.dao.Userdao;
import org.qingqiao.jdbc.FenYe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dao")
public class UserServlet extends HttpServlet {
    Userdao userdao = new Userdao();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.service(request, response);
        String m = request.getParameter("m");
        if ("list".equals(m)){
            list(request, response);
        }else if ("queryByid".equals(m)){
            queryByid(request, response);
        }else if ("update".equals(m)){
            update(request, response);
        }else if ("insert".equals(m)){
            add(request, response);
        }else if ("delete".equals(m)){
           delete(request, response);
        }else if ("login".equals(m)){
            try {
                login(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userdao.login(username, password);
        HttpSession session = request.getSession();
        //用来判断user是不是空值,不为空则表示登录成功
        if (user != null) {
            //先创建cookie
            Cookie username_cookie = new Cookie("username",username);
            Cookie password_cookie = new Cookie("password",password);
            //设置一个cookie时间:秒,分,时,天
            username_cookie.setMaxAge(60*60*24*7);
            password_cookie.setMaxAge(60*60*24*7);
            //响应cookie
            response.addCookie(username_cookie);
            response.addCookie(password_cookie);

            session.setAttribute("q", user);
            session.setAttribute("w", "");
            response.sendRedirect("dao?m=list");
        } else {
            session.setAttribute("w", "用户名或密码错误");
            response.sendRedirect("index.jsp");
        }
    }


    private void delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int i = userdao.delete(id);
        if (i>0){
            try {
                response.sendRedirect("dao?m=list");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        User user = new User(null, username, password, address);
        int i = userdao.add(user);
        if (i>0){
            try {
                response.sendRedirect("dao?m=list");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        User user = new User(id, username, password, address);
        int i = userdao.update(user);
        if (i>0){
            try {
                response.sendRedirect("dao?m=list");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void queryByid(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userdao.quertByid(id);
        //
        request.setAttribute("q",user);
        try {
            request.getRequestDispatcher("update.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) {


        //获取模糊查询的关键字
        String mohu = request.getParameter("mohu");
        //看看你的模糊查询是否在查询内容
        mohu = mohu == null? "":mohu;

        String nowoage = request.getParameter("nowoage");
        Integer allpage = userdao.getallpage(mohu);
        FenYe fenYe = new FenYe(nowoage, allpage, 3);

        List<User> users=userdao.query(fenYe,mohu);
        request.setAttribute("list",users);
        request.setAttribute("aaa",fenYe);

        request.setAttribute("mohu",mohu);

        try {
            request.getRequestDispatcher("list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
