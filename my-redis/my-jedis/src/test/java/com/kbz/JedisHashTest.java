package com.kbz;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.kbz.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/** 常用，很重要！！！ K,V模式不變，但V是一個鍵值對。 */
public class JedisHashTest {

  @Test
  public void test_hset() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.hset("user", "id", "11");
      jedis.hset("user", "age", "20");
      jedis.hset("user", "name", "tommy");
      jedis.hsetnx("user", "email", "tommy@gmail.com");
      jedis.hsetnx("user", "name", "xxx");
      System.out.println("user getAll : " + jedis.hgetAll("user"));
      System.out.println("user name : " + jedis.hget("user", "name"));
      System.out.println("user id : " + jedis.hget("user", "id"));
      System.out.println("user age : " + jedis.hget("user", "age"));
      System.out.println("user email : " + jedis.hget("user", "email"));
      System.out.println("user length : " + jedis.hlen("user"));
      System.out.println("user exists name : " + jedis.hexists("user", "name"));
      System.out.println("user exists email : " + jedis.hexists("user", "email"));
      System.out.println("user keys : " + jedis.hkeys("user"));
      System.out.println("user vals : " + jedis.hvals("user"));
      System.out.println("user xxx : " + jedis.hget("user", "xxx"));
      System.out.println("xxx xxx : " + jedis.hget("xxx", "xxx"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_hmset() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      Map<String, String> customer = new HashMap<>();
      customer.put("id", "11");
      customer.put("name", "tommy");
      customer.put("age", "20");
      jedis.hmset("customer", customer);

      System.out.println("customer getAll : " + jedis.hgetAll("customer"));
      System.out.println("customer id : " + jedis.hmget("customer", "id"));
      System.out.println("customer name : " + jedis.hmget("customer", "name"));
      System.out.println("customer age : " + jedis.hmget("customer", "age"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_hdel() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      Map<String, String> customer = new HashMap<>();
      customer.put("id", "11");
      customer.put("name", "tommy");
      customer.put("age", "20");
      jedis.hmset("customer", customer);
      jedis.hdel("customer", "id");
      System.out.println("customer getAll : " + jedis.hgetAll("customer"));
      System.out.println("customer id : " + jedis.hmget("customer", "id"));
      System.out.println("customer name : " + jedis.hmget("customer", "name"));
      System.out.println("customer age : " + jedis.hmget("customer", "age"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_hincrby() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      Map<String, String> customer = new HashMap<>();
      customer.put("id", "11");
      customer.put("name", "tommy");
      customer.put("age", "20");
      jedis.hmset("customer", customer);
      jedis.hincrBy("customer", "age", 5);
      System.out.println("customer age : " + jedis.hmget("customer", "age"));
      jedis.hincrByFloat("customer", "age", 5.5);
      System.out.println("customer age : " + jedis.hmget("customer", "age"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }
}
