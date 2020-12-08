package cn.zifangsky.quickmodules.example.controller;

import cn.zifangsky.easylimit.permission.annotation.RequiresPermissions;
import cn.zifangsky.quickmodules.example.model.Greeting;
import cn.zifangsky.quickmodules.example.model.HelloMessage;
import cn.zifangsky.quickmodules.example.service.TestService;
import cn.zifangsky.quickmodules.log.annotation.WebLog;
import cn.zifangsky.quickmodules.user.common.SpringContextUtils;
import cn.zifangsky.quickmodules.user.model.SysUser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 测试使用
 *
 * @author zifangsky
 * @date 2017/12/5
 * @since 1.0.0
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource(name = "testServiceImpl")
    private TestService testService;


    @PostMapping(value = "/greeting", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public HelloMessage greeting(@RequestBody Greeting greeting, HttpServletRequest request){
        return testService.greeting(greeting, SpringContextUtils.getRequestIp(request));
    }

    @ResponseBody
    @RequiresPermissions("/aaa/bbb")
    @RequestMapping(value = "/selectByUsername", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @WebLog(content = "在controller中执行 selectByUsername 方法，查询用户名：#{username}")
    public SysUser selectByUsername(String username) {
        return testService.selectByUsername(username);
    }

}
