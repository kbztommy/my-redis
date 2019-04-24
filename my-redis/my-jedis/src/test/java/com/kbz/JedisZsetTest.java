package com.kbz;

import org.junit.Test;

import com.kbz.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/** 在set的基礎上，加上一個score值 */
public class JedisZsetTest {

  @Test
  public void test() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.zadd("zset01", 60, "v1");
      jedis.zadd("zset01", 70, "v2");
      jedis.zadd("zset01", 80, "v3");
      jedis.zadd("zset01", 90, "v4");
      jedis.zadd("zset01", 100, "v5");
      System.out.println("zset01 zrange 0 ~ -1 : " + jedis.zrange("zset01", 0, -1));
      System.out.println("zset01 zrevrange 0 ~ -1 : " + jedis.zrevrange("zset01", 0, -1));
      System.out.println("zset01 zcard : " + jedis.zcard("zset01"));
      System.out.println("zset01 zcount 70 ~ 90 : " + jedis.zcount("zset01", 70, 90));
      System.out.println("zset01 zrank v4 : " + jedis.zrank("zset01", "v4"));
      System.out.println("zset01 zrevrank v4 : " + jedis.zrevrank("zset01", "v4"));
      System.out.println("zset01 zscore v4 : " + jedis.zscore("zset01", "v4"));
      System.out.println("zset01 zrange 0 ~ -1 : " + jedis.zrange("zset01", 0, -1));
      System.out.println(
          "zset01 zrangeByScore 50 ~ 70 : " + jedis.zrangeByScore("zset01", 50, 90, 0, 1));
      System.out.println(
          "zset01 zrangeByScoreWithScores 60 ~ 90 : "
              + jedis.zrangeByScoreWithScores("zset01", 60, 90));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }
}
