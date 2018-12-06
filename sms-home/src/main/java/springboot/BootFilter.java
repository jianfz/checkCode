package springboot;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;

import springboot.model.User;

@Configuration
public class BootFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		System.out.println("this is MyFilter,url :" + request.getRequestURI());
			String requestURI = request.getRequestURI().toUpperCase();
			//如果是登录页面 直接通过。
			if(requestURI.contains("LOGIN")||requestURI.contains("GETNUM")||requestURI.contains("GETCODE")||requestURI.contains("uploadData")){
				arg2.doFilter(arg0, arg1);
			}else{
				HttpSession session = request.getSession();
				User u = (User)session .getAttribute("user");
				//session中没有用户，说明没登录
				if(u==null){
					response.sendRedirect("http://47.105.32.32/sms-home/login");
					return ;
				}else{
					arg2.doFilter(arg0, arg1);
				}
			}	

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
