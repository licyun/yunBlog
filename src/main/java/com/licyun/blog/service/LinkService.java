package com.licyun.blog.service;

import com.licyun.blog.core.dal.mapper.BaseMapper;
import com.licyun.blog.core.plugin.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.licyun.blog.core.dal.entity.Link;
import com.licyun.blog.core.dal.mapper.LinkMapper;

@Service
public class LinkService extends BaseService{
  @Autowired
  private LinkMapper linkMapper;
  
  public PageModel<Link> list(int pageIndex, int pageSize){
    PageModel<Link> pageModel=new PageModel<>(pageIndex, pageSize);
    list(pageModel);
    
    return pageModel;
  }

  @Override
  protected BaseMapper getMapper(){
    return linkMapper;
  }

}
