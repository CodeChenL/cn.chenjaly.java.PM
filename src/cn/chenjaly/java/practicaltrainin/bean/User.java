package cn.chenjaly.java.practicaltrainin.bean;

public class User {
    static Integer id;
    static String loginname;
    static String password;
    static Integer status;
    static String createdate;
    static String username;

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        User.id = id;
    }

    public static String getLoginname() {
        return loginname;
    }

    public static void setLoginname(String loginname) {
        User.loginname = loginname;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static Integer getStatus() {
        return status;
    }

    public static void setStatus(Integer status) {
        User.status = status;
    }

    public static String getCreatedate() {
        return createdate;
    }

    public static void setCreatedate(String createdate) {
        User.createdate = createdate;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public User(String loginname,String username,Integer status) {
        User.loginname = loginname;
        User.username = username;
        User.status = status;
    }
    public User(String loginname,String password,Integer status,String username) {
        User.loginname = loginname;
        User.username = username;
        User.status = status;
    }
    public User(Integer id,String loginname,String password,String username,Integer status) {
        User.loginname = loginname;
        User.username = username;
        User.status = status;
        User.id=id;
    }

    public User() {
    }
}
