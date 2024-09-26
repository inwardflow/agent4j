package net.heimeng.common.core.util;

import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Map;

/**
 * HTTP 工具类
 *
 * @author InwardFlow
 */

public class HttpUtils {
    private static final RestTemplate CLIENT = new RestTemplate();

    public static <T> T postForBean(String url, Class<T> clazz, Map<String, ? extends Serializable> params) {
        return CLIENT.postForObject(url, null, clazz, params);
    }
}
