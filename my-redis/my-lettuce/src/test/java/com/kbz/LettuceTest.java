package com.kbz;

import org.junit.Test;

import com.kbz.util.RedisClientUtil;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceTest {

  @Test
  public void test_ping_sync() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      System.out.println(commands.ping());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_ping_async() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisAsyncCommands<String, String> commands = connection.async();
      RedisFuture<String> redisFuture = commands.ping();
      System.out.println(redisFuture.get());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }
}
