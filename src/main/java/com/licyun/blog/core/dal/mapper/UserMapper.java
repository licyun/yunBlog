package com.licyun.blog.core.dal.mapper;

import com.licyun.blog.core.dal.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper{
  
  User loadByNameAndPass(@Param("username")String username, @Param("password")String password);
  
}
