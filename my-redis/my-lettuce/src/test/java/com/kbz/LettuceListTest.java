package com.kbz;

import org.junit.Test;

import com.kbz.util.RedisClientUtil;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceListTest {

  @Test
  public void test_push() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.lpush("list01", "1", "2", "3", "4", "5");
      commands.rpush("list02", "1", "2", "3", "4", "5");
      System.out.println("list01 lrange 0 ~ -1 : " + commands.lrange("list01", 0, -1));
      System.out.println("list02 lrange 0 ~ -1 : " + commands.lrange("list02", 0, -1));
      System.out.println("list01 length : " + commands.llen("list01"));
      System.out.println("list02 length : " + commands.llen("list02"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_pop() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.lpush("list01", "1", "2", "3", "4", "5");
      System.out.println("before pop, list01 lrange 0 ~ -1 : " + commands.lrange("list01", 0, -1));
      System.out.println("lpop list01 : " + commands.lpop("list01"));
      System.out.println("after lpop, list01 lrange 0 ~ -1 : " + commands.lrange("list01", 0, -1));
      System.out.println("rpop list01 : " + commands.rpop("list01"));
      System.out.println("after rpop, list01 lrange 0 ~ -1 : " + commands.lrange("list01", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_lindex() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.lpush("list01", "1", "2", "3", "4", "5");
      System.out.println("list01 lrange 0 ~ -1 : " + commands.lrange("list01", 0, -1));
      System.out.println("list01 lindex 0 : " + commands.lindex("list01", 0));
      System.out.println("list01 lindex -1 : " + commands.lindex("list01", -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_lrem() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.lpush("list01", "1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
      System.out.println("list01 lrange 0 ~ -1 : " + commands.lrange("list01", 0, -1));
      System.out.println("list01 lrem 3 : " + commands.lrem("list01", 2, "3"));
      System.out.println("list01 lrange 0 ~ -1 : " + commands.lrange("list01", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_ltrim() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.lpush("list01", "1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
      System.out.println("list01 lrange 0 ~ -1 : " + commands.lrange("list01", 0, -1));
      System.out.println("list01 ltrim 0 ~ 3 : " + commands.ltrim("list01", 0, 3));
      System.out.println("list01 lrange 0 ~ -1 : " + commands.lrange("list01", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_rpoplpush() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.lpush("list1", "1", "2", "3", "4", "5");
      commands.lpush("list2", "6", "7", "8", "9", "10");
      System.out.println("before rpoplpush ,lrange 0 ~ -1 :" + commands.lrange("list1", 0, -1));
      System.out.println("before rpoplpush ,lrange 0 ~ -1 :" + commands.lrange("list2", 0, -1));
      System.out.println("list1 rpop to list2 lpush :" + commands.rpoplpush("list1", "list2"));
      System.out.println("after rpoplpush ,lrange 0 ~ -1 :" + commands.lrange("list1", 0, -1));
      System.out.println("after rpoplpush ,lrange 0 ~ -1 :" + commands.lrange("list2", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_lset() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.lpush("list1", "1", "2", "3", "4", "5");
      System.out.println("before lset ,lrange 0 ~ -1 :" + commands.lrange("list1", 0, -1));
      System.out.println("list1 lset :" + commands.lset("list1", 1, "999"));
      System.out.println("after lset ,lrange 0 ~ -1 :" + commands.lrange("list1", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_linsert() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.lpush("list1", "1", "2", "3", "4", "5");
      System.out.println("before linsert, lrange 0 ~ -1 :" + commands.lrange("list1", 0, -1));
      System.out.println("list1 linsert :" + commands.linsert("list1", true, "1", "hello"));
      System.out.println("after linsert, lrange 0 ~ -1 :" + commands.lrange("list1", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }
}
