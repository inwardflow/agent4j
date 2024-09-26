package net.heimeng.common.core.domain.model;

import net.heimeng.common.core.constant.GrantType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户登录对象
 *
 * @author InwardFlow
 */

@Data
public class LoginBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 授权类型 (小程序, Web 端等)
     */
    @NotNull(message = "{auth.grant.type.not.null}")
    private GrantType grantType;

    /**
     * 用户授权令牌
     */
    private String token;

}
