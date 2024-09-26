package net.heimeng.web.service;

import net.heimeng.common.core.util.CacheUtils;
import net.heimeng.common.core.util.JsonUtils;
import net.heimeng.common.jedis.util.JedisUtils;
import net.heimeng.modules.ai.constant.AiConstant;
import net.heimeng.web.domain.SysLlmConfig;
import net.heimeng.web.mapper.SysLlmConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模型配置Service接口
 *
 * @author InwardFlow
 */
@Service
@RequiredArgsConstructor
public class SysLlmConfigService {

    private final SysLlmConfigMapper baseMapper;

    /**
     * 初始化模型配置
     */
    public void init() {
        // 项目启动时，初始化参数到缓存，加载配置类
        List<SysLlmConfig> list = baseMapper.selectList();
        // 加载OSS初始化配置
        for (SysLlmConfig config : list) {
            String configKey = config.getConfigKey();
            // TODO: is default
            if ("0".equals(config.getStatus())) {
                JedisUtils.set(AiConstant.DEFAULT_CONFIG_KEY, configKey);
            }
            CacheUtils.put(AiConstant.CONFIG_PREFIX, config.getConfigKey(), JsonUtils.toJsonString(config));
        }
    }
}
