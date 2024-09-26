package net.heimeng.common.satoken.service;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import java.util.Collections;
import java.util.List;

/**
 * Sa-Token 权限管理实现类
 *
 * @author InwardFlow
 */

@AutoConfiguration
public class SaPermissionImpl implements StpInterface {

    // 添加权限列表
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        if (StpUtil.isLogin(loginId)) {
            return Collections.singletonList("user");
        } else {
            return null;
        }
    }

    // 添加角色列表
    @Override
    public List<String> getRoleList(Object s, String loginType) {
        return null;
    }
}
