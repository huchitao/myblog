package cn.zptc.blog.service;

import cn.zptc.blog.entity.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> listBlog(Blog blog);
    Blog getBlogById(Long id);
    int deleteBlog(Long id);
    int updateBlog(Long id,Blog blog);
    int savaBlog(Blog blog);
    List<Blog> listNewBlog(int count);
    List<Blog> queryBlogs(String query);
    List<Blog> listBlogByTagId(Long id);
    Blog getBlogtoDetail(Long id);
    int updateBlogViews(Long id);
    List<Blog> listBlogInId(List<Long> ids);
    List<Integer> getAllYear();
    List<Blog> getBlogByYear(Integer year);
    Integer getBlogCount();
}
