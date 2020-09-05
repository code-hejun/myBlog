package com.hj.blog.contorller.admin;

import com.github.pagehelper.PageInfo;
import com.hj.blog.po.Tag;
import com.hj.blog.po.Type;
import com.hj.blog.service.TagService;
import com.hj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    //展示type页面
   /*@RequestMapping("/types")
    public String showTypes(Model model){
        List<Type> types =  typeService.listType();
        model.addAttribute("types",types );
        return "admin/type";
    }*/

    //展示type页面
    @RequestMapping("/types")
    public String showTypes(@RequestParam(defaultValue = "1")Integer currentPage,
                            @RequestParam(defaultValue = "6")Integer pageSize,
                            Model model){
        PageInfo<Type> types =  typeService.listType(currentPage,pageSize);
        model.addAttribute("types",types );
        return "admin/type";
    }

    //跳转到新增页面
    @RequestMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }
    //跳转到编辑页面
    @RequestMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getTypeById(id));
        return "admin/type-input";
    }

    //进行新增,并重定向到展示页面
    @RequestMapping("/types2")
    public String typeInput(Type type){
        typeService.saveType(type);
        return "redirect:/admin/types";
    }
    //进行编辑,并重定向到展示页面
    @RequestMapping("/types2/{id}")
    public String typeInput2(@PathVariable Long id, Type type){
        type.setId(id);
        typeService.updateType(type);
        return "redirect:/admin/types";
    }

    //删除
    @RequestMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Integer id){
        typeService.removeTypeById(id);
        return "redirect:/admin/types";
    }


}
