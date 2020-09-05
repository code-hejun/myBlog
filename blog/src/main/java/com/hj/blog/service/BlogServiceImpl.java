package com.hj.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hj.blog.common.MarkdownUtils;
import com.hj.blog.mapper.BlogMapper;
import com.hj.blog.po.Blog;
import com.hj.blog.po.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;


    @Override
    public List<Blog> listBlog() {
        return blogMapper.listBlog();
    }

    @Override
    public PageInfo<Blog> listBlog(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Blog> list = blogMapper.listBlog();
        return new PageInfo<Blog>(list);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }


    //跳转至阅读界面，阅读量+1
    @Transactional
    @Override
    public Blog getAndConvert(Long id){
        blogMapper.updateBlogViewsById(id);
        Blog blog =  blogMapper.getBlogById(id);
        /*blog.setViews(blog.getViews()+1);
        blogMapper.updateBlog(blog);*/
        MarkdownUtils markdownUtils = new MarkdownUtils();
        blog.setContent(markdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    @Transactional
    @Override
    public boolean savaBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        boolean success =  blogMapper.savaBlog(blog);
        //System.out.println("结果:"+success);
        for (Tag tag: blog.getTags()){
            blogMapper.savaBlogTag(blog.getId(),tag.getId());
        }
        return success;

    }

    @Transactional
    @Override
    public boolean updateBlog(Blog blog) {
        Blog oldBlogData =  blogMapper.getBlogById(blog.getId());
        blog.setViews(oldBlogData.getViews());
        blog.setUpdateTime(new Date());
        boolean success = blogMapper.updateBlog(blog);
        blogMapper.deleteBlogTag(blog.getId());
        for (Tag tag: blog.getTags()){
            blogMapper.savaBlogTag(blog.getId(),tag.getId());
        }
        return success;
    }

    @Transactional
    @Override
    public boolean deleteBlog(Long id) {

        blogMapper.deleteBlogTag(id);
        boolean success = blogMapper.deleteBlog(id);
        return success;
    }

    @Override
    public PageInfo<Blog> listBlogForIndex(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Blog>  list = blogMapper.listBlogForIndex();
        return new PageInfo<Blog>(list);
    }

    @Override
    public PageInfo<Blog> listBlogForType(int currentPage, int pageSize, Long typeId) {
        PageHelper.startPage(currentPage,pageSize);
        List<Blog> list = blogMapper.listBlogForType(typeId);
        return new PageInfo<Blog>(list);
    }

    @Override
    public PageInfo<Blog> listBlogForTag(int currentPage, int pageSize, Long tagId) {
        PageHelper.startPage(currentPage,pageSize);
        List<Blog> list = blogMapper.listBlogForTag(tagId);
        return new PageInfo<Blog>(list);
    }

    @Override
    public List<Blog> getBlogByViewsTop(Integer count) {
        return blogMapper.getBlogByViewsTop(count);
    }

    @Override
    public List<Blog> getBlogByCreateTime(Integer count) {
        return blogMapper.getBlogByCreateTime(count);
    }

    @Transactional
    @Override
    public  Map<String,List<Blog>> getBlogByYear() {
        List<String> years = blogMapper.getAllBlogYear();
        Map<String,List<Blog>> map = new HashMap<>();
        for(String year : years){
            map.put(year,blogMapper.getBlogByYear(year));
        }
        return map;
    }

    @Override
    public Integer getBlogCount() {
        return blogMapper.getBlogCount();
    }


}
