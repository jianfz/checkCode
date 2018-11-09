package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.mapper.UrlConfigMapper;
import springboot.model.UrlConfig;

@Service
public class UrlConfigService {
	@Autowired
	private UrlConfigMapper urlConfigMapper;
	 
	public UrlConfig getUrlConfig(String keyWord,String type){
		UrlConfig urlConfig = urlConfigMapper.findUrlConfig(keyWord, type);
		return urlConfig;
	}

}
