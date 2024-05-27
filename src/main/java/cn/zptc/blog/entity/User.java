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
public class User {
    private Long id;
    private String nickName;
    private String userName;
    private String password;
    private String email;
    private String avatar; //头像
    private Integer type; //用户类型
    private Date createTime;
    private Date updateTime;

    private List<Blog> blogs;
}

