package com.licyun.blog.core.dal.mapper;

import java.util.List;

import com.licyun.blog.core.dal.entity.Tag;

public interface TagMapper extends BaseMapper{

  public int insertBatch(List<Tag> list);
  
  public int deleteByPostid(String postid);
  
  public List<String> getTagsByPost(String postid);

}
