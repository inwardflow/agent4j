package net.heimeng.modules.ai.factory;

import net.heimeng.common.core.util.CacheUtils;
import net.heimeng.common.core.util.JsonUtils;
import net.heimeng.modules.ai.config.AiProperties;
import net.heimeng.modules.ai.constant.AiConstant;
import net.heimeng.modules.ai.exception.AiException;
import net.heimeng.modules.ai.model.ChatModelDecorator;
import net.heimeng.modules.ai.strategy.AiStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 大语言模型工厂类
 *
 * @author InwardFlow
 */

@Slf4j
@Component
public class AiFactory {
    private static final Map<String, ChatModelDecorator> MODEL_CACHE = new ConcurrentHashMap<>();

    @Bean(name = "DefaultChatModel")
    private ChatModel defaultChatModel() {
        return getInstance();
    }

    @Bean(name = "ColdChatClient")
    private ChatClient coldChatClient() {
        return ChatClient.builder(defaultChatModel())
                .defaultOptions(ChatOptionsBuilder.builder()
                .withTemperature(0F)
                .build()
        ).build();
    }

    /**
     * 获取默认实例
     *
     * @return 默认实例
     */
    public static synchronized ChatModel getInstance() {
        // TODO: 返回默认模型
        return getInstance("zhipu");
    }

    /**
     * 根据类型获取实例
     *
     * @param configKey 配置key
     * @return 指定实例
     */
    public static synchronized ChatModel getInstance(String configKey) {

        // 从 Redis 缓存中获取配置信息
        String json = CacheUtils.get(AiConstant.CONFIG_PREFIX, configKey);
        if (json == null) {
            // 如果找不到, 则抛出异常
            throw new AiException("404", "系统异常, '" + configKey + "' 配置信息不存在!");
        }
        AiProperties properties = JsonUtils.parseObject(json, AiProperties.class);

        // 尝试从缓存中获取模型
        ChatModelDecorator modelDecorator = MODEL_CACHE.get(configKey);
        if (modelDecorator == null) {
            // 如果模型不在缓存中, 则根据配置信息, 创建新模型并放入缓存
            MODEL_CACHE.put(configKey, AiStrategy.create(configKey, json));
            log.info("创建 AiModel 实例 key => {}", configKey);
            return MODEL_CACHE.get(configKey).getChatModel();
        }

        // TODO: 配置不相同则重新构建
        if (!modelDecorator.propertiesEqual(properties)) {
            MODEL_CACHE.put(configKey, AiStrategy.create(configKey, json));
            log.info("重载 AiModel 实例 key => {}", configKey);
            return MODEL_CACHE.get(configKey).getChatModel();
        }
        return modelDecorator.getChatModel();
    }

}
