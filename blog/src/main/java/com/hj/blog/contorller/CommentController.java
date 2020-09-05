package com.hj.blog.contorller;

import com.hj.blog.po.Comment;
import com.hj.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("/comments/{id}")
    public String comments(@PathVariable Long id, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments",comments);
        return "blog::commentList";
    }

    @RequestMapping("/comments")
    public String post(Comment comment, Model model, HttpSession session){
        if(session.getAttribute("user")!=null){
            comment.setAdminComment(true);
        }
        Long blogId = comment.getBlog().getId();
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
