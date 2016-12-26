package com.licyun.blog.web.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.licyun.blog.biz.CommentManager;
import com.licyun.blog.biz.PostManager;
import com.licyun.blog.biz.VisitStatManager;
import com.licyun.blog.core.WebConstants;
import com.licyun.blog.core.util.CookieUtil;
import com.licyun.blog.service.vo.PostVO;

@Controller
@RequestMapping("/pages")
public class PageController{
  @Autowired
  private PostManager postManager;
  @Autowired
  private CommentManager commentManager;
  @Autowired
  private VisitStatManager visitStatManager;

  @RequestMapping("/{pageid}")
  public String page(@PathVariable("pageid") String pageid, HttpServletRequest request, Model model){
    PostVO post = postManager.loadReadById(pageid);
    if(post != null){
      visitStatManager.record(pageid);
      model.addAttribute(WebConstants.PRE_TITLE_KEY, post.getTitle());
      model.addAttribute("post", post);
      model.addAttribute("comments",
          commentManager.getAsTree(pageid, new CookieUtil(request, null).getCookie("comment_author")));
    }

    return post != null ? "post" : "404";
  }

}
