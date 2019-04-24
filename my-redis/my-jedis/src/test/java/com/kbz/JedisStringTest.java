package com.kbz;

import org.junit.Test;

import com.kbz.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/** String -> key value */
public class JedisStringTest {
  @Test
  public void test_get() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set("a", "A");
      System.out.println("key : a , value : " + jedis.get("a"));
      System.out.println("key : b , value : " + jedis.get("b"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_del() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set("a", "A");
      System.out.println("key : a , value : " + jedis.get("a"));
      System.out.println("delete key : a");
      jedis.del("a");
      System.out.println("key : a , value : " + jedis.get("a"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_append() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set("a", "A");
      System.out.println("key : a , value : " + jedis.get("a"));
      System.out.println("key : a , append 12345");
      jedis.append("a", "12345");
      System.out.println("key : a , value : " + jedis.get("a"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_strlen() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set("a", "AAAAA");
      System.out.println("key : a , value : " + jedis.get("a"));
      System.out.println("length : " + jedis.strlen("a"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_incr_and_decr() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set("b", "0");
      System.out.println("key : b , value : " + jedis.get("b"));

      System.out.println("b incr 1");
      jedis.incr("b");
      System.out.println("key : b , value : " + jedis.get("b"));

      System.out.println("b incr 3");
      jedis.incrBy("b", 3);
      System.out.println("key : b , value : " + jedis.get("b"));

      System.out.println("b decr 1");
      jedis.decr("b");
      System.out.println("key : b , value : " + jedis.get("b"));

      System.out.println("b decr 4");
      jedis.decrBy("b", 4);
      System.out.println("key : b , value : " + jedis.get("b"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_getrange() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set("a", "hello world");
      System.out.println("key : a , value : " + jedis.get("a"));
      System.out.println("length : " + jedis.strlen("a"));
      System.out.println("range 0 ~ 5  " + jedis.getrange("a", 0, 5));
      System.out.println("range 1 ~ 7  " + jedis.getrange("a", 1, 7));
      System.out.println("range 0 ~ -1  " + jedis.getrange("a", 0, -1));
      System.out.println("range 0 ~ 13  " + jedis.getrange("a", 0, 13));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_setrange() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set("a", "hello world");
      System.out.println("key : a , value : " + jedis.get("a"));
      System.out.println("length : " + jedis.strlen("a"));
      System.out.println("setrange 0 xxx");
      jedis.setrange("a", 0, "xxxxx");
      System.out.println("key : a , value : " + jedis.get("a"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_setex() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.setex("a", 5, "hello world");
      System.out.println("key : a , value : " + jedis.get("a"));
      System.out.println("key : a , ttl : " + jedis.ttl("a"));
      Thread.sleep(6 * 1000);
      System.out.println("after 6s ,key : a , value : " + jedis.get("a"));
      System.out.println("after 6s ,key : a , ttl : " + jedis.ttl("a"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_setnx() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.del("a");
      jedis.setnx("a", "hello world");
      System.out.println("key : a , value : " + jedis.get("a"));

      jedis.setnx("a", "good night");
      System.out.println("key : a , value : " + jedis.get("a"));

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_mset_mget() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.mset("a", "1", "b", "2", "c", "3");
      System.out.println("mget a,b,c : " + jedis.mget("a", "b", "c"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_msetnx() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.mset("a", "1", "b", "2", "c", "3");
      System.out.println("mget a,b,c : " + jedis.mget("a", "b", "c"));
      jedis.msetnx("a", "4", "b", "4", "c", "4", "d", "4");
      System.out.println("mget a,b,c,d : " + jedis.mget("a", "b", "c", "d"));
      jedis.msetnx("d", "4", "e", "5");
      System.out.println("mget a,b,c,d,e : " + jedis.mget("a", "b", "c", "d", "e"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }
}
