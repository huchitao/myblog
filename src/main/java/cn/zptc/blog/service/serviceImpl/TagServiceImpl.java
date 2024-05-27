package cn.zptc.blog.service.serviceImpl;

import cn.zptc.blog.dao.TagDao;
import cn.zptc.blog.dao.TypeDao;
import cn.zptc.blog.entity.Tag;
import cn.zptc.blog.entity.Type;
import cn.zptc.blog.exception.NotFoundException;
import cn.zptc.blog.service.TagService;
import cn.zptc.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagDao.getTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Transactional
    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }

    @Transactional
    @Override
    public int updateTag(Long id,Tag tag) {
        Tag t= tagDao.getTagById(id);
        if(t==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,t);
        return tagDao.updateTag(tag);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.listTag();
    }

    @Override
    public List<Tag> listTagTop() {
        return tagDao.listTagTop();
    }

    @Override
    public List<Tag> listTagOrderByCount() {
        return tagDao.listTagOrderByCount();
    }
}
