package com.kbz.util;

import io.lettuce.core.RedisClient;

public class RedisClientUtil {
  private static volatile RedisClient redisClient;

  private RedisClientUtil() {}

  public static RedisClient getRedisClientInstance() {
    if (redisClient == null) {
      synchronized (RedisClientUtil.class) {
        if (redisClient == null) {
          redisClient = RedisClient.create("redis://127.0.0.1:6379/0");
        }
      }
    }
    return redisClient;
  }
}
