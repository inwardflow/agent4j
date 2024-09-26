package net.heimeng.common.core.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信小程序登录对象
 *
 * @author InwardFlow
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class WxLoginBody extends LoginBody {

    /**
     * 小程序 code
     */
    @NotBlank(message = "{wx.code.not.blank}")
    private String wxCode;

}
