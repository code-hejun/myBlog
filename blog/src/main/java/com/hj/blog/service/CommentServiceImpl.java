package com.hj.blog.service;

import com.hj.blog.mapper.BlogMapper;
import com.hj.blog.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public void saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId == -1) {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        blogMapper.saveComment(comment);
    }

    @Override
    @Transactional
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> old_list = blogMapper.listCommentByBlogId(blogId);
        List<Comment> new_list = new ArrayList<>();
        if (old_list !=null){
            for(Comment comment : old_list){
                if(comment.getParentComment()==null){
                    comment.setReplyComments(listReplyCommentByBlogId(comment.getBlog().getId(),comment.getId()));
                    new_list.add(comment);
                }
            }
        }
        //System.out.println(new_list);
        return new_list;
    }

    @Override
    public List<Comment> listReplyCommentByBlogId(Long blogId, Long parentCommentId) {
        return blogMapper.listReplyCommentByBlogId(blogId,parentCommentId);
    }
}
