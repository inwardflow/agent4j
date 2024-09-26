package net.heimeng.common.wxapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信小程序 DTO 基类
 *
 * @author InwardFlow
 */

@Data
public class WxBaseEntity implements Serializable {

    /**
     * 错误码
     */
    @JsonProperty("errcode")
    private Integer errCode;

    /**
     * 错误信息
     */
    @JsonProperty("errmsg")
    private String errMsg;

}
