package net.heimeng.web.domain.vo;

import net.heimeng.web.domain.User;
import io.github.linpeilie.annotations.AutoMapper;

import java.time.OffsetDateTime;

/**
 * 用户信息视图对象
 *
 * @author InwardFlow
 */
@AutoMapper(target = User.class)
public class UserVO {
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 创建时间
     */
    private OffsetDateTime createTime;
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
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }

    public OffsetDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(OffsetDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
