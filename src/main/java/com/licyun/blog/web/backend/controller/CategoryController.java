package com.licyun.blog.web.backend.controller;

import java.util.Date;
import java.util.List;

import com.licyun.blog.biz.CategoryManager;
import com.licyun.blog.core.dal.entity.Category;
import com.licyun.blog.core.plugin.MapContainer;
import com.licyun.blog.core.util.IdGenerator;
import com.licyun.blog.service.CategoryService;
import com.licyun.blog.web.backend.form.validator.CategoryFormValidator;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.licyun.blog.core.util.CollectionUtils;

@Controller("adminCategoryController")
@RequestMapping("/backend/categorys")
@RequiresRoles(value = { "admin", "editor" }, logical = Logical.OR)
public class CategoryController{
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private CategoryManager categoryManager;

  @RequestMapping(method = RequestMethod.GET)
  public String index(){
    return "backend/post/category";
  }

  @ResponseBody
  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public Object data(){
    List<MapContainer> list = categoryManager.listAsTree();
    for(MapContainer temp : list){
      temp.put("text", temp.remove("name"));
      List<MapContainer> nodes = temp.get("nodes");
      if(CollectionUtils.isEmpty(nodes))
        continue;

      for(MapContainer child : nodes){
        child.put("text", child.remove("name"));
        child.put("icon", "glyphicon glyphicon-star");
      }
    }

    return list;
  }

  @ResponseBody
  @RequestMapping(method = RequestMethod.POST)
  public Object insert(Category category, String parent){
    MapContainer form = CategoryFormValidator.validateInsert(category);
    if(!form.isEmpty()){
      return form.put("success", false);
    }

    category.setId(IdGenerator.uuid19());
    category.setCreateTime(new Date());
    category.setLastUpdate(category.getCreateTime());
    return new MapContainer("success", categoryService.insertChildren(category, parent));
  }

  @ResponseBody
  @RequestMapping(value = "/{categoryName}", method = RequestMethod.DELETE)
  public Object remove(@PathVariable String categoryName){
    categoryManager.remove(categoryName);
    return new MapContainer("success", true);
  }

}
