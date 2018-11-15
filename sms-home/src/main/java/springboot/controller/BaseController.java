package springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springboot.model.TestMybatis;
import springboot.redis.RedisDao;
import springboot.redis.User;
import springboot.service.AccountService;

@Controller
@ResponseBody
public class BaseController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping("/map")
	public Map testMap() {
		Map map = new HashMap();
		map.put("name", "zjf1");
		return map;
	}

	@RequestMapping("/list")
	public List<Map<String, String>> testList() {
		System.out.println("*************************");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map map1 = new HashMap();
		map1.put("name", "zjf1");
		Map map2 = new HashMap();
		map2.put("name", "zjf2");
		Map map3 = new HashMap();
		map3.put("name", "zjf3");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map1);
		return list;
	}

	@RequestMapping("db2test")
	public Map testDb2() {
		Map map = new HashMap();
		String sql = "select * from sys_user where username='gurongzq'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list != null && list.size() > 0) {
			map = list.get(0);
		}
		return map;
	}

	@RequestMapping("setRedisTest")
	public String setKey(String key, String value) {
		redisDao.setKey(key, value);
		return "set redis key successfully";
	}

	@RequestMapping("getRedisTest")
	public String getKey(String key) {
		return redisDao.getKey(key);
	}

	@RequestMapping("getAccountList")
	public List<TestMybatis> getAccountList() {
		return accountService.findAccountList();
	}

	@RequestMapping("getAccount")
	public TestMybatis getTestMybatis(int id) {
		return accountService.findAccount(id);
	}

	@RequestMapping("setAccount")
	public String setAccount(int id, String name, double money) {
		TestMybatis t = accountService.add(id, name, money);
		return "test MyBatis+redis successfully" + t.getName();

	}

	@RequestMapping("setHash")
	public String setHash(String username, String password, String age,
			String classname, String schoolname) {
		User user = new User(username, password, age, classname, schoolname);
		redisDao.setHash("user" + username, user);
		return "set redis hash key successfully";
	}

	@RequestMapping("getHash")
	public User getHash(String username) {
		User u = redisDao.getHash("user" + username);
		return u;
	}
	@RequestMapping("logList")
	public ModelAndView  getLog() {
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("logList");
	    return mv;
	}
}
