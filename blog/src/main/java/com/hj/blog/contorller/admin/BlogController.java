package com.hj.blog.contorller.admin;
import com.github.pagehelper.PageInfo;
import com.hj.blog.common.DataGridView;
import com.hj.blog.common.SelecetData;
import com.hj.blog.po.Blog;
import com.hj.blog.po.Tag;
import com.hj.blog.po.Type;
import com.hj.blog.service.BlogService;
import com.hj.blog.service.TagService;
import com.hj.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;


    //展示blog页面
    @RequestMapping("/blog")
    public String listBlog(@RequestParam(defaultValue = "1")Integer currentPage,
                            @RequestParam(defaultValue = "6")Integer pageSize,
                            Model model){
        PageInfo<Blog> blogList = blogService.listBlog(currentPage,pageSize);
        List<Type> typeList = typeService.listType();
        model.addAttribute("blogs",blogList);
        model.addAttribute("types",typeList);
        return "admin/blogs";
    }


  /*  @RequestMapping("/blog/search")
    public String search(@RequestParam(defaultValue = "1")Integer currentPage,
                            @RequestParam(defaultValue = "2")Integer pageSize,
                            Model model){
        PageInfo<Blog> blogList = blogService.listBlog(currentPage,pageSize);
        List<Type> typeList = typeService.listType();
        model.addAttribute("blogs",blogList);
        model.addAttribute("types",typeList);
        return "/admin/blogs";
    }*/


    //跳转到新增页面
    @RequestMapping("/blog/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = new Blog();
        blog.setId(0L);
        blog.setType(new Type());
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    //跳转到修改页面
    @RequestMapping("/blog/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = blogService.getBlogById(id);

       /* List<Long> list = new ArrayList<>();
        for(Tag tag: blog.getTags()){
            list.add(tag.getId());
        }
        blog.setTag(list);*/

        /*String str = "";
        for(Tag tag: blog.getTags()){
            str = str + tag.getId()+",";
        }
        str = str.substring(0,str.length()-1);
        System.out.println(str);
        blog.setTagList(str);*/

        //System.out.println(blog.toString());

        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }


    //获取多选框数据
    @RequestMapping("/json/{id}")
    @ResponseBody
    public DataGridView getDateForJson(@PathVariable Long id){
        //System.out.println("获取多选数据");
        List<Long> list = new ArrayList<>();
        //System.out.println(id);
        if(id!=0L){
            Blog blog = blogService.getBlogById(id);
            //博客的标签的id的集合
            for(Tag tag: blog.getTags()){
                list.add(tag.getId());
            }
        }else {
            System.out.println("无选中数据");
        }

        List<SelecetData> selecetDataList = new ArrayList<>();
        List<Tag> TagList = tagService.listTag();
        for(Tag tag : TagList){
            SelecetData selecetData = new SelecetData();
            if(list.contains(tag.getId())){
                selecetData.setName(tag.getName());
                selecetData.setValue(tag.getId());
                selecetData.setSelected("selected");
            }else {
                selecetData.setName(tag.getName());
                selecetData.setValue(tag.getId());
            }
            selecetDataList.add(selecetData);
        }
        DataGridView dataGridView = new DataGridView(selecetDataList);
        return dataGridView;
    }


    //完成新增或修改，跳转到blog页面
    @RequestMapping("/blogs")
    public String post(Blog blog){
        Long tid = blog.getType().getId();
        //System.out.println(tid);
        blog.setType(typeService.getTypeById(tid));
        blog.setTags(tagService.listTagWithIds(blog.getTagIds()));
        //System.out.println(blog);
        if(blog.getId()==null||blog.getId()==0L){
            blogService.savaBlog(blog);
        }else {
            blogService.updateBlog(blog);
        }
        return "redirect:/admin/blog";
    }

    @RequestMapping("/delete")
    public String delete(Long id){

        blogService.deleteBlog(id);
        return "redirect:/admin/blog";
    }





}
