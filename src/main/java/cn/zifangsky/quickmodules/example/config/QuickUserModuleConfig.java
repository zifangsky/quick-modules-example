package cn.zifangsky.quickmodules.example.config;

import cn.zifangsky.quickmodules.common.enums.EncryptionTypes;
import cn.zifangsky.quickmodules.user.annotations.EnableWebUser;
import cn.zifangsky.quickmodules.user.enums.FilterChainTypes;
import cn.zifangsky.quickmodules.user.plugins.WebUserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 测试使用quick-user模块
 * @author zifangsky
 * @date 2017/11/2
 * @since 1.0.0
 */
@Configuration
@EnableWebUser
public class QuickUserModuleConfig {
    @Bean
    public WebUserInfo webUserInfo(){
        Map<String, String> filterChainTypesMap = new LinkedHashMap<>();
        filterChainTypesMap.put("/css/**", FilterChainTypes.Anon.getCode());
        filterChainTypesMap.put("/layui/**", FilterChainTypes.Anon.getCode());
        filterChainTypesMap.put("/**", (FilterChainTypes.Kickout.getCode() + "," +FilterChainTypes.Authc.getCode()));

        WebUserInfo.Builder builder = new WebUserInfo.Builder();

        return builder.encryptionType(EncryptionTypes.Sha256Crypt)
                .loginCheckUrl("/check")
                .unauthorizedUrl("/error/403.html")
                .logoutUrl("/logout")
                //启用登录校验验证码
                .enableLoginVerifyCode(true)
                //同一个用户只允许在一个设备登录
                .deleteOldSession(true)
                //前后端分离模式
//                .separationArchitecture(true)
                .filterChainMap(filterChainTypesMap)
                //开启Shiro注解需要的AOP切面表达式
                .aopExpression("execution(* cn.zifangsky..controller..*.*(..))")
                .build();
    }


}
