package net.heimeng.common.wxapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * 微信小程序 获取手机号 实体类
 *
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-info/phone-number/getPhoneNumber.html">获取手机号</a>
 * @author InwardFlow
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GetPhoneNumber extends WxBaseEntity {

    @Valid
    @NotNull(message = "phone_info 不能为 null")
    @JsonProperty("phone_info")
    private PhoneInfo phoneInfo;

    @Data
    public static class PhoneInfo {
        @NotBlank(message = "手机号不能为空")
        private String phoneNumber;
        private String purePhoneNumber;
        private String countryCode;
        private Watermark watermark;
    }

    @Data
    public static class Watermark {
        private Timestamp timestamp;

        @JsonProperty("appid")
        private String appId;
    }
}
