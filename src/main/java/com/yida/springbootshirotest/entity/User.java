package com.yida.springbootshirotest.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Table(name = "s_user")
public class User implements Serializable {
    @Id //指定主键
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")//针对mysql,获取插入后,主键的值
    private String  id;
    private String name;
    private String password;
    private String salt;
    //定义角色集合
    @Transient
    private List<Role> roles;

    public User() {
    }

    public User(String name, String password, String salt, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", roles=" + roles +
                '}';
    }
}
