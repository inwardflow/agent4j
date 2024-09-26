package net.heimeng.web.service;

import net.heimeng.web.domain.vo.UserVO;

/**
 * 用户 Service 接口
 *
 * @author InwardFlow
 */
public interface IUserService {

    /**
     * 通过 userId 查询用户信息
     */
    UserVO selectUserById(Long userId);

    /**
     * 通过 openId 查询用户信息
     */
    UserVO selectUserByOpenId(String openId);
}
