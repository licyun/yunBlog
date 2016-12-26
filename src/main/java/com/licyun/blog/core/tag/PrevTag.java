package com.licyun.blog.core.tag;

import javax.servlet.jsp.JspException;

import com.licyun.blog.core.plugin.PageModel;

public class PrevTag extends AbstartTagSupport{
  private static final long serialVersionUID = 1L;

  @Override
  public int doStartTag() throws JspException{
    PageModel<?> model = getPagination().getModel();

    if(model.getPageIndex() > 1){
      setPageAttribute(model.getPageIndex() - 1);
      return EVAL_BODY_INCLUDE;
    }else{
      return SKIP_BODY;
    }
  }

}
