package net.heimeng.common.wxapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信小程序 code2Session 实体类
 *
 * @see <a href="https://developers.weixin.qq.com/minigame/dev/api-backend/open-api/login/auth.code2Session.html">登录凭证校验</a>
 * @author InwardFlow
 */


@Data
@EqualsAndHashCode(callSuper = true)
public class Code2Session extends WxBaseEntity {

    /**
     * 用户唯一标识
     */
    @NotBlank(message = "openId 不能为空")
    @JsonProperty("openid")
    private String openId;

    /**
     * 会话密钥
     */
    @JsonProperty("session_key")
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符
     * <p>若当前小程序已绑定到微信开放平台账号下会返回</p>
     */
    @JsonProperty("unionid")
    private String unionId;

}
