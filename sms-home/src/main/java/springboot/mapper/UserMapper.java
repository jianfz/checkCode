package springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import springboot.model.TestMybatis;
import springboot.model.User;

@Mapper
public interface UserMapper {
	@Insert("insert into sys_user(id,username, password) values(#{id},#{username}, #{password})")
	int add(User u);

	@Update("update sys_user set username = #{username}, password = #{password} where id = #{id}")
	int update(@Param("username") String name, @Param("password") String password,
			@Param("id") int id);

	@Delete("delete from sys_user where id = #{id}")
	int delete(int id);

	@Select("select id, username, password from sys_user where id = #{id}")
	User findUser(@Param("id") int id);

	@Select("select * from sys_user")
	List<User> findUserList();
	
	
	@Select("select * from sys_user where username= #{username} and password=#{password}")
	User findUserByup(@Param("username") String username,@Param("password") String password);
}
