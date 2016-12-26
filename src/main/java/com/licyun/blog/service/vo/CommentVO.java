package com.licyun.blog.service.vo;

import java.util.ArrayList;
import java.util.List;

import com.licyun.blog.core.dal.entity.Post;
import com.licyun.blog.core.dal.entity.Comment;
import com.licyun.blog.core.plugin.TreeItem;

/**
 * 评论业务对象
 * 
 * @author zhou
 *
 */
public class CommentVO extends Comment implements TreeItem<CommentVO>{
  private Post post;
  private List<CommentVO> children;

  public Post getPost(){
    return post;
  }

  public void setPost(Post post){
    this.post = post;
  }

  public void setChildren(List<CommentVO> children){
    this.children = children;
  }

  @Override
  public List<CommentVO> getChildren(){
    return children;
  }

  @Override
  public void addChild(CommentVO comment){
    if(children == null)
      setChildren(new ArrayList<CommentVO>());

    getChildren().add(comment);
  }

}
