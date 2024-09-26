package net.heimeng.common.wxapi.task;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import net.heimeng.common.core.util.ValidatorUtils;
import net.heimeng.common.wxapi.model.WxAccessToken;
import net.heimeng.common.wxapi.config.WxConfig;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序定时任务类
 *
 * @author InwardFlow
 */

@Slf4j
@Component
@EnableScheduling
@ConditionalOnProperty(name = "wx.enable", havingValue = "true")
public class WxTask {

    private final WxConfig wxConfig;

    public WxTask(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    /**
     * 定时任务：获取接口调用凭据
     *
     * <p>首次运行延迟 5 秒钟，2 小时重新获取一次</p>
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/mp-access-token/getAccessToken.html">微信官方开发者文档</a>
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 7200 * 1000)
    public void getAccessTokenTask() {
        log.info("正在获取 wxAccessToken, 当前时间: {}", OffsetDateTime.now());
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";

        // 构建表单
        Map<String, Object> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", wxConfig.getAppId());
        requestUrlParam.put("secret", wxConfig.getAppSecret());
        requestUrlParam.put("grant_type", "client_credential");

        // 发起请求
        String body = HttpRequest.post(requestUrl)
                .header("Content-Type", "multipart/form-data")
                .form(requestUrlParam)
                .timeout(5000)
                .execute()
                .body();

        WxAccessToken wxAccessToken;
        // 校验与处理响应结果
        try {
            wxAccessToken = ValidatorUtils.validate(JSONUtil.toBean(body, WxAccessToken.class));
            wxConfig.setAccessToken(wxAccessToken.getAccessToken());
            log.info("刷新 wxAccessToken 成功! 内容为: {}", wxConfig.getAccessToken());
        } catch (ConstraintViolationException e) {
            log.error("获取 wxAccessToken 失败! 原因: {}, 响应内容为: {}", e.getMessage(), body);
        }
    }
}
