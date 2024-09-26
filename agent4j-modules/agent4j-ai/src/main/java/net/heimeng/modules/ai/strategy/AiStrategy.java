package net.heimeng.modules.ai.strategy;

import net.heimeng.common.core.util.SpringUtils;
import net.heimeng.modules.ai.model.ChatModelDecorator;

/**
 * 大语言模型逻辑
 *
 * @author InwardFlow
 */

public interface AiStrategy {

    String BASE_NAME = "AiStrategy";

    static ChatModelDecorator create(String configKey, String properties) {
        // 授权类型和客户端id
        String beanName = configKey + BASE_NAME;
        if (!SpringUtils.containsBean(beanName)) {
            throw new RuntimeException("授权类型不正确!");
        }
        AiStrategy instance = SpringUtils.getBean(beanName);
        return instance.create(properties);
    }

    ChatModelDecorator create(String properties);
}
