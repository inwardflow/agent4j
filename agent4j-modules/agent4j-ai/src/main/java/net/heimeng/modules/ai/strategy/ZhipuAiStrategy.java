package net.heimeng.modules.ai.strategy;

import net.heimeng.common.core.util.JsonUtils;
import net.heimeng.modules.ai.config.ZhipuAiProperties;
import net.heimeng.modules.ai.model.ChatModelDecorator;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.ai.zhipuai.ZhiPuAiChatOptions;
import org.springframework.ai.zhipuai.api.ZhiPuAiApi;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 智谱清言实现类
 *
 * @author InwardFlow
 */

@Slf4j
@Service("zhipu" + AiStrategy.BASE_NAME)
public class ZhipuAiStrategy implements AiStrategy {
    @Override
    public ChatModelDecorator create(String properties) {
        // 加载大语言模型的配置属性
        ZhipuAiProperties config = JsonUtils.parseObject(properties, ZhipuAiProperties.class);

        // 获取 baseUrl (如果有)
        String baseUrl = Optional.ofNullable(config.getBaseUrl())
                .filter(StringUtils::isNotBlank)
                .orElse(null);

        // TODO: 这里需要进行错误处理吗?
        ZhiPuAiApi api = (baseUrl != null) ? new ZhiPuAiApi(baseUrl, config.getApiKey()) :
                new ZhiPuAiApi(config.getApiKey());
        // TODO: 进一步完善自定义配置
        ZhiPuAiChatModel chatModel = new ZhiPuAiChatModel(api, ZhiPuAiChatOptions.builder()
                .withModel(config.getModelName())
                .withTemperature(config.getTemperature())
                .build());

        // 返回带属性的模型
        return new ChatModelDecorator(chatModel, config);
    }
}
