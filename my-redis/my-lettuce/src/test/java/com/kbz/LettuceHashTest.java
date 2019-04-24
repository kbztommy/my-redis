package com.kbz;

import org.junit.Test;

import com.kbz.util.RedisClientUtil;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceHashTest {
  @Test
  public void test() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.hset("user01", "id", "1");
      commands.hset("user01", "name", "tommy");
      commands.hset("user01", "age", "18");
      System.out.println("hgetall : " + commands.hgetall("user01"));
      System.out.println("hkeys : " + commands.hkeys("user01"));
      System.out.println("hvals : " + commands.hvals("user01"));
      System.out.println("user01 id : " + commands.hget("user01", "id"));
      System.out.println("user01 name : " + commands.hget("user01", "name"));
      System.out.println("user01 age : " + commands.hget("user01", "age"));
      System.out.println("user01 id exists: " + commands.hexists("user01", "id"));
      System.out.println("user01 email exists: " + commands.hexists("user01", "email"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }
}
