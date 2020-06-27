package com.yida.springbootshirotest.mapper;

import com.yida.springbootshirotest.entity.Perms;
import com.yida.springbootshirotest.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 谦让的小哪吒
 * @version 1.0
 * @date 2020/6/1
 */
public interface UserMapper extends Mapper<User> {
    //根据用户名查询所有角色
    User findRolesByUserName(String name);
    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);
}
