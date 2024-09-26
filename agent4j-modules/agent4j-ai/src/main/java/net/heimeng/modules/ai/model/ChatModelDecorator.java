package net.heimeng.modules.ai.model;

import net.heimeng.modules.ai.config.AiProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.ai.chat.model.ChatModel;

/**
 * ChatModel 装饰器接口
 * @see ChatModel
 * @author InwardFlow
 */
@Data
@AllArgsConstructor
public class ChatModelDecorator implements WithAiProperties {

    private ChatModel chatModel;
    private AiProperties properties;

    @Override
    public void setProperties(AiProperties properties) {
        this.properties = properties;
    }

    @Override
    public AiProperties getProperties() {
        return properties;
    }

    @Override
    public boolean propertiesEqual(AiProperties properties) {
        return this.properties.equals(properties);
    }
}
