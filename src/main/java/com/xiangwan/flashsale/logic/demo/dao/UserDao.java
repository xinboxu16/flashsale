package com.xiangwan.flashsale.logic.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiangwan.flashsale.logic.demo.domain.User;

public interface UserDao {
	@Select("select * from user where id = #{id}")
	public User getUserById(@Param("id")long id);
}
