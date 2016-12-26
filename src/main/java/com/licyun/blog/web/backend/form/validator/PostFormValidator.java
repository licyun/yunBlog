package com.licyun.blog.web.backend.form.validator;

import com.licyun.blog.core.dal.entity.Post;
import com.licyun.blog.core.plugin.MapContainer;
import com.licyun.blog.core.dal.constants.PostConstants;
import com.licyun.blog.core.util.StringUtils;

public class PostFormValidator{

  public static MapContainer validatePublish(Post post){
    return validatePost(post, true);
  }

  public static MapContainer validateUpdate(Post post){
    MapContainer form = validatePublish(post);
    if(StringUtils.isBlank(post.getId())){
      form.put("msg", "文章ID不合法");
    }

    return form;
  }

  public static MapContainer validateFastUpdate(Post post){
    MapContainer form = validatePost(post, false);
    if(StringUtils.isBlank(post.getId())){
      form.put("msg", "文章ID不合法");
    }

    return form;
  }

  private static MapContainer validatePost(Post post, boolean verifyContent){
    MapContainer form = new MapContainer();
    if(StringUtils.isBlank(post.getTitle())){
      form.put("msg", "文章标题未填写");
    }else if(verifyContent && StringUtils.isBlank(post.getContent())){
      form.put("msg", "请填写文章内容");
    }else if(PostConstants.TYPE_POST.equals(post.getType()) && StringUtils.isBlank(post.getCategoryid())){
      form.put("msg", "请选择文章分类");
    }

    return form;
  }

}
