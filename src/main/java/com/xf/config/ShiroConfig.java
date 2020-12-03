package com.xf.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //shirofilterfactorybean
    @Bean
    public ShiroFilterFactoryBean getBean(@Qualifier("getSecurityManager")DefaultWebSecurityManager securityManager){
        //还是和下面一样,先新建,再关联
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/rooms","authc");
        filterMap.put("/live_out/**","authc");
        filterMap.put("/update","authc");
        filterMap.put("/bookrooms","authc");
        filterMap.put("/toAdd","authc");
        filterMap.put("/toUpdate/**","authc");
        filterMap.put("/live_in/**","authc");
        filterMap.put("/orders/**","authc");
        filterMap.put("/booknow","authc");
//        filterMap.put("/**","authc");
        filterMap.put("/admin","authc");

        bean.setFilterChainDefinitionMap(filterMap);

        bean.setLoginUrl("/toLogin");

        return bean;
    }

    //securitymanager
    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        //新建一个securityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
