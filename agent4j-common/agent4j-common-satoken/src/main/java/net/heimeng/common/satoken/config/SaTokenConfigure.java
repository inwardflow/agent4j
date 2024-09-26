package net.heimeng.common.satoken.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SaToken 配置类
 *
 * @author InwardFlow
 */

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(SecurityProperties.class)
public class SaTokenConfigure implements WebMvcConfigurer {

    private final SecurityProperties securityProperties;

    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 指定一条 match 规则
//            SaRouter.match("/**")    // 拦截的 path 列表，可以写多个 */
//                    .notMatch("/auth/doLogin")        // 排除掉的 path 列表，可以写多个
//                    .check(r -> StpUtil.checkLogin());        // 要执行的校验动作，可以写完整的 lambda 表达式

            // 根据路由划分模块，不同模块不同鉴权
            SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
        })).addPathPatterns("/**").excludePathPatterns(securityProperties.getExcludes());
    }
}
