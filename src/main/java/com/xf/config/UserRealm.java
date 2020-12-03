package com.xf.config;

import com.xf.dao.AccountMapper;
import com.xf.pojo.Account;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    AccountMapper accountMapper;
    @Override
    //授权操作authz
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    //认证操作Authc
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //将参数里的authenticationToken转换为UsernamePasswordToken
        //并赋值给token
        System.out.println("执行认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从数据库找这个账户信息
        Account account = accountMapper.LoginSelect(token.getUsername());

        //如果token中存的username在数据库中找不到,返回空
        if (account==null){
            return null;
        }

        //找到了username就进行认证
        return new SimpleAuthenticationInfo("",account.getPassword(),"");
    }
}
