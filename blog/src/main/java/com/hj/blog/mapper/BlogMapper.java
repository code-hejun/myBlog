package com.hj.blog.mapper;

import com.github.pagehelper.PageInfo;
import com.hj.blog.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BlogMapper {

   Tag getTagById(Integer id);

   List<Tag> listTag();

   List<Tag> listTagWithIds(String ids);

   void saveTag(Tag tag);

   void updateTag(Tag tag);

   void removeTagById(Integer id);

   List<Tag> getTagTop(Integer count);

   //-----------------------------------

    Type getTypeById(Long id);

    List<Type> listType();

    void saveType(Type type);

    void updateType(Type type);

    void removeTypeById(Integer id);

    List<Type> getTypeTop(Integer count);

    //------------------------------------

    List<Blog> listBlog();

    Blog getBlogById(Long id);

    //分布查询使用
    List<Tag> getBlogTags(Integer id);

    boolean savaBlog(Blog blog);

    boolean updateBlog(Blog blog);

    boolean savaBlogTag(Long bid, Long rid);

    boolean deleteBlogTag(Long id);

    boolean deleteBlog(Long id);

    List<Blog> listBlogForIndex();

    Integer getMsgNumById(Integer id);

    List<Blog> getBlogByViewsTop(Integer count);

    List<Blog> getBlogByCreateTime(Integer count);

    List<String> getAllBlogYear();

    List<Blog> getBlogByYear(String year);

    Integer getBlogCount();

    void updateBlogViewsById(Long id);

    //------------------------------------

    void saveComment(Comment comment);

    List<Comment> listCommentByBlogId(Long blogId);

    List<Comment> listReplyCommentByBlogId(Long blogId,Long parentCommentId);

    //------------------------------------

    List<Blog> listBlogForType(Long typeId);

    List<Blog> listBlogForTag(Long tagId);

    //------------------------------------

    User checkUser(String username ,String password);

}
