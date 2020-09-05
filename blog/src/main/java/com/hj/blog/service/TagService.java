package com.hj.blog.service;

import com.github.pagehelper.PageInfo;
import com.hj.blog.po.Tag;


import java.util.List;


public interface TagService {

    Tag getTagById(Integer id);

    List<Tag> listTag();

    PageInfo<Tag> listTag(int currentPage, int pageSize);

    List<Tag> listTagWithIds(String ids);

    void saveTag(Tag tag);

    void updateTag(Tag tag);

    void removeTagById(Integer id);

    List<Tag> getTagTop(Integer count);



}
