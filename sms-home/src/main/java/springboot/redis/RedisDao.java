package springboot.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	public void setKey(String key, String value) {
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		ops.set(key, value, 1, TimeUnit.MINUTES);
	}

	public String getKey(String key) {
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		return ops.get(key);

	}

	public void setHash(String key, Object o) {
		HashOperations<String, String, Object> hashOperations = redisTemplate
				.opsForHash();
		hashOperations.put(key, "testRedis", o);

	}

	public User getHash(String key) {
		HashOperations<String, String, User> hashOperations = redisTemplate
				.opsForHash();
		User u = hashOperations.get(key, "testRedis");
		return u;

	}
}