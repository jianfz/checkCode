package springboot.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class TestRedis {

	private StringRedisTemplate stringRedisTemplate;

	private RedisTemplate redisTemplate;

	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
	}

	public void testObj() throws Exception {
		User user = new User("aa@126.com", "aa", "aa123456", "aa", "123");
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		operations.set("com.neox", user);
		operations.set("com.neo.f", user, 1, TimeUnit.SECONDS);
		Thread.sleep(1000);
		// redisTemplate.delete("com.neo.f");
		boolean exists = redisTemplate.hasKey("com.neo.f");
		if (exists) {
			System.out.println("exists is true");
		} else {
			System.out.println("exists is false");
		}
		// Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
	}
}