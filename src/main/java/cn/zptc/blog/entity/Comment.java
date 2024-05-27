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
public class Comment {
    private Long id;
    private String nickName;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private boolean isAdminComment;

    private Blog blog;
    private List<Comment> replyComments;

    private Comment parentComment;
}
