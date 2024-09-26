package net.heimeng.web.domain.vo;

/**
 * 登录验证信息
 *
 * @author InwardFlow
 */

public class LoginVO {

    /**
     * 用户认证令牌
     */
    private String token;

    /**
     * 用户 openId
     */
    private String openId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
