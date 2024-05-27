package cn.zptc.blog.entity;


import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Log {
    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;

}
