package com.hj.blog.contorller;

import com.github.pagehelper.PageInfo;
import com.hj.blog.po.Blog;
import com.hj.blog.po.Tag;
import com.hj.blog.service.BlogService;
import com.hj.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    @RequestMapping("/tags/{id}")
    public String tags(@RequestParam(defaultValue = "1") Integer currentPage,
                        @RequestParam(defaultValue = "6") Integer pageSize,
                        @PathVariable Long id,
                        Model model){

        List<Tag> tags = tagService.getTagTop(1000);
        if(id==-1){
            id = tags.get(0).getId();
        }
        PageInfo<Blog> blogInfoByTag = blogService.listBlogForTag(currentPage,pageSize,id);
        model.addAttribute("tags",tags);
        model.addAttribute("blogInfoByTag",blogInfoByTag);
        model.addAttribute("activeTagId",id);
        return "tag";
    }
}
