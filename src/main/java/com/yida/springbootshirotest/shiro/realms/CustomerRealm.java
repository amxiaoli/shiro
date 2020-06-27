package com.yida.springbootshirotest.shiro.realms;

import com.yida.springbootshirotest.entity.Perms;
import com.yida.springbootshirotest.entity.Role;
import com.yida.springbootshirotest.entity.User;
import com.yida.springbootshirotest.service.UserService;
import com.yida.springbootshirotest.util.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 自定义realm
 *
 * @author 谦让的小哪吒
 * @version 1.0
 * @date 2020/6/1
 */

public class CustomerRealm extends AuthorizingRealm {
    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("================授权================");
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证: " + primaryPrincipal);

        //根据主身份信息获取角色 和 权限信息
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findRolesByUserName(primaryPrincipal);

        //授权角色信息
        List<Role> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getName());
                //  获取权限信息
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)) {
                    for (Perms perm : perms) {
                        System.out.println(perm);
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    }
                }
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("================认证================");
        //根据token获去身份信息
        String principal = (String) authenticationToken.getPrincipal();
        System.out.println(principal);
        //在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.selectByName(principal);
        System.out.println(user);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }
}
