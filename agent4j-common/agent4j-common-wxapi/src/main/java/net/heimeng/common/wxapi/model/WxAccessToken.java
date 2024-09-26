package net.heimeng.common.wxapi.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信小程序 getAccessToken 实体类
 *
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/mp-access-token/getAccessToken.html">获取接口调用凭据</a>
 * @author InwardFlow
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class WxAccessToken extends WxBaseEntity {

    /**
     * 获取到的凭证
     */
    @NotNull
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒。目前是7200秒之内的值。
     */
    @NotNull
    private Integer expiresIn;

}
