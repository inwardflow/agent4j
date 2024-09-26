package net.heimeng.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import net.heimeng.web.domain.User;
import net.heimeng.web.domain.vo.UserVO;
import net.heimeng.web.mapper.UserMapper;
import net.heimeng.web.service.IUserService;
import net.heimeng.common.core.util.StringUtils;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * 用户业务层处理
 *
 * @author InwardFlow
 */
@Service
public class UserServiceImpl implements IUserService {
    public final UserMapper mapper;

    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserVO selectUserById(Long userId) {
        return mapper.selectVoById(userId);
    }

    @Override
    public UserVO selectUserByOpenId(@NonNull String openId) {
        // 如果传入的 openId 为空则报错，或者返回 null
        return StringUtils.isBlank(openId) ? null : mapper.selectUserByOpenId(openId);
    }

    private LambdaQueryWrapper<User> buildQueryWrapper(User bo) {
        LambdaQueryWrapper<User> lqw = Wrappers.lambdaQuery();
        lqw.eq(ObjectUtil.isNotNull(bo.getUserId()), User::getOpenId, bo.getOpenId());
        lqw.eq(StringUtils.isNotBlank(bo.getOpenId()), User::getOpenId, bo.getOpenId());
        lqw.eq(StringUtils.isNotBlank(bo.getAvatar()), User::getAvatar, bo.getAvatar());
        lqw.eq(StringUtils.isNotBlank(bo.getSex()), User::getSex, bo.getSex());
        lqw.like(StringUtils.isNotBlank(bo.getNickName()), User::getNickName, bo.getNickName());
        lqw.eq(StringUtils.isNotBlank(bo.getPhoneNumber()), User::getPhoneNumber, bo.getPhoneNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), User::getStatus, bo.getStatus());
        lqw.orderByAsc(User::getUserId);
        return lqw;
    }
}
