package com.kbz;

import org.junit.Test;

import com.kbz.util.JedisPoolUtil;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * List -> 單key多Value。 
 * 一個字符串列表，左右都可插入、添加。 
 * 如果key不存在，創建新的鏈表。 
 * 如果key存在，新增內容。 如果value全移除，對應的key也會消失。
 * 鏈表的操作無論是頭、尾效率都極高，但假如是對中間元素的操作，效率就很慘淡。
 */
public class JedisListTest {

  @Test
  public void test_lpush() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("users", "tommy", "ann", "john", "jay");
      System.out.println("lpush,lrange 0 ~ -1 :" + jedis.lrange("users", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_rpush() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.rpush("users", "tommy", "ann", "john", "jay");
      System.out.println("rpush,lrange 0 ~ -1 :" + jedis.lrange("users", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_lpop() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("users", "tommy", "ann", "john", "jay");
      System.out.println("before lpop,lrange 0 ~ -1 :" + jedis.lrange("users", 0, -1));
      System.out.println("lpop : " + jedis.lpop("users"));
      System.out.println("after lpop,lrange 0 ~ -1 :" + jedis.lrange("users", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_rpop() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("users", "tommy", "ann", "john", "jay");
      System.out.println("before rpop,lrange 0 ~ -1 :" + jedis.lrange("users", 0, -1));
      System.out.println("lpop : " + jedis.rpop("users"));
      System.out.println("after rpop,lrange 0 ~ -1 :" + jedis.lrange("users", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_lindex() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("users", "tommy", "ann", "john", "jay");
      System.out.println("lrange 0 ~ -1 :" + jedis.lrange("users", 0, -1));
      System.out.println("index 0 :" + jedis.lindex("users", 0));
      System.out.println("index -1 :" + jedis.lindex("users", -1));
      System.out.println("index 3 :" + jedis.lindex("users", 3));
      System.out.println("index 4 :" + jedis.lindex("users", 4));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_llen() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("users", "tommy", "ann", "john", "jay");
      System.out.println("llen : " + jedis.llen("users"));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_lrem() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("list1", "1", "2", "2", "3", "3", "3", "4", "4", "4", "4");
      System.out.println("before lrem ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
      jedis.lrem("list1", 1, "3");
      System.out.println("after lrem ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_ltrim() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("list1", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
      System.out.println("before ltrim ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
      jedis.ltrim("list1", 3, 7);
      System.out.println("after ltrim ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_rpoplpush() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("list1", "1", "2", "3", "4", "5");
      jedis.lpush("list2", "6", "7", "8", "9", "10");
      System.out.println("before rpoplpush ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
      System.out.println("before rpoplpush ,lrange 0 ~ -1 :" + jedis.lrange("list2", 0, -1));
      jedis.rpoplpush("list1", "list2");
      System.out.println("after rpoplpush ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
      System.out.println("after rpoplpush ,lrange 0 ~ -1 :" + jedis.lrange("list2", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_lset() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("list1", "1", "2", "3", "4", "5");
      System.out.println("before lset ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
      jedis.lset("list1", 1, "999");
      System.out.println("after lset ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }

  @Test
  public void test_linsert() {
    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.flushDB();
      jedis.lpush("list1", "1", "2", "3", "4", "5", "x", "y", "1");
      System.out.println("before linsert_before ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
      jedis.linsert("list1", LIST_POSITION.BEFORE, "1", "java");
      System.out.println("after linsert_before ,lrange 0 ~ -1 :" + jedis.lrange("list1", 0, -1));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != jedis) {
        jedis.close();
      }
    }
  }
}
