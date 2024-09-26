package net.heimeng.common.jedis.service;

import net.heimeng.common.core.util.JsonUtils;
import net.heimeng.common.jedis.config.JedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Redis 服务类, 提供对 Redis 数据库的基本操作
 *
 * @author InwardFlow
 */
@Slf4j
@Service
public class JedisService implements DisposableBean {

    private final Jedis jedis;
    private final JedisConfig jedisConfig;

    public JedisService(Jedis jedis, JedisConfig jedisConfig) {
        this.jedis = jedis;
        this.jedisConfig = jedisConfig;
        // 初始化时选择默认数据库
        this.jedis.select(jedisConfig.getDatabase());
    }

    /**
     * 将对象以 JSON 格式存储到 Redis
     *
     * @param key   键
     * @param value 值
     * @param index Redis 数据库索引
     */
    public void set(String key, Object value, int index) {
        try {
            jedis.select(index);
            jedis.set(key, JsonUtils.toJsonString(value));
        } catch (JedisException e) {
            // 日志记录和异常处理
            log.warn("Jedis 设置值时发生异常!", e);
        }
    }

    /**
     * 从 Redis 获取值并转换为指定类型
     *
     * @param key   键
     * @param index Redis 数据库索引
     * @return 值
     */
    public String get(String key, int index) {
        try {
            jedis.select(index);
            return jedis.get(key);
        } catch (JedisException e) {
            // 日志记录和异常处理
            log.warn("Jedis 获取值时发生异常!", e);
            return null;
        }
    }

    /**
     * 从 Redis 删除值
     *
     * @param key   键
     * @param index Redis 数据库索引
     * @return 值
     */
    public long del(String key, int index) {
        try {
            jedis.select(index);
            return jedis.del(key);
        } catch (JedisException e) {
            // 日志记录和异常处理
            log.warn("Jedis 获取值时发生异常!", e);
            return 0;
        }
    }

    /**
     * 检查键是否存在于 Redis 中
     *
     * @param key 键
     * @return 如果键存在返回 true，否则返回 false
     */
    public boolean exists(String key, int index) {
        try {
            jedis.select(index);
            return jedis.exists(key);
        } catch (JedisException e) {
            // 日志记录和异常处理
            log.error("Jedis 在查询值是否存在时发生异常!", e);
            return false;
        }
    }

    /**
     * 使用默认数据库索引从 Redis 获取值
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        return get(key, jedisConfig.getDatabase());
    }

    /**
     * 使用默认数据库索引将对象以 JSON 格式存储到 Redis
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        set(key, value, jedisConfig.getDatabase());
    }

    /**
     * 使用默认数据库删除键
     *
     * @param key 键
     * @return 删除的数量
     */
    public long del(String key) {
        return del(key, jedisConfig.getDatabase());
    }

    /**
     * 使用默认数据库查询键是否存在
     *
     * @param key 键
     */
    public boolean exists(String key) {
        return exists(key, jedisConfig.getDatabase());
    }

    @Override
    public void destroy() {
        // 在类销毁时关闭 Jedis 客户端
        jedis.close();
    }
}
