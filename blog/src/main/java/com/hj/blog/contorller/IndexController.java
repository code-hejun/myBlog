package com.hj.blog.contorller;

import com.github.pagehelper.PageInfo;
import com.hj.blog.po.Blog;
import com.hj.blog.po.Tag;
import com.hj.blog.service.BlogService;
import com.hj.blog.service.TagService;
import com.hj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    @Autowired
    TypeService typeService;

    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "1") Integer currentPage,
                        @RequestParam(defaultValue = "5") Integer pageSize, Model model,
                        HttpSession session){
        //System.out.println(session.getAttribute("user"));

        model.addAttribute("tagTop",tagService.getTagTop(12));
        model.addAttribute("typeTop",typeService.getTypeTop(6));
        model.addAttribute("blogByViewsTop",blogService.getBlogByViewsTop(6));
        PageInfo<Blog> blogInfo =  blogService.listBlogForIndex(currentPage,pageSize);
        model.addAttribute("blogInfo",blogInfo);
        return "index";
    }

    @RequestMapping("/blog/{id}")
    public String getBlog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

    @RequestMapping("/footer/newBlog")
    public String newBlog(Model model) {
        model.addAttribute("newBlogList", blogService.getBlogByCreateTime(2));
        return "_fragments :: newBlogList";
    }

}
