package net.heimeng.web.controller;

import cn.hutool.json.JSONUtil;
import net.heimeng.common.core.constant.GrantType;
import net.heimeng.common.core.domain.R;
import net.heimeng.common.core.domain.model.LoginBody;
import net.heimeng.common.core.util.ValidatorUtils;
import net.heimeng.web.domain.vo.LoginVO;
import net.heimeng.web.service.IAuthStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 登录认证
 *
 * @author InwardFlow
 */

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * 登录认证接口
     * @param body 响应主体
     * @return 登录验证信息
     */
    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody String body) {
        // 反序列化 LoginBody
        LoginBody loginBody = ValidatorUtils.validate(JSONUtil.toBean(body, LoginBody.class));
        try {
            GrantType grantType = Objects.requireNonNull(loginBody.getGrantType());
            return R.ok(IAuthStrategy.login(body, grantType));
        } catch (NullPointerException e) {
            log.info("授权错误: 不存在该认证类型");
            return R.fail("不存在该认证类型");
        }
    }
}
