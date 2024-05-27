package cn.zptc.blog.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Tag {
    private Long id;
    @NotEmpty(message = "标签名称不能为空！")
    private String name;

    private Integer blogsCount;
    private List<Blog> blogs;
}
