package com.licyun.blog.service;

import com.licyun.blog.core.dal.entity.User;
import com.licyun.blog.core.dal.mapper.BaseMapper;
import com.licyun.blog.core.dal.mapper.UserMapper;
import com.licyun.blog.core.plugin.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService{
  @Autowired
  private UserMapper userMapper;

  public PageModel<User> list(int pageIndex, int pageSize){
    PageModel<User> page = new PageModel<>(pageIndex, pageSize);
    super.list(page);
    return page;
  }
  
  public User login(String username,String password){
    return userMapper.loadByNameAndPass(username, password);
  }

  @Override
  protected BaseMapper getMapper(){
    return userMapper;
  }

}
