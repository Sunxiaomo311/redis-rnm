package com.taste3.rnm.core.contant;

/**
 * Redis操作常量
 *
 * @author 3taste
 */
public class RedisConstant {

    /**
     * 30天
     */
    public static final Integer REDIS_SCAN_COUNT = 500;

    /**
     * 1分钟
     */
    public static final Long REDIS_EXPIRE_1MINUTE = (long) 60;

    /**
     * 3分钟
     */
    public static final Long REDIS_EXPIRE_3MINUTE = (long) 60 * 3;

    /**
     * 6分钟
     */
    public static final Long REDIS_EXPIRE_6MINUTE = (long) 60 * 6;

    /**
     * 40分钟
     */
    public static final Long REDIS_EXPIRE_40MINUTE = (long) 60 * 40;

    /**
     * 1小时
     */
    public static final Long REDIS_EXPIRE_1HOUR = (long) 60 * 60;

    /**
     * 4小时
     */
    public static final Long REDIS_EXPIRE_4HOUR = (long) 60 * 60 * 4;

    /**
     * 1天
     */
    public static final Long REDIS_EXPIRE_1DAY = (long) 60 * 60 * 24;

    /**
     * 1天
     */
    public static final Long REDIS_EXPIRE_3DAY = (long) 60 * 60 * 24 * 3;

    /**
     * 7天
     */
    public static final Long REDIS_EXPIRE_7DAY = (long) 60 * 60 * 24 * 7;

    /**
     * 15天
     */
    public static final Long REDIS_EXPIRE_15DAY = (long) 60 * 60 * 24 * 15;

    /**
     * 30天
     */
    public static final Long REDIS_EXPIRE_1MONTH = 60 * 60 * 24 * 30L;

}
