package cn.zptc.blog.service.serviceImpl;

import cn.zptc.blog.dao.BlogDao;
import cn.zptc.blog.dao.BlogsTagsDao;
import cn.zptc.blog.dao.TagDao;
import cn.zptc.blog.entity.Blog;
import cn.zptc.blog.entity.Tag;
import cn.zptc.blog.entity.Type;
import cn.zptc.blog.entity.User;
import cn.zptc.blog.exception.NotFoundException;
import cn.zptc.blog.service.BlogService;
import cn.zptc.blog.util.MarkDownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogsTagsDao blogsTagsDao;

    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public List<Blog> listBlog(Blog blog) {
        return blogDao.listBlog(blog);
    }

    @Transactional
    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        blogsTagsDao.deleteTags(id);
        return blogDao.deleteBlog(id);
    }

    @Transactional
    @Override
    public int updateBlog(Long id, Blog blog) {
        Blog b = blogDao.getBlogById(id);
        if (b == null) {
            throw new NotFoundException("该博客不存在！");
        }
        blogsTagsDao.deleteTags(blog.getId());
        if(getTagIds(blog.getTagIds()).size()!=0){
            blogsTagsDao.addTags(getTagIds(blog.getTagIds()),blog.getId());
        }
        blog.setUpdateTime(new Date());
        BeanUtils.copyProperties(blog,b);
        return blogDao.updateBlog(b);
    }


    @Transactional
    @Override
    public int savaBlog(Blog blog) {
        Date date = new Date();
        blog.setViews(0);
        blog.setCreateTime(date);
        blog.setUpdateTime(date);
        int i= blogDao.savaBlog(blog);
        List<Long> tagIds = getTagIds(blog.getTagIds());
        if(tagIds.size()!=0){
            blogsTagsDao.addTags(tagIds,blog.getId());
        }

        return i;
    }

    @Override
    public List<Blog> listNewBlog(int count) {
        return blogDao.listNewBlog(count);
    }

    @Override
    public List<Blog> queryBlogs(String query) {
        return blogDao.queryBlogs(query);
    }

    @Override
    public List<Blog> listBlogByTagId(Long id) {
        String allBlogIds = blogsTagsDao.getAllBlogIds(id);
        if(allBlogIds==null||allBlogIds==""){
            return new ArrayList<Blog>();
        }
        List<Long> ids = new ArrayList<>();
        String[] split = allBlogIds.split(",");
        for (int i = 0; i <split.length ; i++) {
            ids.add(Long.valueOf(split[i]));
        }
        List<Blog> blogs = listBlogInId(ids);
        List<Blog> bs = new ArrayList<>();
        for (Blog b:blogs) {
            Blog blog = new Blog();
            BeanUtils.copyProperties(b,blog);
            List<Tag> allTag = getAllTag(blog.getTagIds());
            blog.setTags(allTag);
            bs.add(blog);
        }
        return bs;
    }

    @Transactional
    @Override
    public Blog getBlogtoDetail(Long id) {
        Blog judgeBlog = blogDao.getBlogtoDetail(id);
        if(judgeBlog==null){
            throw new NotFoundException("博客不存在");
        }
        updateBlogViews(id);
        Blog blog = blogDao.getBlogtoDetail(id);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        List<Tag> allTag = getAllTag(blog.getTagIds());
        b.setTags(allTag);
        b.setContent(MarkDownUtils.markdownToHtmlExtensions(b.getContent()));
        return b;
    }

    @Transactional
    @Override
    public int updateBlogViews(Long id) {
        return blogDao.updateBlogViews(id);
    }

    @Override
    public List<Blog> listBlogInId(List<Long> ids) {
        return blogDao.listBlogInId(ids);
    }

    @Override
    public List<Integer> getAllYear() {
        return blogDao.getAllYear();
    }

    @Override
    public List<Blog> getBlogByYear(Integer year) {
        return blogDao.getBlogByYear(year);
    }

    @Override
    public Integer getBlogCount() {
        return blogDao.getBlogCount();
    }

    public Map<Integer,List<Blog>> archivesBlog(){
        Map map=new LinkedHashMap<Integer,List<Blog>>();
        List<Integer> allYear = getAllYear();
        for (int i = 0; i <allYear.size() ; i++) {
            Integer currentYear = allYear.get(i);
            map.put(currentYear,getBlogByYear(currentYear));
        }
        return map;
    }

    public List<Tag> getAllTag(String tags){
        if(null!=tags){
            String[] split = tags.split(",");
            List<Tag> tag=new ArrayList<>();
            for (int i = 0; i <split.length ; i++) {
                Tag tagById = tagDao.getTagById(Long.valueOf(split[i]));
                tag.add(tagById);
            }
            return tag;
        }else{
            return null;
        }
    }


    public List<Long> getTagIds(String tagIds){
        ArrayList<Long> lists = new ArrayList<>();
        if(null!=tagIds&&tagIds!=""){
            String[] tagId = tagIds.split(",");

            for (int i = 0; i < tagId.length; i++) {
                lists.add(Long.valueOf(tagId[i]));
            }
        }

        return lists;
    }
}
