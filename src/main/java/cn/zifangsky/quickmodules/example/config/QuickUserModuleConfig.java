package cn.zifangsky.quickmodules.example.config;

import cn.zifangsky.easylimit.enums.EncryptionTypeEnums;
import cn.zifangsky.easylimit.filter.impl.support.DefaultFilterEnums;
import cn.zifangsky.quickmodules.user.annotations.EnableWebUser;
import cn.zifangsky.quickmodules.user.plugins.WebTokenUserInfo;
import cn.zifangsky.quickmodules.user.plugins.WebUserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * 测试使用quick-user模块
 * @author zifangsky
 * @date 2020/12/7
 * @since 1.1.0
 */
@Configuration
@EnableWebUser
public class QuickUserModuleConfig {

    /**
     * 前后端分离开发模式
     */
    @Bean
    public WebTokenUserInfo webTokenUserInfo(){
        //添加指定路径的权限校验
        LinkedHashMap<String, String[]> patternPathFilterMap = this.patternPathFilterMap();

        WebTokenUserInfo userInfo = (WebTokenUserInfo) new WebTokenUserInfo()
                .encryptionType(EncryptionTypeEnums.Sha256Crypt)
                .loginCheckUrl("/check")
                .unauthorizedUrl("/error/403.html")
                .logoutUrl("/logout")
                //启用登录校验验证码
                .enableLoginVerifyCode(false)
                //同一个用户只允许在一个设备登录
                .kickOutOldSessions(true)
                //开启权限注解匹配的切面表达式
                .aopExpression("execution(* cn.zifangsky..controller..*.*(..))");

        return userInfo
                .filter()
                .filterChainMap(patternPathFilterMap)
                .build();
    }

    /**
     * 传统MVC开发模式
     */
//    @Bean
    public WebUserInfo webUserInfo(){
        //添加指定路径的权限校验
        LinkedHashMap<String, String[]> patternPathFilterMap = this.patternPathFilterMap();

        WebUserInfo userInfo = new WebUserInfo()
                .encryptionType(EncryptionTypeEnums.Sha256Crypt)
                .loginCheckUrl("/check")
                .unauthorizedUrl("/error/403.html")
                .logoutUrl("/logout")
                //启用登录校验验证码
                .enableLoginVerifyCode(false)
                //同一个用户只允许在一个设备登录
                .kickOutOldSessions(true)
                //开启权限注解匹配的切面表达式
                .aopExpression("execution(* cn.zifangsky..controller..*.*(..))");

        return userInfo
                .filter()
                .filterChainMap(patternPathFilterMap)
                .build();
    }


    /**
     * 请求路径与需要的访问权限
     */
    private LinkedHashMap<String, String[]> patternPathFilterMap(){
        LinkedHashMap<String, String[]> patternPathFilterMap = new LinkedHashMap<>();
        patternPathFilterMap.put("/css/**", new String[]{DefaultFilterEnums.ANONYMOUS.getFilterName()});
        patternPathFilterMap.put("/layui/**", new String[]{DefaultFilterEnums.ANONYMOUS.getFilterName()});
        patternPathFilterMap.put("/test/greeting", new String[]{DefaultFilterEnums.ANONYMOUS.getFilterName()});
//        patternPathFilterMap.put("/test/selectByUsername", new String[]{"perms[/aaa/bbb]"});

        //其他路径需要登录才能访问
        patternPathFilterMap.put("/**", new String[]{DefaultFilterEnums.LOGIN.getFilterName()});

        return patternPathFilterMap;
    }
}
