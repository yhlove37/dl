package ssm.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private int uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String telephone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String sex;
    private int state;   //是否激活
    private String code; //激活码
    private String headimage;

    public User() {
    }

    public User(int uid, String username, String password, String name, String email, String telephone, Date birthday, String sex, int state, String code, String headimage) {
        this.uid= uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.birthday = birthday;
        this.sex = sex;
        this.state = state;
        this.code = code;
        this.headimage = headimage;
    }

    public int getUid() {
        return uid;
    }

    public void setUid( int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", state=" + state +
                ", code='" + code + '\'' +
                ", headimage='" + headimage + '\'' +
                '}';
    }
}
