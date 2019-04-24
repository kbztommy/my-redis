package com.kbz;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.kbz.util.RedisClientUtil;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceStringTest {

  @Test
  public void test_set() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.set("k1", "v1");
      System.out.println("k1 : " + commands.get("k1"));
      System.out.println("k1 length : " + commands.strlen("k1"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_mset() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      Map<String, String> map = new HashMap<>();
      map.put("k1", "v1");
      map.put("k2", "k2");
      map.put("k3", "v3");
      commands.mset(map);
      System.out.println("k1 : " + commands.get("k1"));
      System.out.println("k2 : " + commands.get("k2"));
      System.out.println("k3 : " + commands.get("k3"));
      System.out.println("keys k : " + commands.keys("k?"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_getrange() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.set("k4", "Hello World!");
      commands.getrange("k4", 0, -1);
      System.out.println("k4 range 0 ~ -1 : " + commands.getrange("k4", 0, -1));
      System.out.println("k4 range 5 ~ 11 : " + commands.getrange("k4", 5, 10));

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_setrange() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.set("k4", "Hello World!");
      System.out.println("key : k4 , value : " + commands.get("k4"));
      System.out.println("length : " + commands.strlen("k4"));
      System.out.println("brfore setrange 0 xxxxx");
      commands.setrange("k4", 0, "xxxxx");
      System.out.println("key : k4 , value : " + commands.get("k4"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  @Test
  public void test_setex() {
    RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    try {
      RedisCommands<String, String> commands = connection.sync();
      commands.flushdb();
      commands.setex("k1", 5, "v1");
      System.out.println("k1 : " + commands.get("k1"));
      System.out.println("key : k1 , ttl : " + commands.ttl("k1"));
      Thread.sleep(6 * 1000);
      System.out.println("sleep 6 s");
      System.out.println("k1 : " + commands.get("k1"));
      System.out.println("key : k1 , ttl : " + commands.ttl("k1"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }
  @Test
  public void test_setnx() {
	  RedisClient redisClient = RedisClientUtil.getRedisClientInstance();
	  StatefulRedisConnection<String, String> connection = redisClient.connect();
	  try {
		  RedisCommands<String, String> commands = connection.sync();
		  commands.flushdb();
		  commands.setnx("k1", "v1");
		  commands.setnx("k1", "v2");
		  System.out.println("k1 : " + commands.get("k1"));		
	  } catch (Exception e) {
		  e.printStackTrace();
	  } finally {
		  if (connection != null) {
			  connection.close();
		  }
	  }
  }
}
