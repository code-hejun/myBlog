package com.hj.blog.service;

import com.hj.blog.mapper.BlogMapper;
import com.hj.blog.po.Comment;

import java.util.List;


public interface CommentService {

    void saveComment(Comment comment);

    List<Comment> listCommentByBlogId(Long blogId);

    List<Comment> listReplyCommentByBlogId(Long blogId,Long parentCommentId);

}
