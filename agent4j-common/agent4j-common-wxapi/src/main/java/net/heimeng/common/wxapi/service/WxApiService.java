package net.heimeng.common.wxapi.service;

import net.heimeng.common.core.util.HttpUtils;
import net.heimeng.common.wxapi.config.WxConfig;
import net.heimeng.common.wxapi.constant.WxApiUrlEnum;
import net.heimeng.common.wxapi.model.Code2Session;
import net.heimeng.common.wxapi.model.GetPhoneNumber;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序 api 服务
 *
 * @author InwardFlow
 */

@Service
@Validated
public class WxApiService {

    private final WxConfig wxConfig;

    public WxApiService(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    /**
     * 获取 openId 用户唯一标识
     *
     * @param wxCode 小程序调用 wx.login 返回的 code
     * @return JSON 字符串
     */
    public @Valid Code2Session code2Session(String wxCode) {
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", wxConfig.getAppId());
        requestUrlParam.put("secret", wxConfig.getAppSecret());
        requestUrlParam.put("js_code", wxCode);
        requestUrlParam.put("grant_type", "authorization_code");
        return HttpUtils.postForBean(WxApiUrlEnum.CODE_2_SESSION.build(), Code2Session.class, requestUrlParam);
    }

    /**
     * 获取用户手机号
     * @param wxCode 小程序调用 wx.login 返回的 code
     * @return 手机号
     */
    public @Valid GetPhoneNumber code2PhoneNumber(String wxCode) {
        String requestUrl = WxApiUrlEnum.GET_PHONE_NUMBER.build("access_token=" + wxConfig.getAccessToken()) ;
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("code", wxCode);
        return HttpUtils.postForBean(requestUrl, GetPhoneNumber.class, requestUrlParam);
    }

}
