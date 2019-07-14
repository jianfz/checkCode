package springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import springboot.mapper.AccountMapper;
import springboot.model.TestMybatis;

@Service
@CacheConfig(cacheNames = "userCache")
// 本类内方法指定使用缓存时，默认的名称就是userCache
public class AccountService {
	@Autowired
	private AccountMapper accountMapper;

	@CachePut(key = "#p0")
	public TestMybatis add(int id, String name, double money) {
		TestMybatis t = new TestMybatis();
		t.setId(id);
		t.setName(name);
		t.setMoney(money);
		System.out.println("test");
		this.accountMapper.add(t);
		return this.accountMapper.findAccount(t.getId());
	}

	public int update(String name, double money, int id) {
		return accountMapper.update(name, money, id);
	}

	//删除
	public int delete(int id) {
		return accountMapper.delete(id);
	}

	@Cacheable(key = "#p0")
	public TestMybatis findAccount(int id) {
		System.out.println("***********************查询************************");
		return accountMapper.findAccount(id);
	}

	public List<TestMybatis> findAccountList() {
		return accountMapper.findAccountList();
	}
}