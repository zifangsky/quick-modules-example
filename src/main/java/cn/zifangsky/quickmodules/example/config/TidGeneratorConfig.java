package cn.zifangsky.quickmodules.example.config;

import cn.zifangsky.quickmodules.common.common.resp.TidGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置TID生成工具类
 *
 * @author zifangsky
 * @date 2020/12/7
 * @since 1.1.0
 */
@Configuration
public class TidGeneratorConfig {

    @Value("${tid.machine.id}")
    private Long tidMachineId;

    @Bean
    public TidGenerator tidGenerator(){
        return new TidGenerator(tidMachineId);
    }
}