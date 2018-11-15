package springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springboot.model.User;
import springboot.service.UserService;

@ResponseBody
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	public ModelAndView  login() {
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("login/login");
	    return mv;
	}
	@RequestMapping("userLogin")
	public Map userLogin(HttpServletRequest request){
		String username = request.getParameter("login");
		String password = request.getParameter("pwd");
		User u = userService.findUserByup(username,password);
		Map map = new HashMap();
		if(null!=u){
			map.put("status", "success");
			map.put("Text", "登录成功!");
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
		}else{
			map.put("status", "fail");
		}
		
		return map;
	}
}
