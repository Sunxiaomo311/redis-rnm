package com.taste3.rnm.core.service;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.MultiKeyCommands;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ClearRedisService {

    protected static RedisTemplate<String, Object> redis;

    public static boolean matchScanAndSetExpire(String pattern, Integer count) {
        // 这个不是返回结果的数量，应该是每次scan的数量
        // 这一次scan match到的结果
        // 不断拿着新的cursor scan，最终会拿到所有匹配的值
        return Boolean.TRUE.equals(redis.execute((RedisCallback<Boolean>) connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            MultiKeyCommands multiKeyCommands = (MultiKeyCommands) commands;
            ScanParams scanParams = new ScanParams();
            scanParams.match(pattern);
            // 这个不是返回结果的数量，应该是每次scan的数量
            scanParams.count(count);
            ScanResult<String> scan = multiKeyCommands.scan("0", scanParams);
            while (null != scan.getStringCursor()) {
                // 这一次scan match到的结果
                long time = Math.round(Math.random() * 5 * 60 * 60);
                if (!CollectionUtils.isEmpty(scan.getResult())) {
                    scan.getResult().forEach(key -> {
                        if (ttl(key) < 0) {
                            expire(key, time);
                        }
                    });
                }
                // 不断拿着新的cursor scan，最终会拿到所有匹配的值
                if (!scan.getStringCursor().equals("0")) {
                    scan = multiKeyCommands.scan(scan.getStringCursor(), scanParams);
                    continue;
                } else {
                    break;
                }
            }
            return true;
        }));
    }

    protected static Long ttl(final String key) {
        return redis.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redis.getStringSerializer();
            return connection.ttl(Objects.requireNonNull(serializer.serialize(key)));
        });
    }

    /**
     * 过期时间精确到秒
     *
     * @param key
     * @param expire
     * @return
     */
    protected static Boolean expire(final String key, long expire) {
        return redis.expire(key, expire, TimeUnit.SECONDS);
    }
}
