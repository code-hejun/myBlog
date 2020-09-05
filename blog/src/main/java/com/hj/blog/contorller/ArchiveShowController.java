package com.hj.blog.contorller;

import com.hj.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap",blogService.getBlogByYear());
        model.addAttribute("blogCount",blogService.getBlogCount());
        return "archives";
    }

}
