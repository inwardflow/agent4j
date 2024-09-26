package net.heimeng.web.mapper;

import net.heimeng.web.domain.User;
import net.heimeng.web.domain.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户Mapper接口
 *
 * @author InwardFlow
 */
public interface UserMapper extends BaseMapperPlus<User, UserVO> {
    @Select("SELECT * FROM sys_user WHERE open_id = #{openId}")
    UserVO selectUserByOpenId(@Param("openId") String openId);
}
