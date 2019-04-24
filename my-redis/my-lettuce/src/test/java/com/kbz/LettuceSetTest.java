package com.kbz;

import org.junit.Test;

import com.kbz.util.RedisClientUtil;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceSetTest {
  @Test
  public void test_sadd() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.sadd("set01", "1", "1", "2", "3", "4");
      System.out.println("set01 : " + commands.smembers("set01"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_srem() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.sadd("set01", "1", "1", "2", "3", "4");
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("remove 2,3 : " + commands.srem("set01", "2", "3"));
      System.out.println("set01 : " + commands.smembers("set01"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_srandmember() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.sadd("set01", "1", "1", "2", "3", "4");
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("randmember : " + commands.srandmember("set01"));
      System.out.println("randmember x 3 : " + commands.srandmember("set01", 3));
      System.out.println("randmember x 5 : " + commands.srandmember("set01", 5));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_spop() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.sadd("set01", "1", "1", "2", "3", "4");
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("spop : " + commands.spop("set01"));
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("spop : " + commands.spop("set01", 2));
      System.out.println("set01 : " + commands.smembers("set01"));

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_smove() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.sadd("set01", "1", "1", "2", "3", "4");
      commands.sadd("set02", "5", "6", "7", "8", "4");
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("set02 : " + commands.smembers("set02"));
      System.out.println("smove 3 from set01 to set02 : " + commands.smove("set01", "set02", "3"));
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("set02 : " + commands.smembers("set02"));
      System.out.println("smove 8 from set01 to set02 : " + commands.smove("set01", "set02", "3"));
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("set02 : " + commands.smembers("set02"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_sdiff() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.sadd("set01", "1", "1", "2", "3", "4");
      commands.sadd("set02", "5", "6", "7", "8", "4");
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("set02 : " + commands.smembers("set02"));
      System.out.println("sdiff : " + commands.sdiff("set01", "set02"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_sinter() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.sadd("set01", "1", "1", "2", "3", "4");
      commands.sadd("set02", "5", "6", "7", "8", "4");
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("set02 : " + commands.smembers("set02"));
      System.out.println("sinter : " + commands.sinter("set01", "set02"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_sunion() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.sadd("set01", "1", "1", "2", "3", "4");
      commands.sadd("set02", "5", "6", "7", "8", "4");
      System.out.println("set01 : " + commands.smembers("set01"));
      System.out.println("set02 : " + commands.smembers("set02"));
      System.out.println("sunion : " + commands.sunion("set01", "set02"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }
}
