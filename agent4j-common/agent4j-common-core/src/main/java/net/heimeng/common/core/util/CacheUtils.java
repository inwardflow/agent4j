package net.heimeng.common.core.util;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * 缓存操作工具类
 *
 * @author InwardFlow
 */
@SuppressWarnings(value = {"unchecked"})
public class CacheUtils {

    private static final CacheManager CACHE_MANAGER = SpringUtils.getBean(CacheManager.class);

    public static void put(String cacheName, Object key, Object value) {
        Cache cache = CACHE_MANAGER.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public static <T> T get(String cacheName, Object key) {
        Cache cache = CACHE_MANAGER.getCache(cacheName);
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(key);
            return valueWrapper != null ? (T) valueWrapper.get() : null;
        }
        return null;
    }

    public static void evict(String cacheName, Object key) {
        Cache cache = CACHE_MANAGER.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }

    public static void clear(String cacheName) {
        Cache cache = CACHE_MANAGER.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        }
    }
}
