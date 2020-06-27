package com.yida.springbootshirotest.service;

import com.yida.springbootshirotest.entity.Perms;
import com.yida.springbootshirotest.entity.User;

import java.util.List;

/**
 * @author 谦让的小哪吒
 * @version 1.0
 * @date 2020/6/1
 */
public interface UserService {
    void insert(User user);

    User selectByName(String name);
    //根据用户名查询所有角色
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);
}
