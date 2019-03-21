package cn.zifangsky.quickmodules.example.service;

import cn.zifangsky.quickmodules.example.model.Greeting;
import cn.zifangsky.quickmodules.example.model.HelloMessage;
import cn.zifangsky.quickmodules.user.model.SysUser;

/**
 * 测试
 *
 * @author zifangsky
 * @date 2017/12/5
 * @since 1.0.0
 */
public interface TestService {

    HelloMessage greeting(Greeting greeting, String ip);

    SysUser selectByUsername(String username);
}
