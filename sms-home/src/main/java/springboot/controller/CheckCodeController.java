package springboot.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springboot.checkCodeUtil.HttpAPIService;
import springboot.model.UrlConfig;
import springboot.service.UrlConfigService;

@Controller
@ResponseBody
public class CheckCodeController {

	@Resource
	private HttpAPIService httpAPIService;

	@Autowired
	private UrlConfigService urlConfigService;

	@RequestMapping("getNum")
	public String getNum(HttpServletRequest request) throws Exception {

		try {
			// 获取请求的参数
			// String param = request.getQueryString();
			Map<String, String[]> paramMap = request.getParameterMap();

			String urlName = "";
			if (paramMap.containsKey("urlName")) {
				urlName = paramMap.get("urlName")[0];
			} else {
				return "参数中无urlName!";
			}
			UrlConfig urlConfig = urlConfigService.getUrlConfig(urlName,
					"getNum");
			if (urlConfig == null) {
				return "urlName不正确!";
			}
			if (!"0".equals(urlConfig.getStatus())) {
				return "地址已失效!";
			}
			// 获取urlName对应的 获取号码的 url
			String targetUrl = urlConfig.getUrl();

			StringBuffer paramSb = new StringBuffer("");
			for (String key : paramMap.keySet()) {
				if ("urlName".equals(key)) {
					continue;
				}
				paramSb.append("&");
				paramSb.append(key);
				paramSb.append("=");
				paramSb.append(paramMap.get(key)[0]);
			}
			String paramStr = paramSb.toString();
			paramStr.substring(1, paramStr.length());

			String finalUrl = targetUrl + "?" + paramStr;

			String str = httpAPIService.doGet(finalUrl);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "解析参数出错!";

		}

	}

	@RequestMapping("getCode")
	public String getCode(HttpServletRequest request) throws Exception {

		try {
			// 获取请求的参数
			// String param = request.getQueryString();
			Map<String, String[]> paramMap = request.getParameterMap();

			String urlName = "";
			if (paramMap.containsKey("urlName")) {
				urlName = paramMap.get("urlName")[0];
			} else {
				return "参数中无urlName!";
			}
			UrlConfig urlConfig = urlConfigService.getUrlConfig(urlName,
					"getCode");
			if (urlConfig == null) {
				return "urlName不正确!";
			}
			if (!"0".equals(urlConfig.getStatus())) {
				return "地址已失效!";
			}
			// 获取urlName对应的 获取号码的 url
			String targetUrl = urlConfig.getUrl();

			StringBuffer paramSb = new StringBuffer("");
			for (String key : paramMap.keySet()) {
				if ("urlName".equals(key)) {
					continue;
				}
				paramSb.append("&");
				paramSb.append(key);
				paramSb.append("=");
				paramSb.append(paramMap.get(key)[0]);
			}
			String paramStr = paramSb.toString();
			paramStr.substring(1, paramStr.length());

			String finalUrl = targetUrl + "?" + paramStr;

			String str = httpAPIService.doGet(finalUrl);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "解析参数出错!";
		}

	}

}
