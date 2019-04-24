package com.kbz;

import org.junit.Test;

import com.kbz.util.RedisClientUtil;

import io.lettuce.core.Range;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceZset {
  @Test
  public void test() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.zadd("zset01", 50, "v1");
      commands.zadd("zset01", 60, "v2");
      commands.zadd("zset01", 70, "v3");
      commands.zadd("zset01", 80, "v4");
      commands.zadd("zset01", 90, "v5");
      commands.zadd("zset01", 100, "v6");
      System.out.println("zset01 range 0 ~ -1 : " + commands.zrange("zset01", 0, -1));
      System.out.println("zset01 revrange 0 ~ -1 : " + commands.zrevrange("zset01", 0, -1));
      System.out.println("zset01 zcard : " + commands.zcard("zset01"));
      System.out.println("zset01 count : " + commands.zcount("zset01", Range.create(50, 90)));
      System.out.println("zset01 zrank : " + commands.zrank("zset01", "v2"));
      System.out.println("zset01 zrevrank v4 : " + commands.zrevrank("zset01", "v4"));
      System.out.println("zset01 zscore v4 : " + commands.zscore("zset01", "v4"));
      System.out.println("zset01 zrange 0 ~ -1 : " + commands.zrange("zset01", 0, -1));
      System.out.println(
          "zset01 zrangeByScore 50 ~ 90 : "
              + commands.zrangebyscore("zset01", Range.create(50, 90)));
      System.out.println(
          "zset01 zrangeByScoreWithScores 50 ~ 90 : "
              + commands.zrangebyscoreWithScores("zset01", Range.create(50, 90)));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }
}
