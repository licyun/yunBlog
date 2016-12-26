package com.licyun.blog.web.front.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.licyun.blog.biz.PostManager;
import com.licyun.blog.biz.aop.StaticTemplate;
import com.licyun.blog.core.WebConstants;
import com.licyun.blog.core.dal.constants.OptionConstants;
import com.licyun.blog.core.dal.constants.PostConstants;
import com.licyun.blog.core.feed.ArticleAdapter;
import com.licyun.blog.core.feed.Channel;
import com.licyun.blog.core.feed.Channel.Article;
import com.licyun.blog.core.feed.RssFeedWriter;
import com.licyun.blog.core.util.ServletUtils;
import com.licyun.blog.core.util.StringUtils;
import com.licyun.blog.service.OptionsService;
import com.licyun.blog.service.vo.PostVO;

@Controller
public class IndexController{
  @Autowired
  private PostManager postManager;
  @Autowired
  private StaticTemplate staticTemplate;
  @Autowired
  private OptionsService optionsService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(@RequestParam(value = "page", defaultValue = "1") int page, String word, Model model){
    if(!StringUtils.isBlank(word)){
      word = word.trim();
      model.addAttribute("page", postManager.search(word, page));
      model.addAttribute("search", word);
      model.addAttribute(WebConstants.PRE_TITLE_KEY, word);
    }else{
      model.addAttribute("page", postManager.listPost(page, 10));
    }

    return "index";
  }

  @RequestMapping(value = "/feed")
  public void rss(HttpServletRequest request, HttpServletResponse response) throws IOException{
    Channel channel = new Channel();
    channel.setDomain(ServletUtils.getDomain(request));
    channel.setLogoUrl(channel.getDomain() + "/resource/img/logo.png");
    channel.setTitle(optionsService.getOptionValue(OptionConstants.TITLE));
    channel.setDescription(optionsService.getOptionValue(OptionConstants.DESCRIPTION));

    List<Article> items = new ArrayList<>();
    for(PostVO pvo : postManager.listRecent(10, PostConstants.POST_CREATOR_ALL)){
      items.add(new ArticleAdapter(pvo));
    }
    channel.setItems(items);
    response.setContentType("text/xml");
    try{
      RssFeedWriter.write(channel, response.getOutputStream());
    }catch(XMLStreamException | IOException e){
      throw new IOException(e);
    }
  }

  @RequestMapping(value = "/sitemap", method = RequestMethod.GET)
  public String sitemap(Model model){
    List<PostVO> posts = postManager.listBySitemap();
    model.addAttribute("posts", posts);

    return "sitemap";
  }

  @RequestMapping("/restatic.json")
  public void restatic(HttpServletRequest request){
  }

}
