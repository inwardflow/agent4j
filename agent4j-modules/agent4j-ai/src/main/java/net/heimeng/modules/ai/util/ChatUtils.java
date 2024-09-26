package net.heimeng.modules.ai.util;

import net.heimeng.common.core.util.SpringUtils;
import org.springframework.ai.chat.client.ChatClient;

/**
 * 大语言模型聊天工具类
 *
 * @author InwardFlow
 */
public class ChatUtils {

    private final static ChatClient CLIENT = SpringUtils.getBean(ChatClient.class);

    public static String generate(String text) {
        return CLIENT.prompt().user(text).call().content();
    }
}
