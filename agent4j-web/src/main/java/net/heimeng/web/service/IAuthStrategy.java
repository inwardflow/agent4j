package net.heimeng.web.service;

import net.heimeng.common.core.util.SpringUtils;
import net.heimeng.common.core.constant.GrantType;
import net.heimeng.web.domain.vo.LoginVO;

/**
 * 登录授权策略
 *
 * @author InwardFlow
 */
public interface IAuthStrategy {
    String BASE_NAME = "AuthStrategy";

    /**
     * 登录
     */
    static LoginVO login(String body, GrantType grantType) {
        // 授权类型和客户端id
        String beanName = grantType + BASE_NAME;
        if (!SpringUtils.containsBean(beanName)) {
            throw new RuntimeException("授权类型不正确!");
        }
        IAuthStrategy instance = SpringUtils.getBean(beanName);
        return instance.login(body);
    }

    /**
     * 登录
     */
    LoginVO login(String body);
}
