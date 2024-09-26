package net.heimeng.web.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import net.heimeng.web.domain.User;
import net.heimeng.web.domain.bo.UserBO;
import net.heimeng.web.domain.vo.UserVO;
import net.heimeng.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户个人信息业务层处理
 *
 * @author InwardFlow
 */

@Service
public class UserProfileService {
    @Autowired
    private UserMapper userMapper;

    public UserVO getUserProfile(Long userId) {
        return userMapper.selectVoById(userId);
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 影响的行数
     */
    public int updateUserProfile(UserBO user) {
        return userMapper.update(null,
                new LambdaUpdateWrapper<User>()
                        .set(ObjectUtil.isNotNull(user.getNickName()), User::getNickName, user.getNickName())
                        .set(ObjectUtil.isNotNull(user.getPhoneNumber()), User::getPhoneNumber, user.getPhoneNumber())
                        .set(ObjectUtil.isNotEmpty(user.getSex()), User::getSex, user.getSex())
                        .eq(User::getUserId, user.getUserId()));
    }
}
