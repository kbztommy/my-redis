package com.kbz;

import org.junit.Test;

import com.kbz.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/** 單key多value */
public class JedisSetTest {

  @Test
  public void test_sadd() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.sadd("set01", "0", "0", "1", "2", "2", "3");
      System.out.println("smembers : " + jedis.smembers("set01"));
      System.out.println("is '1' a member of set01 : " + jedis.sismember("set01", "1"));
      System.out.println("scard : " + jedis.scard("set01"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_srem() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.sadd("set01", "0", "0", "1", "2", "2", "3");
      System.out.println("smembers before remove 1: " + jedis.smembers("set01"));
      jedis.srem("set01", "1");
      System.out.println("smembers after remove 1: " + jedis.smembers("set01"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_srandmember() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.sadd("set01", "0", "0", "1", "2", "2", "3");
      System.out.println("smembers : " + jedis.smembers("set01"));
      System.out.println("srandmember : " + jedis.srandmember("set01"));
      System.out.println("srandmember : " + jedis.srandmember("set01", 2));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_spop() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.sadd("set01", "1", "2", "3", "4", "5", "6");
      System.out.println("smembers before spop : " + jedis.smembers("set01"));
      System.out.println("spop : " + jedis.spop("set01"));
      System.out.println("smembers after spop : " + jedis.smembers("set01"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_smove() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.sadd("set01", "1", "2", "3", "4", "5", "6");
      jedis.sadd("set02", "7", "8", "9", "10");
      System.out.println("set01 : " + jedis.smembers("set01"));
      System.out.println("set02 : " + jedis.smembers("set02"));
      System.out.println("move 1 from set01 to set02 : " + jedis.smove("set01", "set02", "1"));
      System.out.println("set01 : " + jedis.smembers("set01"));
      System.out.println("set02 : " + jedis.smembers("set02"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_sdiff() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.sadd("set01", "1", "2", "3", "4", "5", "6");
      jedis.sadd("set02", "1", "2", "3", "4", "a", "b");
      System.out.println("set01 : " + jedis.smembers("set01"));
      System.out.println("set02 : " + jedis.smembers("set02"));
      System.out.println("在set01但不在set02 : " + jedis.sdiff("set01", "set02"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_sinter() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.sadd("set01", "1", "2", "3", "4", "5", "6");
      jedis.sadd("set02", "1", "2", "3", "4", "a", "b");
      System.out.println("set01 : " + jedis.smembers("set01"));
      System.out.println("set02 : " + jedis.smembers("set02"));
      System.out.println("set01與set02交集 : " + jedis.sinter("set01", "set02"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_sunion() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.sadd("set01", "1", "2", "3", "4", "5", "6");
      jedis.sadd("set02", "1", "2", "3", "4", "a", "b");
      System.out.println("set01 : " + jedis.smembers("set01"));
      System.out.println("set02 : " + jedis.smembers("set02"));
      System.out.println("set01與set02聯集 : " + jedis.sunion("set01", "set02"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }
}
