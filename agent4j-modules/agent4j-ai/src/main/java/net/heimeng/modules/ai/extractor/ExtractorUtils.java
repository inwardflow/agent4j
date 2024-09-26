package net.heimeng.modules.ai.extractor;

import net.heimeng.common.core.util.SpringUtils;
import net.heimeng.modules.ai.model.WithDescription;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 提取器工具类
 *
 * @author InwardFlow
 */
public class ExtractorUtils {

    private static final ChatClient CLIENT = SpringUtils.getBean(ChatClient.class);
    public static <T extends WithDescription> T extractWithDescription(String text, Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            return CLIENT.prompt()
                    .user(u -> u.text("""
                                    Extract information from:
                                     {text}
                                     ---
                                     Here is the description:
                                     {description}
                                """)
                            .params(Map.of("text", text, "description", instance.getProperties())))
                    .call()
                    .entity(new ParameterizedTypeReference<>() {});
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Unable to create an instance of " + clazz.getName(), e);
        }
    }

    public static <T> T extract(String text, Class<T> clazz) {
        String className = clazz.getSimpleName();
        return CLIENT.prompt()
                .user(u -> u.text("""
                                    Extract information about {className} from {text}
                                """)
                        .params(Map.of("className", className, "text", text)))
                .call()
                .entity(clazz);
    }

    public static <T> T extract(String text, ParameterizedTypeReference<T> typeReference) {
        return CLIENT.prompt()
                .user(u -> u.text("Extract information from: " + text))
                .call()
                .entity(typeReference);
    }

}
