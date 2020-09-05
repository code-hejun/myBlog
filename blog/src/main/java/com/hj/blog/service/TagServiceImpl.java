package com.hj.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hj.blog.mapper.BlogMapper;
import com.hj.blog.po.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Tag getTagById(Integer id) {

        return blogMapper.getTagById(id);
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> tags = blogMapper.listTag();
        return tags;
    }

    @Override
    public PageInfo<Tag> listTag(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Tag> list = blogMapper.listTag();
        return new PageInfo<>(list);
    }

    @Override
    public List<Tag> listTagWithIds(String ids){
        List<Tag> tags = blogMapper.listTagWithIds(ids);
        return tags;
    }

    @Override
    public void saveTag(Tag tag) {
        blogMapper.saveTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        blogMapper.updateTag(tag);
    }


    @Override
    public void removeTagById(Integer id) {
        blogMapper.removeTagById(id);
    }

    @Override
    public List<Tag> getTagTop(Integer count) {
        return blogMapper.getTagTop(count);
    }

}
