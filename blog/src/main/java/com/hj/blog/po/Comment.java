package com.hj.blog.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();

    private Comment parentComment;

    private boolean adminComment = false;
}
