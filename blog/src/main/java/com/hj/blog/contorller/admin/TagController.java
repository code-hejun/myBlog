package com.hj.blog.contorller.admin;

import com.github.pagehelper.PageInfo;
import com.hj.blog.po.Tag;
import com.hj.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagService tagService;

    //展示tag页面
   /* @RequestMapping("/tags")
    public String showTags(Model model){
        List<Tag> tags =  tagService.listTag();
        model.addAttribute("tags",tags);
        return "admin/tag";
    }*/

    //展示tag页面
    @RequestMapping("/tags")
    public String showTags(@RequestParam(defaultValue = "1")Integer currentPage,
                           @RequestParam(defaultValue = "6")Integer pageSize,
                            Model model){
        PageInfo<Tag> tags =  tagService.listTag(currentPage,pageSize);
        model.addAttribute("tags",tags);
        return "admin/tag";
    }

    //跳转到新增页面
    @RequestMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }
    //跳转到编辑页面
    @RequestMapping("/tags/{id}/input")
    public String editInput(@PathVariable Integer id,Model model){
        model.addAttribute("tag",tagService.getTagById(id));
        return "admin/tag-input";
    }

    //进行新增,并重定向到展示页面
    @RequestMapping("/tags2")
    public String tagInput(Tag tag){
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }
    //进行编辑,并重定向到展示页面
    @RequestMapping("/tags2/{id}")
    public String tagInput2(@PathVariable Long id, Tag tag){
        System.out.println(id);
        tag.setId(id);
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }

    //删除
    @RequestMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Integer id){
        tagService.removeTagById(id);
        return "redirect:/admin/tags";
    }


}
