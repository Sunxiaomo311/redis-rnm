package com.taste3.rnm.core;

import com.taste3.rnm.core.contant.RedisConstant;
import com.taste3.rnm.core.service.ClearRedisService;
import lombok.extern.slf4j.Slf4j;

/**
 * 清理redis死键方法类
 *
 * @author 3taste
 */
@Slf4j
public class ClearRedisKeyUtil {


    /**
     * 给指定key设置过期时间
     *
     * @param transactionId 要清理的key，例如：bonus:transaction:[^hash]*
     */
    public static void clearSpecifiedKey(String transactionId) {
        long time = System.currentTimeMillis();
        boolean flag = ClearRedisService.matchScanAndSetExpire(transactionId, RedisConstant.REDIS_SCAN_COUNT);
        long endTime = System.currentTimeMillis() - time;
        if (!flag) {
            log.error("---> clearSpecifiedKey transactionId {} ERROR!", transactionId);
        }
        log.info("---> clearSpecifiedKey transactionId {} SUCCESS. use time {}min, {}s",
                transactionId, endTime / 1000 / 60, endTime / 1000);
    }


}
