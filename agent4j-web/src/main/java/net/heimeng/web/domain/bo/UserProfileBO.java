package net.heimeng.web.domain.bo;

import lombok.Data;

/**
 * 用户信息业务层实体类
 *
 * @author InwardFlow
 */

@Data
public class UserProfileBO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

}
