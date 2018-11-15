package springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import springboot.mapper.UserMapper;
import springboot.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	@CachePut(key = "#p0")
	public User add(int id, String username, String password) {
		
		User u = new User();
		u.setId(id);
		u.setUsername(username);
		u.setPassword(password);
		this.userMapper.add(u);
		return this.userMapper.findUser(u.getId());
	}

	public int update(String username, String password, int id) {
		return userMapper.update(username, password, id);
	}

	public int delete(int id) {
		return userMapper.delete(id);
	}

	@Cacheable(key = "#p0")
	public User findUser(int id) {
		System.out.println("***********************查询************************");
		return userMapper.findUser(id);
	}

	
	@Cacheable(key = "#p0")
	public User findUserByup(String username,String password) {
		System.out.println("***********************查询************************");
		return userMapper.findUserByup(username,password);
	}
	
	
	public List<User> findUserList() {
		return userMapper.findUserList();
	}
}
