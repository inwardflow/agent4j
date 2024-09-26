package net.heimeng.common.jedis.util;

import net.heimeng.common.core.util.SpringUtils;
import net.heimeng.common.jedis.service.JedisService;

/**
 * Jedis 工具类，用于封装对 Redis 的操作。
 *
 * @author InwardFlow
 */
public class JedisUtils {

    private static final JedisService SERVICE = SpringUtils.getBean(JedisService.class);

    /**
     * 获取存储在 Redis 中的值
     *
     * @param key 键
     * @return 值，如果键不存在则返回 null
     */
    public static String get(String key) {
        try {
            return SERVICE.get(key);
        } catch (Exception e) {
            // 这里可以添加日志记录和异常处理逻辑
            return null;
        }
    }

    /**
     * 设置键值对到 Redis
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, String value) {
        SERVICE.set(key, value);
    }

    /**
     * 从 Redis 中删除键
     *
     * @param key 键
     * @return 删除的键数量
     */
    public static long del(String key) {
        try {
            return SERVICE.del(key);
        } catch (Exception e) {
            // 日志记录和异常处理
            return 0;
        }
    }

    /**
     * 检查键是否存在于 Redis 中
     *
     * @param key 键
     * @return 如果键存在返回 true，否则返回 false
     */
    public static boolean exists(String key) {
        try {
            return SERVICE.exists(key);
        } catch (Exception e) {
            // 日志记录和异常处理
            return false;
        }
    }

}
