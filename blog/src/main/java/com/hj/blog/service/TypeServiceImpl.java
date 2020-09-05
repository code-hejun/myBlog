package com.hj.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hj.blog.mapper.BlogMapper;
import com.hj.blog.po.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Type getTypeById(Long id) {

        return blogMapper.getTypeById(id);
    }

    @Override
    public List<Type> listType() {
        List<Type> types = blogMapper.listType();
        return types;
    }

    @Override
    public PageInfo<Type> listType(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Type> list = blogMapper.listType();
        return new PageInfo<>(list);
    }

    @Override
    public void saveType(Type type) {
        blogMapper.saveType(type);
    }

    @Override
    public void updateType(Type type) {
        blogMapper.updateType(type);
    }

    @Override
    public void removeTypeById(Integer id) {
        blogMapper.removeTypeById(id);
    }

    @Override
    public List<Type> getTypeTop(Integer count) {
        return blogMapper.getTypeTop(count);
    }

}
