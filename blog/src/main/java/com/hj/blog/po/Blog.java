package com.hj.blog.po;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag; //原创/转载
    private Integer views = 0; //浏览数
    private boolean appreciation; //赞赏
    private boolean shareStatement; //版权标记
    private boolean commentabled; //能否评论
    private boolean recommend; //是否推荐
    private boolean published; //发布状态(草稿/发布)
    private String description; //描述

    private String author; //作者

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateTime;

    private List<Comment> comments;//留言数

    private Type type;
    private List<Tag> tags;


    //tagId集合字符串
    private String tagIds;



}
