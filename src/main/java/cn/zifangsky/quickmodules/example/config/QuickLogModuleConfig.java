package cn.zifangsky.quickmodules.example.config;

import cn.zifangsky.quickmodules.log.annotation.EnableWebLog;
import cn.zifangsky.quickmodules.log.plugins.AbstractLogManager;
import cn.zifangsky.quickmodules.log.plugins.WebLogInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试使用quick-log模块
 * @author zifangsky
 * @date 2017/12/5
 * @since 1.0.0
 */
@Configuration
@EnableWebLog
public class QuickLogModuleConfig {

    @Bean
    public WebLogInfo webLogInfo(){
        WebLogInfo.Builder builder = new WebLogInfo.Builder();
        AbstractLogManager logManager = new LogManager();

        return builder
                //启动日志缓存
                .enableCache(true)
                //自定义LogManager
                .logManager(logManager)
                .build();
    }

}
