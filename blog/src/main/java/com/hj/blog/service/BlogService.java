package com.hj.blog.service;

import com.github.pagehelper.PageInfo;
import com.hj.blog.po.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<Blog> listBlog();

    PageInfo<Blog> listBlog(int currentPage, int pageSize);

    Blog getBlogById(Long id);

    Blog getAndConvert(Long id);

    boolean savaBlog(Blog blog);

    boolean updateBlog(Blog blog);

    boolean deleteBlog(Long id);

    PageInfo<Blog> listBlogForIndex(int currentPage, int pageSize);

    PageInfo<Blog> listBlogForType(int currentPage, int pageSize,Long typeId);

    PageInfo<Blog> listBlogForTag(int currentPage, int pageSize,Long tagId);

    List<Blog> getBlogByViewsTop(Integer count);

    List<Blog> getBlogByCreateTime(Integer count);

    Map<String,List<Blog>> getBlogByYear();

    Integer getBlogCount();

}
