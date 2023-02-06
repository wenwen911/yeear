package org.qingqiao.dao;

import org.qingqiao.bean.User;
import org.qingqiao.jdbc.FenYe;
import org.qingqiao.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Userdao {
    private Connection conn = JDBCUtil.getConn();

    public List<User> query(FenYe fenYe, String mohu) {
        ArrayList<User> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from year where username like ? limit ?,?");
            ps.setString(1,"%"+mohu+"%");
            ps.setInt(2,(fenYe.getNowoage()-1)*3);
            ps.setInt(3,fenYe.getPagecount());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public User quertByid(int id){
        User a = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from year where id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                a = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return a;
    }

    public int update(User b){
        int i = 0;
        try {
            //修改:  update 传入对象名 set username=?,password=?,address=? where id=?"
            PreparedStatement ps = conn.prepareStatement("update year set username=?,password=?,address=? where id=?");
            ps.setString(1,b.getUsername());
            ps.setString(2,b.getPassword());
            ps.setString(3,b.getAddress());
            ps.setInt(4,b.getId());
            //有几个修改过的内容
            i = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    public int add(User b){
        int i = 0;
        try {
            //添加: insert into 传入对象名 values 值
            PreparedStatement ps = conn.prepareStatement("insert into year(username,password,address) values(?,?,?)");
            ps.setString(1,b.getUsername());
            ps.setString(2,b.getPassword());
            ps.setString(3,b.getAddress());
            i = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    public int delete(int id){
        int i = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("delete from year where id = ?");
            ps.setInt(1,id);
            i = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    public User login(String username,String password){
        User user = null;

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select * from year where username = ? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }


    public Integer getallpage(String mohu){
        int i = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("select count(*) from year where username like ?");
            ps.setString(1,"%"+mohu+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                i=rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }
}
