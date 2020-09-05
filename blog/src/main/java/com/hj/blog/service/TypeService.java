package com.hj.blog.service;

import com.github.pagehelper.PageInfo;
import com.hj.blog.po.Tag;
import com.hj.blog.po.Type;

import java.util.List;


public interface TypeService {

    Type getTypeById(Long id);

    List<Type> listType();

    PageInfo<Type> listType(int currentPage, int pageSize);

    void saveType(Type type);

    void updateType(Type type);

    void removeTypeById(Integer id);

    List<Type> getTypeTop(Integer count);
}
