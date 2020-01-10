package com.xiangwan.flashsale.logic.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiangwan.flashsale.logic.user.domain.User;

public interface UserDao {
	@Select("select * from user where id = #{id}")
	public User getUserById(@Param("id")long id);

	@Insert("insert into user(id,name)values(#{id},#{name})")
	public int insert(User user);
	
	@Select("select * from user where phoneNumber = #{phoneNumber}")
	public User getUserByPhoneNumber(@Param("phoneNumber")long phoneNumber);
}
