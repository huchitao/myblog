package cn.zptc.blog.dao;


import cn.zptc.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogDao {
    List<Blog> listBlog(Blog blog);
    Blog getBlogById(Long id);
    int deleteBlog(Long id);
    int updateBlog(Blog blog);
    int savaBlog(Blog blog);
    List<Blog> listNewBlog(int count);
    List<Blog> queryBlogs(String query);
    Blog getBlogtoDetail(Long id);
    int updateBlogViews(Long id);
    List<Blog> listBlogInId(List<Long> ids);
    List<Integer> getAllYear();
    List<Blog> getBlogByYear(Integer year);
    Integer getBlogCount();
}
