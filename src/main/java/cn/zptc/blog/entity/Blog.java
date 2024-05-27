package cn.zptc.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Blog {

    private Long id;
    private String title; //标题
    private String content; //内容
    private String firstPicture; //首图
    private String flag; //标记
    private Integer views; //浏览次数
    private String description;//描述
    private int appreciation; //赞赏是否开启
    private int shareStatement; //版权是否开启
    private int commentabled; //评论是否开启
    private int published; //是否发布
    private Integer recommend; //是否推荐
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    private String tagIds;

    private Type type;

    private List<Tag> tags;

    private User user;

    private List<Comment> comments;
}
