package com.yida.springbootshirotest.service.impl;

import com.yida.springbootshirotest.entity.Perms;
import com.yida.springbootshirotest.entity.User;
import com.yida.springbootshirotest.mapper.UserMapper;
import com.yida.springbootshirotest.service.UserService;
import com.yida.springbootshirotest.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 谦让的小哪吒
 * @version 1.0
 * @date 2020/6/1
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        // 生成随机盐
        String salt = SaltUtils.getSalt(6);
        System.out.println("yan:===" + salt);
        // 把随机盐保存到数据库
        user.setSalt(salt);
        //user.setSalt("rdI(XV");
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        //Md5Hash md5Hash = new Md5Hash(user.getPassword(), "rdI(XV", 1024);
        user.setPassword(md5Hash.toHex());
        userMapper.insertSelective(user);
        System.out.println("添加成功");
    }

    @Override
    public User selectByName(String name) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public User findRolesByUserName(String username) {
        return userMapper.findRolesByUserName(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return userMapper.findPermsByRoleId(id);
    }
}
