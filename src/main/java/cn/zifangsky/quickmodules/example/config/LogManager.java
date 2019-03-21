package cn.zifangsky.quickmodules.example.config;

import cn.zifangsky.quickmodules.common.common.SpringContextUtils;
import cn.zifangsky.quickmodules.log.model.UserInfo;
import cn.zifangsky.quickmodules.log.plugins.AbstractLogManager;
import cn.zifangsky.quickmodules.user.common.Constants;
import cn.zifangsky.quickmodules.user.model.SysUser;

import javax.servlet.http.HttpSession;

/**
 * 日志相关
 *
 * @author zifangsky
 * @date 2017/12/5
 * @since 1.0.0
 */
public class LogManager extends AbstractLogManager {

    @Override
    public UserInfo getUserInfo() {
        HttpSession session = SpringContextUtils.getSession();
        SysUser user = (SysUser) session.getAttribute(Constants.SESSION_USER);

        if(user != null){
            return new UserInfo(user.getId(), user.getName());
        }

        return null;
    }
}
