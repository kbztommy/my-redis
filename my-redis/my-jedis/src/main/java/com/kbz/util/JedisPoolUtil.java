package com.kbz.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
  private static volatile JedisPool jedisPool = null;

  private JedisPoolUtil() {}

  public static JedisPool getJedisPoolInstance() {
    if (null == jedisPool) {
      synchronized (JedisPoolUtil.class) {
        if (null == jedisPool) {
          JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
          jedisPoolConfig.setMaxTotal(1000);
          jedisPoolConfig.setMaxIdle(32);
          jedisPoolConfig.setMaxWaitMillis(100 * 1000);
          jedisPoolConfig.setTestOnBorrow(true);
          jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
        }
      }
    }
    return jedisPool;
  }
}
