package cn.zifangsky.quickmodules.example.service.impl;

import cn.zifangsky.quickmodules.example.model.Greeting;
import cn.zifangsky.quickmodules.example.model.HelloMessage;
import cn.zifangsky.quickmodules.example.service.TestService;
import cn.zifangsky.quickmodules.log.annotation.WebLog;
import cn.zifangsky.quickmodules.log.annotation.WebLogs;
import cn.zifangsky.quickmodules.log.enums.LogTypes;
import cn.zifangsky.quickmodules.user.mapper.UserMapper;
import cn.zifangsky.quickmodules.user.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试
 *
 * @author zifangsky
 * @date 2017/12/5
 * @since 1.0.0
 */
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @WebLog(content = "请求的用户名：#{greeting.name}，年龄：#{greeting.age}，IP：#{ip}", type = LogTypes.BUSINESS, module = "测试业务接口", condition = "#{greeting.age>10}")
    public HelloMessage greeting(Greeting greeting, String ip) {
        HelloMessage message = new HelloMessage();
        if(greeting != null){
            message.setContent("Hello <" + greeting.getName() + "," + greeting.getAge() + ">,Welcome!");
        }

        return message;
    }

    @Override
    @WebLogs({
            @WebLog(content = "查询用户名【#{username}】的基本信息", type = LogTypes.BUSINESS, module = "用户模块")
            ,@WebLog(content = "执行 selectByUsername 方法", type = LogTypes.OPERATION)
    })
    public SysUser selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
