package com.licyun.blog.core.dal.mapper;

import java.util.List;

import com.licyun.blog.core.plugin.MapContainer;
import com.licyun.blog.service.vo.CommentVO;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper extends BaseMapper{

  List<CommentVO> listRecent();

  List<MapContainer> listCountByGroupStatus();

  /**
   * 根据postid获取被批准的评论和指定creator的评论
   * 
   * @param postid
   * @param creator
   * @return
   */
  List<CommentVO> listByPost(@Param("postid") String postid, @Param("creator") String creator);

  /**
   * 更改comment的状态
   * 
   * @param commentid
   */
  int setStatus(@Param("commentid") String commentid, @Param("status") String status);

}
