package springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import springboot.model.TestMybatis;

@Mapper
public interface AccountMapper {
	@Insert("insert into testMybatis(id,name, money) values(#{id},#{name}, #{money})")
	int add(TestMybatis t);

	@Update("update testMybatis set name = #{name}, money = #{money} where id = #{id}")
	int update(@Param("name") String name, @Param("money") double money,
			@Param("id") int id);

	@Delete("delete from testMybatis where id = #{id}")
	int delete(int id);

	@Select("select id, name as name, money as money from testMybatis where id = #{id}")
	TestMybatis findAccount(@Param("id") int id);

	@Select("select id, name as name, money as money from testMybatis")
	List<TestMybatis> findAccountList();
}
