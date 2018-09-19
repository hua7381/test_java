package zgh;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Redis {

	@Test
	public void fn1() {
		Jedis jedis = null;
		try {
			jedis = new Jedis("localhost");
			System.out.println("服务正在运行: " + jedis.ping());
			
			// 设置 redis 字符串数据
			jedis.set("key1", "hello world 阿斯蒂芬");
			
		} finally {
			jedis.close();
		}
	}
	
	@Test
	public void fn2() {
		Jedis jedis = null;
		try {
			jedis = new Jedis("localhost");

			System.out.println("key1: " + jedis.get("key1"));
			
		} finally {
			jedis.close();
		}
	}
	
	@Test
	public void fn3() {
		Jedis jedis = null;
		try {
			jedis = new Jedis("localhost");

			jedis.del("list1");
			
			//存储数据到列表中
	        jedis.lpush("list1", "a1");
	        jedis.lpush("list1", "b1");
	        jedis.lpush("list1", "c1");
			
		} finally {
			jedis.close();
		}
	}
	
	@Test
	public void fn4() {

		Jedis jedis = null;
		try {
			jedis = new Jedis("localhost");

	        // 获取存储的数据并输出
	        List<String> list = jedis.lrange("list1", 0 ,10);
	        for(int i=0; i<list.size(); i++) {
	            System.out.println("列表项为: "+list.get(i));
	        }
			
		} finally {
			jedis.close();
		}
	}
	
}
