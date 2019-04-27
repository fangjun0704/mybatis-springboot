package com.company.project.annotation.mapper;


import com.company.project.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 这是操作数据库的mapper
 * 如果Mapper类特别多的话 可以省略这个注解，但是一定需要在 Application 中使用@MapperScan代替，否则mapper接口无效
 */
@Mapper
public interface UserMapper {

  @Insert("INSERT INTO t_user(name,age,test_field) values(#{name},#{age},#{testField})")
  @Options(useGeneratedKeys = true, keyProperty = "uId")
    //插入数据后将数据库中的自增主键也一起返回并传入对象User
  void insertUser(User user);

  @Delete("DELETE FROM t_user WHERE u_id = #{uId}")
  int deleteUserById(Integer uId);

  @Update("UPDATE t_user WHERE u_id = #{uId}")
  void updateUser(User user);

  @Select("SELECT * FROM t_user WHERE u_id = #{uId}")
  User getUserById(Integer uId);

}
