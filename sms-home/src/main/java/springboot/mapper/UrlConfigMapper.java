package springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import springboot.model.UrlConfig;

@Mapper
public interface UrlConfigMapper {
	@Select("select * from url_config where urlName= #{urlName} and type= #{type} ")
	UrlConfig findUrlConfig(@Param("urlName") String urlName,@Param("type") String type);
}
