package com.hj.blog.contorller;

import com.github.pagehelper.PageInfo;
import com.hj.blog.po.Blog;
import com.hj.blog.po.Type;
import com.hj.blog.service.BlogService;
import com.hj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @RequestMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1") Integer currentPage,
                        @RequestParam(defaultValue = "6") Integer pageSize,
                        @PathVariable Long id,
                        Model model){
        List<Type> types = typeService.getTypeTop(1000);
        if(id==-1){
            id = types.get(0).getId();
        }
        PageInfo<Blog> blogInfoByType = blogService.listBlogForType(currentPage,pageSize,id);
        model.addAttribute("types",types);
        model.addAttribute("blogInfoByType",blogInfoByType);
        model.addAttribute("activeTypeId",id);
        return "type";
    }


}
