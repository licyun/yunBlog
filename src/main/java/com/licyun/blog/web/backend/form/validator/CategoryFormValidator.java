package com.licyun.blog.web.backend.form.validator;

import com.licyun.blog.core.dal.entity.Category;
import com.licyun.blog.core.plugin.MapContainer;
import com.licyun.blog.core.util.StringUtils;

public class CategoryFormValidator{

  public static MapContainer validateInsert(Category category){
    MapContainer form = new MapContainer();
    if(StringUtils.isBlank(category.getName())){
      form.put("msg", "分类名称不能为空");
    }

    return form;
  }

}
