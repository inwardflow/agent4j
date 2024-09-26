package net.heimeng.common.wxapi.constant;

/**
 * 微信小程序 API URL 常量类
 *
 * @author InwardFlow
 */

public enum WxApiUrlEnum {

    /**
     * code2Session
     */
    CODE_2_SESSION("https://api.weixin.qq.com/sns/jscode2session"),

    /**
     * 获取手机号
     */
    GET_PHONE_NUMBER("https://api.weixin.qq.com/wxa/business/getuserphonenumber");

    private final String url;

    WxApiUrlEnum(String url) {
        this.url = url;
    }

    public String build(String params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        // 确保 URL 参数以 "?" 开头，并且与现有参数正确连接
        if (!url.contains("?")) {
            return url + "?" + params;
        } else {
            return url + "&" + params;
        }
    }

    public String build() {
        return url;
    }
}
