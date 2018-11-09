package springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springboot.model.User;
import springboot.service.UserService;

@Controller
@ResponseBody
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("getUserList")
	public List<User> getUserList(){
		List<User> userList = userService.findUserList();
		return userList;
	}
}
