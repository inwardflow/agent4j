package net.heimeng.web.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.time.OffsetDateTime;

/**
 * 用户业务层实体类
 *
 * @author InwardFlow
 */

@Data
@AutoMapper(target = UserProfileBO.class)
public class UserBO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 创建时间
     */
    private OffsetDateTime createTime;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 最后登录时间
     */
    private OffsetDateTime loginDate;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 微信小程序唯一标识
     */
    private String openId;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 备注
     */
    private String remark;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;
    /**
     * 更新时间
     */
    private OffsetDateTime updateTime;
}
